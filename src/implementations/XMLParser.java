/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package implementations;

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
    MyStack<String> stack;
    //for storing the errors that come up and what line they happen on.
    MyQueue<String> errors;
    
    public XMLParser(File file ) {
        this.file = file;
        this.stack = new MyStack();
        this.errors = new MyQueue();
        
    }
    
    
    
    public void parse(){

        
        try (Scanner scanner = new Scanner(file)){
            
            while (scanner.hasNextLine()){
                String line = scanner.nextLine();
                lineParse(line);
                
            }
            
            
            
            
            
            
            
            
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(XMLParser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void lineParse(String line){
         //need to find the self closing tags <tag/>
        // start tags <tag> and end tags </tag>
        // we ignore the self closing
        // for start tags push them on to the top of the stack
        //for end tags check them with the top of the stack if they  match, pop it.
        //if not add it to the error queue and move on. 
        
        
        
        
        
    }
    
    
    
    
    
    
}
