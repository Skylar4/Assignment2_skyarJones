package implementations;
import java.util.NoSuchElementException;
import utilities.Iterator;
import utilities.ListADT;


/**
 *
 * @author Marian Estrada
 * @param <Integer>
 */

/**
 * Implementation of a dynamic array-based list.
 * This class implements the ListADT interface using a resizable array as the underlying data structure.
 *
 * @param <E> The type of elements stored in the list.
 */


public class MyArrayList<E> implements ListADT<E> {
    private E[] array;
    private int size;
    private static final int INITIAL_CAPACITY = 10;

     /**
     * Constructs an empty list with an initial capacity.
     */
    
    @SuppressWarnings("unchecked")
    public MyArrayList() {
        array = (E[]) new Object[INITIAL_CAPACITY];
        size = 0;
    }

     /**
     * Returns the number of elements in the list.
     *
     * @return The size of the list.
     */
    
    @Override
    public int size() {
        return size;
    }

    
    /**
     * Clears the list, removing all elements.
     */
    
    @Override
    public void clear() {
        array = (E[]) new Object[INITIAL_CAPACITY];
        size = 0;
    }

     /**
     * Adds an element at the specified index in the list.
     *
     * @param index  The position to add the element.
     * @param toAdd  The element to add.
     * @return True if the element is added successfully.
     * @throws NullPointerException      If the element to add is null.
     * @throws IndexOutOfBoundsException If the index is out of bounds.
     */
    
    @Override
    public boolean add(int index, E toAdd) throws NullPointerException, IndexOutOfBoundsException {
        if (toAdd == null) throw new NullPointerException("Element cannot be null");
        if (index < 0 || index > size) throw new IndexOutOfBoundsException("Index out of bounds");

        ensureCapacity();
        System.arraycopy(array, index, array, index + 1, size - index);
        array[index] = toAdd;
        size++;
        return true;
    }

    /**
     * Adds an element to the end of the list.
     *
     * @param toAdd The element to add.
     * @return True if the element is added successfully.
     * @throws NullPointerException If the element to add is null.
     */
    
    @Override
    public boolean add(E toAdd) throws NullPointerException {
        if (toAdd == null) throw new NullPointerException("Element cannot be null");

        ensureCapacity();
        array[size++] = toAdd;
        return true;
    }
    
    /**
     * Adds all elements from another list to this list.
     *
     * @param toAdd The list of elements to add.
     * @return True if the elements are added successfully.
     * @throws NullPointerException If the list to add is null.
     */
    
    @Override
    public boolean addAll(ListADT<? extends E> toAdd) throws NullPointerException {
        if (toAdd == null) throw new NullPointerException("Collection cannot be null");

        Iterator<? extends E> iterator = toAdd.iterator();
        while (iterator.hasNext()) {
            add(iterator.next());
        }
        return true;
    }
    
    
     /**
     * Returns the element at the specified index.
     *
     * @param index The position of the element to retrieve.
     * @return The element at the specified index.
     * @throws IndexOutOfBoundsException If the index is out of bounds.
     */
    
    @Override
    public E get(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException("Index out of bounds");
        return array[index];
    }

    /**
     * Removes the element at the specified index.
     *
     * @param index The position of the element to remove.
     * @return The removed element.
     * @throws IndexOutOfBoundsException If the index is out of bounds.
     */
    
    @Override
    public E remove(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException("Index out of bounds");

        E removedElement = array[index];
        System.arraycopy(array, index + 1, array, index, size - index - 1);
        array[--size] = null;
        return removedElement;
    }
    
     /**
     * Removes the first occurrence of the specified element from the list.
     *
     * @param toRemove The element to remove.
     * @return The removed element, or null if not found.
     * @throws NullPointerException If the element to remove is null.
     */

    @Override
    public E remove(E toRemove) throws NullPointerException {
        if (toRemove == null) throw new NullPointerException("Element cannot be null");

        for (int i = 0; i < size; i++) {
            if (array[i].equals(toRemove)) {
                return remove(i);
            }
        }
        return null;
    }

      /**
     * Replaces the element at the specified index with the given element.
     *
     * @param index    The position of the element to replace.
     * @param toChange The new element.
     * @return The old element at the specified index.
     * @throws NullPointerException      If the new element is null.
     * @throws IndexOutOfBoundsException If the index is out of bounds.
     */
    
    @Override
    public E set(int index, E toChange) throws NullPointerException, IndexOutOfBoundsException {
        if (toChange == null) throw new NullPointerException("Element cannot be null");
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException("Index out of bounds");

        E oldElement = array[index];
        array[index] = toChange;
        return oldElement;
    }

    /**
     * Checks if the list is empty.
     *
     * @return True if the list is empty, false otherwise.
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Checks if the list contains the specified element.
     *
     * @param toFind The element to search for.
     * @return True if the element is found, false otherwise.
     * @throws NullPointerException If the element to search for is null.
     */
    
    @Override
    public boolean contains(E toFind) throws NullPointerException {
        if (toFind == null) throw new NullPointerException("Element found in current list and should not be found.");

    for (int i = 0; i < size; i++) {
        if (array[i] != null && array[i].equals(toFind)) {
            return true;
        }
    }
    return false;
    }

     /**
     * Converts the list into an array of the same type as the provided array.
     *
     * @param toHold The array to hold the elements.
     * @return An array containing the elements of the list.
     * @throws NullPointerException If the provided array is null.
     */
    @Override
    public E[] toArray(E[] toHold) throws NullPointerException {
        if (toHold == null) throw new NullPointerException("Array cannot be null");

        if (toHold.length < size) {
            return (E[]) java.util.Arrays.copyOf(array, size, toHold.getClass());
        }
        System.arraycopy(array, 0, toHold, 0, size);
        if (toHold.length > size) {
            toHold[size] = null;
        }
        return toHold;
    }

       /**
     * Converts the list into an Object array.
     *
     * @return An array containing the elements of the list.
     */
    @Override
    public Object[] toArray() {
        return java.util.Arrays.copyOf(array, size);
    }

     /**
     * Returns an iterator over the elements in the list.
     *
     * @return An iterator.
     */
    
    @Override
    public Iterator<E> iterator() {
        return new MyArrayListIterator();
    }

      /**
     * Ensures that the array has sufficient capacity to store additional elements.
     */
    
    private void ensureCapacity() {
        if (size == array.length) {
            array = java.util.Arrays.copyOf(array, array.length * 2);
        }
    }

      /**
     * Iterator for the MyArrayList class.
     */
    
    private class MyArrayListIterator implements Iterator<E> {
        private int currentIndex = 0;

         /**
         * Checks if there are more elements to iterate.
         *
         * @return True if there are more elements, false otherwise.
         */
        @Override
        public boolean hasNext() {
            return currentIndex < size;
        }

         /**
         * Returns the next element in the iteration.
         *
         * @return The next element.
         * @throws NoSuchElementException If there are no more elements.
         */
        @Override
        public E next() throws NoSuchElementException {
            if (!hasNext()) throw new NoSuchElementException("No more elements");
            return array[currentIndex++];
        }
    }
}