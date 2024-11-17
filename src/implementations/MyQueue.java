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
 *
 * @author jones
 */
public class MyQueue<E> implements QueueADT<E> {
    
    MyDLL<E> queue;
    int size;
    int capacity;

    
    public MyQueue(){
        this.capacity = 10;
        queue = new MyDLL<>();
    }


    
    public MyQueue(int capacity) {
        this.capacity = capacity;
        queue = new MyDLL<>();
    }
    

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

    @Override
    public E peek() throws EmptyQueueException {
         if (isEmpty()){
           throw new EmptyQueueException();
       } 
       return queue.get(0);
    }

    @Override
    public void dequeueAll() {
        queue.clear();
        size = 0;
    }

    @Override
    public boolean isEmpty() {
        return size <= 0;
    }

    @Override
    public boolean contains(E toFind) throws NullPointerException {
      if (toFind == null){
           throw new NullPointerException();
      }
      return queue.contains(toFind);
    }

    @Override
    public int search(E toFind) {

               throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Iterator<E> iterator() {
        return new MyQueueIterator();
    }

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

    @Override
    public Object[] toArray() {

        return queue.toArray();
    }

    @Override
    public E[] toArray(E[] holder) throws NullPointerException {
        if (holder == null ){
            throw new NullPointerException();
            
        }
        return queue.toArray(holder);
    }

    @Override
    public boolean isFull() {
        if (size == capacity){
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public int size() {
        return size;
    }
    

  public class MyQueueIterator implements Iterator <E>{
          int index = 0;
        @Override
        public boolean hasNext() {
            if(index < size ){
                return true;
            }else{
                return false;
            }

        }

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
