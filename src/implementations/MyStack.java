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
 * @author jones
 */
public class MyStack<E> implements StackADT<E> {

    MyArrayList<E> stack;
    int size;
    int capacity;
    
    
        public MyStack(){
        this.capacity = 10;
        stack = new MyArrayList<>();
    }

    public MyStack(int capacity) {
        this.capacity = capacity;
        stack = new MyArrayList<>();
    }
    
    @Override
    public void push(E tag) throws NullPointerException {
        if (tag == null){
            throw new NullPointerException();
        }
        stack.add(0, tag);
        size++;
    }

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

    @Override
    public E peek() throws EmptyStackException {
            if (size == 0 ){
            throw new EmptyStackException();
        }
         return stack.get(0);
             
    }

    @Override
    public void clear() {
       stack.clear();
       size = 0;
    }

    @Override
    public boolean isEmpty() {
        return stack.isEmpty();
    }

    @Override
    public Object[] toArray() {
        return stack.toArray();
    }

    @Override
    public E[] toArray(E[] holder) throws NullPointerException {
        return stack.toArray(holder);
    }

    @Override
    public boolean contains(E toFind) throws NullPointerException {
        if (toFind == null){
            throw new NullPointerException();
        }
        return stack.contains(toFind);
    }

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

    @Override
    public Iterator<E> iterator() {
        return stack.iterator();
    }

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

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean stackOverflow() {
        return size >= capacity;
    }


}
