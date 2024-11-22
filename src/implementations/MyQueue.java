/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package implementations;

import exceptions.FullQueueException;
import exceptions.EmptyQueueException;
import java.util.NoSuchElementException;
import utilities.Iterator;
import utilities.QueueADT;

/**
 * A generic queue implementation using a doubly linked list. This class supports typical queue operations
 * 
 * such as enqueue, dequeue, peek, and checking if the queue is empty or full.
 * 
 * @author jones
 * @param <E>
 */
public class MyQueue<E> implements QueueADT<E> {
    
    MyDLL<E> queue;
    int size;
    int capacity;

    
    /**
     * Constructs a new queue with the default capacity of 10.
     */
    public MyQueue(){
        this.capacity = 10;
        queue = new MyDLL<>();
    }
    
    /**
     * Constructs a new queue with the specified capacity.
     *
     * @param capacity the maximum capacity of the queue
     */
    
    public MyQueue(int capacity) {
        this.capacity = capacity;
        queue = new MyDLL<>();
    }
    

    /**
     * Adds an element to the back of the queue.
     *
     * @param toAdd the element to be added to the queue
     * @throws NullPointerException if the specified element is {@code null}
     */
    
    @Override
    public void enqueue(E toAdd) throws NullPointerException {
        if (toAdd == null){
            throw new NullPointerException();
        }
        if (isFull()){
            //throw exception.
        }
        queue.add(toAdd);
        size++;
    }

     /**
     * Removes and returns the element at the front of the queue.
     *
     * @return the element that was removed from the front of the queue
     * @throws EmptyQueueException if the queue is empty
     */
    
    @Override
    public E dequeue() throws EmptyQueueException {
       if (isEmpty()){
           throw new EmptyQueueException();
       }
       E removed = queue.get(0);
       queue.remove(0);
       size--;
       return removed;
       
    }

     /**
     * Returns the element at the front of the queue without removing it.
     *
     * @return the element at the front of the queue
     * @throws EmptyQueueException if the queue is empty
     */
    
    @Override
    public E peek() throws EmptyQueueException {
         if (isEmpty()){
           throw new EmptyQueueException();
       } 
       return queue.get(0);
    }

      /**
     * Removes all elements from the queue.
     */
    
    @Override
    public void dequeueAll() {
        queue.clear();
        size = 0;
    }

    /**
     * Checks if the queue is empty.
     *
     * @return {@code true} if the queue is empty, {@code false} otherwise
     */
    
    @Override
    public boolean isEmpty() {
        return size <= 0;
    }

     /**
     * Checks if the queue contains the specified element.
     *
     * @param toFind the element to search for
     * @return {@code true} if the queue contains the element, {@code false} otherwise
     * @throws NullPointerException if the specified element is {@code null}
     */
    
    @Override
    public boolean contains(E toFind) throws NullPointerException {
      if (toFind == null){
           throw new NullPointerException();
      }
      return queue.contains(toFind);
    }

     /**
     * Searches for the specified element in the queue.
     *
     * @param toFind the element to search for
     * @return the index of the element, or -1 if not found
     * @throws UnsupportedOperationException since this method is not supported
     */
    
    @Override
    public int search(E toFind) {

               throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

     /**
     * Returns an iterator over the elements in the queue.
     *
     * @return an iterator over the elements in the queue
     */
    
    @Override
    public Iterator<E> iterator() {
        return new MyQueueIterator();
    }

    /**
     * Checks if this queue is equal to another queue.
     *
     * @param that the queue to compare to
     * @return {@code true} if the two queues are equal, {@code false} otherwise
     */
    
    @Override
    public boolean equals(QueueADT<E> that) {
        
        if (queue.size() != that.size()){
            return false;
        }
       Iterator<E> iterator = queue.iterator();
       Iterator<E> iterator2 = that.iterator();
       while (iterator.hasNext() && iterator2.hasNext()){
          E item1 = iterator.next();
          E item2 = iterator2.next();  
          if (item1 != item2){
              return false;
          }
       }
       return true;

    }

     /**
     * Converts the queue to an array.
     *
     * @return an array containing all elements in the queue
     */
    
    @Override
    public Object[] toArray() {

        return queue.toArray();
    }

      /**
     * Converts the queue to an array, storing the elements in the specified array.
     *
     * @param holder the array to hold the elements
     * @return the array containing the elements in the queue
     * @throws NullPointerException if the specified array is {@code null}
     */
    
    @Override
    public E[] toArray(E[] holder) throws NullPointerException {
        if (holder == null ){
            throw new NullPointerException();
            
        }
        return queue.toArray(holder);
    }

      /**
     * Checks if the queue is full.
     *
     * @return {@code true} if the queue is full, {@code false} otherwise
     */
    
    @Override
    public boolean isFull() {
        if (size == capacity){
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * Returns the current size of the queue.
     *
     * @return the size of the queue
     */
    
    @Override
    public int size() {
        return size;
    }
    

    /**
     * An iterator for the queue that allows traversal through the elements.
     */
  public class MyQueueIterator implements Iterator <E>{
          int index = 0;
          
           /**
         * Checks if there is a next element in the queue.
         *
         * @return {@code true} if there is a next element, {@code false} otherwise
         */
          
        @Override
        public boolean hasNext() {
            if(index < size ){
                return true;
            }else{
                return false;
            }

        }

        /**
         * Returns the next element in the queue.
         *
         * @return the next element
         * @throws NoSuchElementException if there are no more elements
         */
        
        @Override
        public E next() throws NoSuchElementException {
            if (hasNext()){
                
                E item = queue.get(index);
                index++;
                return item;
            }
            else{
                throw new  NoSuchElementException();
            }
        }


}
}
