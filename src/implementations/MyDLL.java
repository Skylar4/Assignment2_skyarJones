/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package implementations;

import java.util.NoSuchElementException;
import utilities.Iterator;
import utilities.ListADT;

/**
 * A doubly linked list implementation of the {@link ListADT} interface.
 * This class provides methods for adding, removing, retrieving, and 
 * manipulating elements in a doubly linked list structure.
 *
 * @param <E> the type of elements in this list
 * @author Marian
 */

public class MyDLL<E> implements ListADT<E> {

    private MyDLLNode<E> head;
    private MyDLLNode<E> tail;
    private int size;

     /**
     * Constructs an empty doubly linked list.
     */
    public MyDLL() {
        head = null;
        tail = null;
        size = 0;
    }

      /**
     * Returns the number of elements in this list.
     *
     * @return the size of the list
     */
    @Override
    public int size() {
        return size;
    }

     /**
     * Removes all elements from this list, making it empty.
     */
    @Override
    public void clear() {
        head = null;
        tail = null;
        size = 0;
    }

     /**
     * Adds an element at the specified index in the list.
     *
     * @param index the index at which the element is to be inserted
     * @param toAdd the element to be added
     * @return {@code true} if the element is added successfully
     * @throws NullPointerException     if the specified element is {@code null}
     * @throws IndexOutOfBoundsException if the index is out of range
     */
    
    @Override
    public boolean add(int index, E toAdd) throws NullPointerException, IndexOutOfBoundsException {
        if (toAdd == null) {
            throw new NullPointerException("Element cannot be null");
        }
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }

