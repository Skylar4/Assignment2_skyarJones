/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package implementations;

import exceptions.EmptyQueueException;
import exceptions.EmptyStackException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jones
 */
public class XMLParser {

    File file;
    //for storing the stack
    MyStack<TagInfo> stack;
    //for storing the errorQ that come up and what line they happen on.
    MyQueue<String> errorQ;
    MyQueue<String> extrasQ;

    public XMLParser(File file) {
        this.file = file;
        this.stack = new MyStack<>();
        this.errorQ = new MyQueue<>();
        this.extrasQ = new MyQueue<>();

    }

    public void parse() throws EmptyQueueException, EmptyStackException {
        int lineNumber = 1;
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                if (!line.isEmpty() && !line.startsWith("<?xml")) {
                    lineParse(line, lineNumber);
                }
                lineNumber++;
            }
            checkUnmatchedTags();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(XMLParser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void lineParse(String line, int lineNumber) throws EmptyQueueException {
        int index = 0;
        while (index < line.length()) {
            int startOfTag = line.indexOf("<", index);
            if (startOfTag == -1) {
                return;
            }

            int endOfTag = line.indexOf(">", startOfTag);
            if (endOfTag == -1) {
                errorQ.enqueue("Error at line " + lineNumber + ": Malformed tag");
                return;
            }

            String tag = line.substring(startOfTag + 1, endOfTag).trim();
            String tagName = tag.split("\\s+")[0]; // Get the string before any space

            if (isSelfClosing(tag)) {
                // Self-closing tag, ignore
            } else if (isEndTag(tag)) {
                tagName = tagName.substring(1).trim();
                if (!stack.isEmpty() && matches(stack.peek().tagName, tagName)) {
                    stack.pop();
                } else if (!errorQ.isEmpty() && errorQ.peek().equals(tagName)) {
                    errorQ.dequeue();
                } else if (stack.isEmpty()) {
                    errorQ.enqueue("Error at line " + lineNumber + ": Unmatched closing tag " + tagName);
                } else {
                    handleUnmatchedEndTag(tagName, lineNumber);
                }
            }  else if (isStartTag(tag)) {
                stack.push(new TagInfo(tagName, lineNumber));
            } else {
                errorQ.enqueue("Error at line " + lineNumber + ": Invalid tag " + tagName);
            }
            index = endOfTag + 1;
        }
    }

    
    private void handleUnmatchedEndTag(String tagName, int lineNumber) throws EmptyQueueException {
        boolean found = false;
        MyStack<TagInfo> tempStack = new MyStack<>();
        while (!stack.isEmpty()) {
            TagInfo topTag = stack.pop();
            tempStack.push(topTag);
            if (topTag.tagName.equals(tagName)) {
                found = true;
                break;
            }
        }
        while (!tempStack.isEmpty()) {
            TagInfo tempTag = tempStack.pop();
            if (found) {
                stack.push(tempTag);
            } else {
                errorQ.enqueue("Error at line " + lineNumber + ": Unmatched tag " + tempTag.tagName);
            }
        }
        if (!found) {
            errorQ.enqueue("Error at line " + lineNumber + ": Unmatched closing tag " + tagName);
        }
    }

    private boolean isSelfClosing(String tag) {
        return tag.endsWith("/");
    }

    private boolean isStartTag(String tag) {
        return !tag.startsWith("/") && !isSelfClosing(tag);
    }

    private boolean isEndTag(String tag) {
        return tag.startsWith("/");
    }

    private boolean matches(String startTag, String endTag) {
        return startTag.equals(endTag);
    }


    // Method to check for unmatched stack after parsing all lines
    private void checkUnmatchedTags() throws EmptyQueueException {
        while (!stack.isEmpty()) { // While there are unmatched stack in the stack
            TagInfo unmatchedTag = stack.pop(); // Pop each unmatched tag
            errorQ.enqueue("Unmatched opening tag: " + unmatchedTag);
        }
        while (!errorQ.isEmpty() && !extrasQ.isEmpty()) { // If either queue is empty (but not both), report each element in both queues as error
            if (!errorQ.peek().equals(extrasQ.peek())) {
                errorQ.dequeue();
            } else {
                errorQ.dequeue();
                extrasQ.dequeue();
            }
        }
    }

    // Method to print errorQ and extra content
    public void printErrors() throws EmptyQueueException {
        System.out.println("=============ERROR LOG=============="); // Print error log header
        if (errorQ.isEmpty() && extrasQ.isEmpty()) { // If no errorQ or extra content, print no errorQ found
            System.out.println("No errors found.");
        } else { // Otherwise, print errorQ and extra content
            while (!errorQ.isEmpty()) {
                System.out.println(errorQ.dequeue());
            }
            while (!extrasQ.isEmpty()) {
                System.out.println("Extra content: " + extrasQ.dequeue());
            }
        }
    }

    // Inner class to store tag information
    private static class TagInfo {

        String tagName;
        int lineNumber;

        TagInfo(String tagName, int lineNumber) {
            this.tagName = tagName;
            this.lineNumber = lineNumber;
        }
    }

    public static void main(String[] args) throws EmptyQueueException, EmptyStackException {
        if (args.length != 1) {
            System.err.println("Usage: java -jar Parser.jar <file-path>");
            return;
        }
        File file = new File(args[0]);
        if (!file.exists()) {
            System.err.println("File not found: " + file.getAbsolutePath());
            return;
        }
        XMLParser parser = new XMLParser(file);
        parser.parse();
        parser.printErrors();
    }
}
