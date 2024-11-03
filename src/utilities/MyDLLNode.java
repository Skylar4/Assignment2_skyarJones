/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.MyDLLNode to edit this template
 */
package utilities;

/**
 *
 * @author Marian Estrada
 */
public class MyDLLNode<E> {
    E data;
    MyDLLNode<E> next;
    MyDLLNode<E> prev;

    public MyDLLNode(E data) {
        this.data = data;
        this.next = null;
        this.prev = null;
    }
}