        MyDLLNode<E> newNode = new MyDLLNode<>(toAdd);
        if (index == 0) {
            if (head == null) {
                head = newNode;
                tail = newNode;
            } else {
                newNode.next = head;
                head.prev = newNode;
                head = newNode;
            }
        } else if (index == size) {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        } else {
            MyDLLNode<E> current = getNode(index);
            newNode.next = current;
            newNode.prev = current.prev;
            current.prev.next = newNode;
            current.prev = newNode;
        }
        size++;
        return true;
    }

      /**
     * Appends the specified element to the end of this list.
     *
     * @param toAdd the element to be added
     * @return {@code true} if the element is added successfully
     * @throws NullPointerException if the specified element is {@code null}
     */
    
    @Override
    public boolean add(E toAdd) throws NullPointerException {
        if (toAdd == null) {
            throw new NullPointerException("Element cannot be null");
        }

        MyDLLNode<E> newNode = new MyDLLNode<>(toAdd);
        if (tail == null) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
        size++;
        return true;
    }

    /**
     * Appends all elements from the specified collection to this list.
     *
     * @param toAdd the collection containing elements to be added
     * @return {@code true} if all elements are added successfully
     * @throws NullPointerException if the specified collection is {@code null}
     */
    
    @Override
    public boolean addAll(ListADT<? extends E> toAdd) throws NullPointerException {
        if (toAdd == null) {
            throw new NullPointerException("Collection cannot be null");
        }

        Iterator<? extends E> iterator = toAdd.iterator();
        while (iterator.hasNext()) {
            add(iterator.next());
        }
        return true;
    }

    /**
     * Retrieves the element at the specified index in this list.
     *
     * @param index the index of the element to retrieve
     * @return the element at the specified index
     * @throws IndexOutOfBoundsException if the index is out of range
     */
    @Override
    public E get(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        return getNode(index).data;
    }

     /**
     * Removes the element at the specified index from this list.
     *
     * @param index the index of the element to remove
     * @return the removed element
     * @throws IndexOutOfBoundsException if the index is out of range
     */
    @Override
    public E remove(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }

        MyDLLNode<E> nodeToRemove = getNode(index);
        if (nodeToRemove.prev != null) {
            nodeToRemove.prev.next = nodeToRemove.next;
        } else {
            head = nodeToRemove.next;
        }
        if (nodeToRemove.next != null) {
            nodeToRemove.next.prev = nodeToRemove.prev;
        } else {
            tail = nodeToRemove.prev;
        }
        size--;
        return nodeToRemove.data;
    }

     /**
     * Removes the first occurrence of the specified element from this list.
     *
     * @param toRemove the element to be removed
     * @return the removed element, or {@code null} if not found
     * @throws NullPointerException if the specified element is {@code null}
     */
    @Override
    public E remove(E toRemove) throws NullPointerException {
        if (toRemove == null) {
            throw new NullPointerException("Element cannot be null");
        }

        MyDLLNode<E> current = head;
        while (current != null) {
            if (current.data.equals(toRemove)) {
                if (current.prev != null) {
                    current.prev.next = current.next;
                } else {
                    head = current.next;
                }
                if (current.next != null) {
                    current.next.prev = current.prev;
                } else {
                    tail = current.prev;
                }
                size--;
                return current.data;
            }
            current = current.next;
        }
        return null;
    }

     /**
     * Replaces the element at the specified index with the specified element.
     *
     * @param index the index of the element to replace
     * @param toChange the new element to be set
     * @return the element previously at the specified index
     * @throws NullPointerException     if the specified element is {@code null}
     * @throws IndexOutOfBoundsException if the index is out of range
     */
    
    @Override
    public E set(int index, E toChange) throws NullPointerException, IndexOutOfBoundsException {
        if (toChange == null) {
            throw new NullPointerException("Element cannot be null");
        }
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }

        MyDLLNode<E> node = getNode(index);
        E oldData = node.data;
        node.data = toChange;
        return oldData;
    }

    /**
     *
     * @return
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

      /**
     * Checks if the list contains the specified element.
     *
     * @param toFind the element to search for
     * @return {@code true} if the list contains the element, {@code false} otherwise
     * @throws NullPointerException if the specified element is {@code null}
     */
    @Override
    public boolean contains(E toFind) throws NullPointerException {
        if (toFind == null) {
            throw new NullPointerException("Element cannot be null");
        }

        MyDLLNode<E> current = head;
        while (current != null) {
            if (current.data.equals(toFind)) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    /**
    * Converts the list to an array.
    *
    * @param toHold the array to hold the elements of the list
    * @return an array containing all elements in this list
    * @throws NullPointerException if the specified array is {@code null}
    */
    @Override
    public E[] toArray(E[] toHold) throws NullPointerException {
        if (toHold == null) {
            throw new NullPointerException("Array cannot be null");
        }

        if (toHold.length < size) {
            toHold = (E[]) java.lang.reflect.Array.newInstance(toHold.getClass().getComponentType(), size);
        }
        int i = 0;
        MyDLLNode<E> current = head;
        while (current != null) {
            toHold[i++] = current.data;
            current = current.next;
        }
        if (toHold.length > size) {
            toHold[size] = null;
        }
        return toHold;
    }

    /**
    * Converts the list to an array.
    *
    * @return an array containing all elements in this list
    */
    @Override
    public Object[] toArray() {
        Object[] array = new Object[size];
        int i = 0;
        MyDLLNode<E> current = head;
        while (current != null) {
            array[i++] = current.data;
            current = current.next;
        }
        return array;
    }

    /**
    * Returns an iterator over the elements in the list.
    *
    * @return an iterator for the list
    */
    @Override
    public Iterator<E> iterator() {
        return new MyDLLIterator();
    }

    /**
    * Retrieves the node at the specified index.
    *
    * @param index the index of the node to retrieve
    * @return the node at the specified index
    */
    private MyDLLNode<E> getNode(int index) {
        MyDLLNode<E> current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current;
    }

    /**
    * Iterator implementation for the doubly linked list.
    */
    
    private class MyDLLIterator implements Iterator<E> {

        private MyDLLNode<E> current = head;

         /**
        * Checks if there are more elements to iterate over.
        *
        * @return {@code true} if there are more elements, {@code false} otherwise
        */
        @Override
        public boolean hasNext() {
            return current != null;
        }

         /**
        * Returns the next element in the iteration.
        *
        * @return the next element in the list
        * @throws NoSuchElementException if no more elements exist
        */
        @Override
        public E next() throws NoSuchElementException {
            if (!hasNext()) {
                throw new NoSuchElementException("No more elements");
            }
            E data = current.data;
            current = current.next;
            return data;
        }
    }
}
