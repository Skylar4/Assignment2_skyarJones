/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ouroldADTs;

/**
 * This interface includes all the methods required to have a functioning Queue
 * data structure
 * 
 * @author jones
 * @modified marian
 * @param <T> the data type that the queue will hold.
 */
public interface QueueADT<T> {

    /**
     * Adds an element to the end of the queue.
     *
     * @param element the element to be added to the queue
     * @throws NullPointerException if the specified element is null
     */
    void enqueue(T element);

    /**
     * Removes and returns the front element of the queue.
     *
     * @return the front element of the queue
     * @throws NoSuchElementException if the queue is empty
     */
    T dequeue();

    /**
     * Returns the front element of the queue without removing it.
     *
     * @return the front element of the queue
     * @throws NoSuchElementException if the queue is empty
     */
    T first();

    /**
     * Returns true if the queue contains no elements.
     *
     * @return true if the queue is empty, false otherwise
     */
    boolean isEmpty();

    /**
     * Returns the number of elements in the queue.
     *
     * @return the number of elements in the queue
     */
    int size();
}