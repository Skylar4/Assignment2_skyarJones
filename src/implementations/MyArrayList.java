package implementations;
import java.util.NoSuchElementException;
import utilities.Iterator;
import utilities.ListADT;


/**
 *
 * @author Marian Estrada
 * @param <Integer>
 */
public class MyArrayList<E> implements ListADT<E> {
    private E[] array;
    private int size;
    private static final int INITIAL_CAPACITY = 10;

    @SuppressWarnings("unchecked")
    public MyArrayList() {
        array = (E[]) new Object[INITIAL_CAPACITY];
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        array = (E[]) new Object[INITIAL_CAPACITY];
        size = 0;
    }

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

    @Override
    public boolean add(E toAdd) throws NullPointerException {
        if (toAdd == null) throw new NullPointerException("Element cannot be null");

        ensureCapacity();
        array[size++] = toAdd;
        return true;
    }

@Override
public boolean addAll(ListADT<? extends E> toAdd) throws NullPointerException {
    if (toAdd == null) throw new NullPointerException("Collection cannot be null");

    Iterator<? extends E> iterator = toAdd.iterator();
    while (iterator.hasNext()) {
        add(iterator.next());
    }
    return true;
}
    @Override
    public E get(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException("Index out of bounds");
        return array[index];
    }

    @Override
    public E remove(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException("Index out of bounds");

        E removedElement = array[index];
        System.arraycopy(array, index + 1, array, index, size - index - 1);
        array[--size] = null;
        return removedElement;
    }

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

    @Override
    public E set(int index, E toChange) throws NullPointerException, IndexOutOfBoundsException {
        if (toChange == null) throw new NullPointerException("Element cannot be null");
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException("Index out of bounds");

        E oldElement = array[index];
        array[index] = toChange;
        return oldElement;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

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

    @Override
    public Object[] toArray() {
        return java.util.Arrays.copyOf(array, size);
    }

    @Override
    public Iterator<E> iterator() {
        return new MyArrayListIterator();
    }

    private void ensureCapacity() {
        if (size == array.length) {
            array = java.util.Arrays.copyOf(array, array.length * 2);
        }
    }

    private class MyArrayListIterator implements Iterator<E> {
        private int currentIndex = 0;

        @Override
        public boolean hasNext() {
            return currentIndex < size;
        }

        @Override
        public E next() throws NoSuchElementException {
            if (!hasNext()) throw new NoSuchElementException("No more elements");
            return array[currentIndex++];
        }
    }
}