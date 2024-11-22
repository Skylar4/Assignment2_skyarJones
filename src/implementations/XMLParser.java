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

    static boolean errorsFound = false; // Flag to track if errors were printed

    /**
     *
     * @param file
     */
    public XMLParser(File file) {
        this.file = file;
        this.stack = new MyStack<>();
        this.errorQ = new MyQueue<>();
        this.extrasQ = new MyQueue<>();

    }

    /**
     *
     * @throws EmptyQueueException
     * @throws EmptyStackException
     */
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

    /**
     *
     * @param line
     * @param lineNumber
     * @throws EmptyQueueException
     */
    public void lineParse(String line, int lineNumber) throws EmptyQueueException {
        int index = 0;
        while (index < line.length()) {
            int startOfTag = line.indexOf("<", index);
            if (startOfTag == -1) {
                return;
            }

            int endOfTag = line.lastIndexOf(">");
            if (endOfTag == -1) {
                errorQ.enqueue("Error at line " + lineNumber + ": Malformed tag" + endOfTag);
                return;
            }

            String tag = line.substring(startOfTag + 1, endOfTag).trim();
            String fulltag = line.substring(startOfTag, endOfTag + 1);
            String tagName = tag.split("\s+")[0]; // Get the string before any space

            if (isSelfClosing(tag)) {
                //reverting to just ignore
            } else if (isEndTag(tag)) {
                tagName = tagName.substring(1).trim();
                // If matches top of stack, pop stack and all is well
                if (!stack.isEmpty() && matches(stack.peek().tagName, tagName)) {
                    stack.pop();
                } // Else if matches head of errorQ, dequeue and ignore
                else if (!errorQ.isEmpty() && errorQ.peek().equals(tagName)) {
                    errorQ.dequeue();
                } // Else if stack is empty, add to errorQ
                else if (stack.isEmpty()) {
                    errorQ.enqueue("Error at line " + lineNumber + ": Unmatched closing tag " + "\n\t" + fulltag);
                } // Else search stack for matching Start_Tag
                else {
                    boolean found = false; // Initialize found variable

                    MyStack<TagInfo> tempStack = new MyStack<>();
                    while (!stack.isEmpty()) {
                        TagInfo topTag = stack.pop();
                        if (topTag.tagName.equals(tagName)) {
                            found = true; // Set found to true if matching tag is found
                            break;
                        } else {
                            tempStack.push(topTag);
                        }
                    }

                    if (found) {
                        // Pop each E from stack into errorQ until match, report as error
                        while (!tempStack.isEmpty()) {
                            TagInfo tempTag = tempStack.pop();
                            errorQ.enqueue("Error at line " + lineNumber + "\n\t" + fulltag);
                        }
                    } else {
                        // Add E to extrasQ
                        extrasQ.enqueue("Error at line " + lineNumber + "\n\t" + fulltag);
                        // Push back all elements from tempStack to stack
                        while (!tempStack.isEmpty()) {
                            stack.push(tempStack.pop());
                        }
                    }
                }
            } else if (isStartTag(tag)) {
                stack.push(new TagInfo(tagName, lineNumber));
            }

            // Check for extra closing tags like >>
            if (tag.contains(">")) {
                errorQ.enqueue("Invalid close tag at " + lineNumber + "\n\t" + fulltag);
            }
            index = endOfTag + 1;
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

    private void checkUnmatchedTags() throws EmptyQueueException {
        // If stack is not empty, pop each E into errorQ
        while (!stack.isEmpty()) {
            TagInfo unmatchedTag = stack.pop();
            errorQ.enqueue("Unmatched opening tag: " + unmatchedTag.tagName);
        }

        // Repeat until both queues are empty
        while (!errorQ.isEmpty() || !extrasQ.isEmpty()) {
            // If either queue is empty (but not both), report each E in both queues as error
            if (errorQ.isEmpty() || extrasQ.isEmpty()) {
                if (!errorQ.isEmpty()) {
                    System.out.println(errorQ.dequeue());
                    errorsFound = true;
                }
                if (!extrasQ.isEmpty()) {
                    System.out.println(extrasQ.dequeue());
                    errorsFound = true;
                }
            } else {
                // If both queues are not empty, peek both queues
                if (!errorQ.peek().equals(extrasQ.peek())) {
                    // If they don’t match, dequeue from errorQ and report as error
                    System.out.println(errorQ.dequeue());
                    errorsFound = true;
                } else {
                    // Else dequeue from both
                    errorQ.dequeue();
                    extrasQ.dequeue();
                }
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

    /**
     *
     * @param args
     * @throws EmptyQueueException
     * @throws EmptyStackException
     */
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
        System.out.println("=============ERROR LOG=============="); // Print error log header
        parser.parse();
        if (!errorsFound) // If no errorQ or extra content, print no errorQ found
        {
            System.out.println("No errors found.");
        }
    }
}
