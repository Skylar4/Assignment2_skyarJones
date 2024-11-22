/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package implementations;

import java.util.EmptyStackException;
import utilities.Iterator;
import utilities.StackADT;

/**
 *
 *  * A generic stack implementation using an array list. This class supports typical stack operations
 * such as push, pop, peek, and checking if the stack is empty or full.
 * 
 * @author jones
 */
public class MyStack<E> implements StackADT<E> {

    MyArrayList<E> stack;
    int size;
    int capacity;
    
    
     /**
     * Constructs a new stack with the default capacity of 10.
     */
    public MyStack(){
        this.capacity = 10;
        stack = new MyArrayList<>();
    }
    
      /**
     * Constructs a new stack with the specified capacity.
     *
     * @param capacity the maximum capacity of the stack
     */

    public MyStack(int capacity) {
        this.capacity = capacity;
        stack = new MyArrayList<>();
    }
    
    /**
     * Adds an element to the top of the stack.
     *
     * @param tag the element to be added to the stack
     * @throws NullPointerException if the specified element is {@code null}
     */
    
    @Override
    public void push(E tag) throws NullPointerException {
        if (tag == null){
            throw new NullPointerException();
        }
        stack.add(0, tag);
        size++;
    }

     /**
     * Removes and returns the element at the top of the stack.
     *
     * @return the element that was removed from the top of the stack
     * @throws EmptyStackException if the stack is empty
     */
    
    @Override
    public E pop() throws EmptyStackException {
        if (size == 0 ){
            throw new EmptyStackException();
        }
        E removed = stack.get(0);
        stack.remove(0);
        size--;
        return removed;
    }
    
    /**
     * Returns the element at the top of the stack without removing it.
     *
     * @return the element at the top of the stack
     * @throws EmptyStackException if the stack is empty
     */
    

    @Override
    public E peek() throws EmptyStackException {
            if (size == 0 ){
            throw new EmptyStackException();
        }
         return stack.get(0);
             
    }

     /**
     * Clears all elements from the stack.
     */
    
    @Override
    public void clear() {
       stack.clear();
       size = 0;
    }

     /**
     * Checks if the stack is empty.
     *
     * @return {@code true} if the stack is empty, {@code false} otherwise
     */
    
    @Override
    public boolean isEmpty() {
        return stack.isEmpty();
    }

     /**
     * Converts the stack to an array.
     *
     * @return an array containing all elements in the stack
     */
    
    @Override
    public Object[] toArray() {
        return stack.toArray();
    }

    /**
     * Converts the stack to an array, storing the elements in the specified array.
     *
     * @param holder the array to hold the elements
     * @return the array containing the elements in the stack
     * @throws NullPointerException if the specified array is {@code null}
     */
    
    @Override
    public E[] toArray(E[] holder) throws NullPointerException {
        return stack.toArray(holder);
    }

     /**
     * Checks if the stack contains the specified element.
     *
     * @param toFind the element to search for
     * @return {@code true} if the stack contains the element, {@code false} otherwise
     * @throws NullPointerException if the specified element is {@code null}
     */
    
    @Override
    public boolean contains(E toFind) throws NullPointerException {
        if (toFind == null){
            throw new NullPointerException();
        }
        return stack.contains(toFind);
    }

     /**
     * Searches for the specified element in the stack.
     *
     * @param toFind the element to search for
     * @return the 1-based index of the element from the top of the stack, or -1 if not found
     * @throws NullPointerException if the specified element is {@code null}
     */
    @Override
    public int search(E toFind) {
        if (toFind == null) {
            throw new NullPointerException("Element cannot be null");
        }
        Iterator<E> iterator = stack.iterator();
        int index = 0;
        while (iterator.hasNext()) {
            if (iterator.next().equals(toFind)) {
                return index + 1; // 1-based position from the top of the stack
            }
            index++;
        }
        return -1;
    }

    
    /**
     * Returns an iterator over the elements in the stack.
     *
     * @return an iterator over the elements in the stack
     */
    
    @Override
    public Iterator<E> iterator() {
        return stack.iterator();
    }

     /**
     * Checks if this stack is equal to another stack.
     *
     * @param that the stack to compare to
     * @return {@code true} if the two stacks are equal, {@code false} otherwise
     */
    
    @Override
    public boolean equals(StackADT<E> that) {
        if (that == null) {
            return false;
        }
        if (this.size != that.size()) {
            return false;
        }
        Iterator<E> thisIterator = this.iterator();
        Iterator<E> thatIterator = that.iterator();
        while (thisIterator.hasNext() && thatIterator.hasNext()) {
            if (!thisIterator.next().equals(thatIterator.next())) {
                return false;
            }
        }
        return true;
    }

     /**
     * Returns the current size of the stack.
     *
     * @return the size of the stack
     */
    
    @Override
    public int size() {
        return size;
    }

     /**
     * Checks if the stack has reached its maximum capacity.
     *
     * @return {@code true} if the stack is full, {@code false} otherwise
     */
    
    @Override
    public boolean stackOverflow() {
        return size >= capacity;
    }


}
