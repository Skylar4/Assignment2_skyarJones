package utilities;

import java.util.*;
import java.io.*;

/**
 * This is the professional Stack Interface for Object-Oriented Programming 3
 * (CRPG 304) at the SAIT Polytechnic. This Stack embodies all the standard
 * Stack operations, and includes several helper methods that will give the data
 * structure more flexibility and use.
 */
public interface StackADT<E> extends Serializable
{

	/**
        * Pushes an item onto the top of this stack.
        * 
        * @param toAdd item to be pushed onto the top of the stack.
        * @throws NullPointerException when attempting to add a null element to the
        *                              stack.
        * @pre <code>toAdd</code> must not be <code>null</code>.
        * @post The item <code>toAdd</code> is added to the top of the stack, and the stack's size increases by 1.
        */
	public void push( E toAdd ) throws NullPointerException;

	/**
        * Removes the object at the top of this stack and returns that object as the
        * value of this function.
        * 
        * @return the item popped off the top of the stack.
        * @throws EmptyStackException if there are no items in the stack.
        * @pre The stack must not be empty (i.e., it must have at least 1 item).
        * @post The top item is removed from the stack, and the stack's size decreases by 1.
        */
	public E pop() throws EmptyStackException;

	 /**
        * Looks at the object at the top of this stack without removing it from the
        * stack.
        * 
        * @return the object at the top of this stack.
        * @throws EmptyStackException if the stack is empty.
        * @pre The stack must not be empty (i.e., it must have at least 1 item).
        * @post The top item is returned without removing it from the stack.
        */
	public E peek() throws EmptyStackException;

	 /**
        * Clears all the items from this Stack. This method returns unless there is an
        * Exception (Runtime) thrown.
        * 
        * @pre None.
        * @post All items are removed from the stack, and the stack becomes empty.
        */
	public void clear();

	 /**
        * Returns <code>true</code> if this Stack contains no items.
        * 
        * @return <code>true</code> if this Stack contains no items.
        * @pre None.
        * @post Returns <code>true</code> if the stack is empty, otherwise <code>false</code>.
        */
	public boolean isEmpty();

	 /**
        * Returns an array containing all of the elements in this list in proper
        * sequence. Obeys the general contract of the Collection.toArray method.
        * 
        * @return an array containing all of the elements in this list in proper
        *         sequence.
        * @pre None.
        * @post Returns an array representation of the stack's elements in order from top to bottom.
        */
	public Object[] toArray();

	 /**
        * Returns an array containing all of the elements in this list in proper
        * sequence; the runtime type of the returned array is that of the specified
        * array. Obeys the general contract of the Collection.toArray(Object[]) method.
        * 
        * @param toHold the array into which the elements of this stack are to be
        *               stored, if it is big enough; otherwise, a new array of the same
        *               runtime type is allocated for this purpose.
        * @return an array containing the elements of this stack.
        * @throws NullPointerException if the specified array is null.
        * @pre <code>toHold</code> must not be <code>null</code>.
        * @post Returns an array representation of the stack's elements in order from top to bottom, stored in <code>toHold</code>.
        */
	public E[] toArray( E[] holder ) throws NullPointerException;

	 /**
        * Returns true if this list contains the specified element. More formally,
        * returns true if and only if this list contains at least one element e such
        * that (o==null ? e==null : o.equals(e)).
        * 
        * @param toFind element whose presence in this list is to be tested.
        * @return true if this list contains the specified element.
        * @throws NullPointerException if the specified element is null and this list
        *                              does not support null elements.
        * @pre <code>toFind</code> must not be <code>null</code>.
        * @post Returns <code>true</code> if the stack contains <code>toFind</code>, otherwise <code>false</code>.
        */
	public boolean contains( E toFind ) throws NullPointerException;

	/**
        * Returns the 1-based position where an object is on this stack. If the object
        * o occurs as an item in this stack, this method returns the distance from the
        * top of the stack of the occurrence nearest the top of the stack; the topmost
        * item on the stack is considered to be at distance 1. The equals method is
        * used to compare o to the items in this stack.
        * 
        * @param toFind the desired object.
        * @return the 1-based position from the top of the stack where the object is
        *         located; the return value -1 indicates that the object is not on the
        *         stack.
        * @pre <code>toFind</code> must not be <code>null</code>.
        * @post Returns the 1-based index of <code>toFind</code> in the stack, or -1 if not found.
        */
	public int search( E toFind );

	/**
        * Returns an iterator over the elements in this stack in proper sequence.
        * 
        * @return an iterator over the elements in this stack in proper sequence.
        * @pre None.
        * @post Returns an iterator to traverse the elements in the stack from top to bottom.
        */
	public Iterator<E> iterator();

	/**
        * Returns an iterator over the elements in this stack in proper sequence.
        * 
        * @return an iterator over the elements in this stack in proper sequence.
        * @pre None.
        * @post Returns an iterator to traverse the elements in the stack from top to bottom.
        */
	public boolean equals( StackADT<E> that );

	 /**
        * Returns the depth of the current stack as an integer value.
        * 
        * @return the current size of the stack as an integer.
        * @pre None.
        * @post Returns the current number of elements in the stack.
        */
	public int size();
	
	 /**
        * Returns true if the number of items in the stack equals the length.  
        * This operation is only implemented when a fixed-size stack is required.
        * 
        * @return <code>true</code> if stack is at capacity.
        * @pre The stack must have a fixed capacity.
        * @post Returns <code>true</code> if the stack is full, otherwise <code>false</code>.
        */
	public boolean stackOverflow();
}