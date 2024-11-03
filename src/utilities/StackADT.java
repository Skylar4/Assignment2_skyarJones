/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utilities;

/**
 *
 * @author jones
 */
/**
 * StackADT interface defines the basic operations for a stack data structure.
 *
 * @param <T> the type of elements in this stack
 */
public interface StackADT<T> {

    /**
     * Adds an element to the top of the stack.
     *
     * @param element the element to be added to the stack
     * @throws NullPointerException if the specified element is null
     */
    void push(T element);

    /**
     * Removes and returns the top element of the stack.
     *
     * @return the top element of the stack
     * @throws NoSuchElementException if the stack is empty
     */
    T pop();

    /**
     * Returns the top element of the stack without removing it.
     *
     * @return the top element of the stack
     * @throws NoSuchElementException if the stack is empty
     */
    T peek();

    /**
     * Returns true if the stack contains no elements.
     *
     * @return true if the stack is empty, false otherwise
     */
    boolean isEmpty();

    /**
     * Returns the number of elements in the stack.
     *
     * @return the number of elements in the stack
     */
    int size();
}