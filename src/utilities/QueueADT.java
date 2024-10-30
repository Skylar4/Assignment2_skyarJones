/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utilities;

/**
 * This interface includes all the methods required to have a functioning Queue
 * data structure
 * 
 * @author jones
 * @param <E> the data type that the queue will hold.
 */
public interface QueueADT<E> {

    /**
     * This method will place the object at the back of the queue if the queue
     * is full it should throw an exception.
     *
     * pre-conditions:
     *  that the queue is not already full 
     * 
     * post-conditions:
     *  the object has been added to the back of the queue 
     *  the size is increased by 1
     * @param object the item to be added.
     */
    public void queue(E object);

    /**
     * unqueue will remove the item at the front of the queue and return it.
     *
     * pre-conditions:
     *  there is an object in the queue 
     * post-conditions:
     *  the object at the front of the queue is removed and returned. 
     *  the size is reduced by 1 
     * 
     * @return the object at the front of the queue
     * @throws an exception if the list is empty
     */
    public E unqueue();

    /**
     * isEmpty will return true if it is empty and false if there is still items
     * within.
     *
     * pre-conditions:
     * no precondition is required for this boolean return
     * post-conditions:
     *that a boolean is returned depending on if the queue is empty or not
     * @return a boolean
     */
    public boolean isEmpty();

    /**
     * size will return the current number of elements within the queue
     *
     * pre-conditions:
     * this does not require any pre conditions
     * post-conditions:
     * an integer being returned that specifies how many elements are inside the queue. 
     *
     * @return the number of elements
     */
    public int size();

    /**
     * Opposite of isEmpty. will return true if the queue is full, and false
     * when it is not.
     * pre-conditions:
     *  this depends on wether or not the queue has a specified set size or not
     * post-conditions:
     * returns a boolean based on wether or not the size is at the set cap or not
     *
     * @return a boolean
     */
    public boolean isFull();

}
