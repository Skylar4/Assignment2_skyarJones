/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utilities;

/**
 *
 * @author jones
 */
public class myArrayList<E> implements ListADT {

    private E[] data;
    int size;

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        data = null;
    }

    @Override
    public boolean add(int index, Object toAdd) throws NullPointerException, IndexOutOfBoundsException {
        if (toAdd == null) {
            throw new NullPointerException("object cannot be null");
        }
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("index is out of bounds");
        }
        //creating a new temp array that is 1 larger than the origional array 
        Object[] tempArray = new Object[data.length + 1];
        //copying over the data from the data array to the temp
        //starting at index 0 and pasting it into the temp array at index 0
        //stopping at the specidifed index where we will be adding the new element
        System.arraycopy(data, 0, tempArray, 0, index);
        //adding in the new element at the index specified
        tempArray[index] = toAdd;
        //then copying over the remaining data.
        //starting from the index and pasting it at index + 1 since index now has our new element
        // and we will stop at size- index to grab all the remaining data.
        System.arraycopy(data, index, tempArray, index + 1, size - index);
        //assigning data to point at the temp array and casting it to match the data type E
        data = (E[]) tempArray;
        size++;
        return true;
    }

    @Override
    public boolean add(Object toAdd) throws NullPointerException {

        if (toAdd == null) {
            throw new NullPointerException("object cannot be null");
        }
        //creating a new temp array that is 1 larger to hold the new object
        Object[] tempArray = new Object[data.length + 1];  
        //copy over the data to the new temp array 
       System.arraycopy(data, 0, tempArray, 0, size);
       //add the new element to the back of the array
        tempArray[tempArray.length-1] = toAdd;
        //assign data the new temp array casting to type E
        data = (E[]) tempArray;
        size++;
        
        return true;
    }

    @Override
    public boolean addAll(ListADT toAdd) throws NullPointerException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Object get(int index) throws IndexOutOfBoundsException {
          if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("index is out of bounds");
        }
        return data[index];
    }

    @Override
    public Object remove(int index) throws IndexOutOfBoundsException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Object remove(Object toRemove) throws NullPointerException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Object set(int index, Object toChange) throws NullPointerException, IndexOutOfBoundsException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean isEmpty() {
        if (size == 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean contains(Object toFind) throws NullPointerException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Object[] toArray(Object[] toHold) throws NullPointerException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Object[] toArray() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Iterator iterator() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
