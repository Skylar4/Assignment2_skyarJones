/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utilities;

/**
 *
 * @author jones
 */
public interface  QueueADT<E>{
    
    
    
    public void queue(E object);
    
    
    public void unqueue();
            
    public boolean isEmpty();
    
    public int size();
    
    public boolean isFull();
    
    
    
    
    
}
