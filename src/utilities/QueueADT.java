package utilities;

import exceptions.EmptyQueueException;
import java.io.*;

/**
 * This is the professional Queue Interface for Object-Oriented Programming 3
 * (CRPG 304) at the SAIT Polytechnic. This Queue embodies all the standard
 * Queue operations, and includes several helper methods that will give the data
 * structure more flexibility and use.
 */
public interface QueueADT<E> extends Serializable
{
	 /**
        * Enqueue will place the added item at the last position in the queue. This
        * method will not allow <code>null</code> values to be added to the Queue.
        * 
        * @param toAdd the item to be added to the Queue.
        * @throws NullPointerException raised when a <code>null</code> object is placed
        *                              in the Queue.
        * @pre toAdd must not be <code>null</code>.
        * @post The element <code>toAdd</code> is added to the end of the queue, 
        *       and the queue's size increases by 1.
        */
	public void enqueue( E toAdd ) throws NullPointerException;

	 /**
        * Dequeue will remove the first item that was placed in the Queue.
        * 
        * @return the first item in the Queue.
        * @throws EmptyQueueException raised when the queue's length is zero (0).
        * @pre The queue must not be empty (i.e., the size must be greater than 0).
        * @post The first item is removed from the queue, and the queue's size decreases by 1.
        */
	public E dequeue() throws EmptyQueueException;

	/**
        * Peek provides a reference to the first item in the queue without removing
        * it from the queue.
        * 
        * @return the first item in the queue.
        * @throws EmptyQueueException raised when the queue's length is zero (0).
        * @pre The queue must not be empty (i.e., the size must be greater than 0).
        * @post The first item is returned without modifying the queue.
        */
	public E peek() throws EmptyQueueException;
	
	/**
        * DequeueAll removes all items in the queue.
        * 
        * @pre None.
        * @post All items are removed from the queue, and the queue becomes empty.
        */
	public void dequeueAll();

	/**
        * Returns <code>true</code> when the queue contains no items.
        * 
        * @return <code>true</code> when queue length is zero (0).
        * @pre None.
        * @post Returns <code>true</code> if the queue is empty, otherwise <code>false</code>.
        */
	public boolean isEmpty();
	
	/**
        * Returns true if this list contains the specified element.
        * 
        * @param toFind the element whose presence in this list is to be tested.
        * @return <code>true</code> if this list contains the specified element.
        * @throws NullPointerException if the specified element is <code>null</code> and this list does not
        *                               support <code>null</code> elements.
        * @pre toFind must not be <code>null</code>.
        * @post Returns <code>true</code> if <code>toFind</code> is in the queue, otherwise <code>false</code>.
        */
	public boolean contains( E toFind ) throws NullPointerException;

	/**
	 * Returns the 1-based position where an object is on this queue. If the
	 * object o occurs as an item in this queue, this method returns the
	 * distance from the front of the queue of the occurrence nearest the front of
	 * the queue; the first item on the stack is considered to be at distance
	 * 1. The equals method is used to compare o to the items in this queue.
	 * 
         * @pre toFind must not be <code>null</code>.
         * @post Returns the 1-based index of <code>toFind</code> in the queue, or -1 if not found.
         * 
	 * @param toFind
	 *            the desired object.
	 * @return the 1-based position from the top of the queue where the object
	 *         is located; the return value -1 indicates that the object is not
	 *         on the queue.
	 */
	public int search( E toFind );

	
        /**
         * Returns an iterator over the elements in this queue in proper sequence.
         * 
         * @return an iterator over the elements in this queue.
         * @pre None.
         * @post Returns an iterator to traverse the elements in the queue.
         */
	public Iterator<E> iterator();

	/**
        * Used to compare two Queue ADTs. To be equal, two queues must contain equal
        * items appearing in the same order.
        * 
        * @param that the Queue ADT to be compared to this queue.
        * @return <code>true</code> if the queues are equal.
        * @pre <code>that</code> must not be <code>null</code>.
        * @post Returns <code>true</code> if both queues are equal, otherwise <code>false</code>.
        */
	public boolean equals( QueueADT<E> that );

	 /**
        * Returns an array containing all of the elements in this queue in proper
        * sequence.
        * 
        * @return an array containing all elements in the queue in proper sequence.
        * @pre None.
        * @post Returns an array representation of the queue elements in order.
        */
	public Object[] toArray();

	/**
        * Returns an array containing all of the elements in this queue in proper
        * sequence; the runtime type of the returned array is that of the specified
        * array.
        * 
        * @param toHold the array into which the elements of this queue are to be
        *               stored.
        * @return an array containing the elements of this queue.
        * @throws NullPointerException if the specified array is <code>null</code>.
        * @pre <code>toHold</code> must not be <code>null</code>.
        * @post Returns an array representation of the queue elements in order, stored in <code>toHold</code>.
        */
	public E[] toArray( E[] holder ) throws NullPointerException;

	 /**
        * Returns true if the number of items in the queue equals the capacity.
        * 
        * @return <code>true</code> if queue is at capacity.
        * @pre The queue must have a fixed capacity.
        * @post Returns <code>true</code> if the queue is full, otherwise <code>false</code>.
        */
	public boolean isFull();

	/**
        * Returns the length of the current queue.
        * 
        * @return the current size of the queue.
        * @pre None.
        * @post Returns the current number of elements in the queue.
        */
	public int size();
}
