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
    //for storing the tags
    MyStack<String> tags;
    //for storing the errors that come up and what line they happen on.
    MyQueue<String> errors;
    MyQueue<String> extras;

    public XMLParser(File file) {
        this.file = file;
        this.tags = new MyStack();
        this.errors = new MyQueue();
        this.extras = new MyQueue();

    }

    public void parse() {

        int lineNumber = 1;
        try (Scanner scanner = new Scanner(file)) {

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                lineParse(line, lineNumber);
                lineNumber++;

            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(XMLParser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void lineParse(String line, int lineNumber) {
        //need to find the self closing tags <tag/>
        // start tags <tag> and end tags </tag>
        // we ignore the self closing
        // for start tags push them on to the top of the stack
        //for end tags check them with the top of the stack if they  match, pop it.
        //if not add it to the error queue and move on. 
        int index = 0;

        while (index < line.length()) {

            // finding the start of a tag.      
            int startOfTag = line.indexOf("<", index);
            // if start of tag doesnt find the start of a tag it breaks out of the function
            if (startOfTag == -1) {
                return;
            }
            //find the index of the ned of the tag.
            int endOfTag = line.indexOf(">", index);
            // sets the index at just after the end of the tag to look for another on the same line
            index = endOfTag + 1;
            //checks if the tag is self closing if it is is loops around. 
            if (line.charAt(endOfTag - 1) == '/') {
                continue;
            }

            // cutting the tag out of the line
            String tag = line.substring(startOfTag, endOfTag);
            // checks if it is not an ending tag then adds it to the top of the stack
            if (line.charAt(startOfTag + 1) != '/') {
                tags.push(tag);
            }
            //checks if the tag is an ending tag
            if (tag.charAt(startOfTag + 1) == '/') {
                //working with a ending tag, so we have to see if it has a matching start tag 
                // checks to see if the tags stack is empty
                if (tags.isEmpty()) {
                    errors.enqueue(tag + ";" + lineNumber);

                }
                String topTag = tags.peek();
                topTag = topTag.substring(1, topTag.length() - 1);
                String InsideOfTag = tag.substring(2, tag.length() - 1);

                //if the tags match pop the tag off the stack.
                if (topTag.equals(InsideOfTag)) {

                    tags.pop();

                } else if (!topTag.equals(InsideOfTag)) {
                    // if the closing tag doesnt match the top tag, add it to the error queue
                    errors.enqueue(tag + ":" + lineNumber);
                } else {

                }

            }
        }

    }

    private void checkUnmatchedTags() {
        while (!tags.isEmpty()) {
            errors.enqueue("Unmatched opening tag: " + tags.pop());
        }
    }

    public void printErrors() throws EmptyQueueException {
        while (!errors.isEmpty()) {
            System.out.println(errors.dequeue());
        }
    }

    public static void main(String[] args) throws EmptyQueueException {
        if (args.length != 1) {
            System.err.println("Usage: java XMLParser <file-path>");
            return;
        }
        File file = new File(args[0]);
        XMLParser parser = new XMLParser(file);
        parser.parse();
        parser.printErrors();
    }
}
