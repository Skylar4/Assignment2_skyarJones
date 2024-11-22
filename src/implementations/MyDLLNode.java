/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package implementations;

/**
 *A node in a doubly linked list, holding an element and references to both the next and previous nodes.
 *
 * @author Marian
 */
public class MyDLLNode<E> {
    E data;
    MyDLLNode<E> next;
    MyDLLNode<E> prev;

    /**
     * Constructs a new node with the specified data.
     *
     * @param data the data to be stored in the node
     */
    public MyDLLNode(E data) {
        this.data = data;
        this.next = null;
        this.prev = null;
    }
}
