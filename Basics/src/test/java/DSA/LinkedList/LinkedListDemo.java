package DSA.LinkedList;

public class LinkedListDemo {
    //Node class
    static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
            this.next = null;

        }
    }

    public static void main(String[] args) {
        //Create nodes
        Node head = new Node(10);
        head.next = new Node(20);
        head.next.next = new Node(30);

        //Traverse and print
        Node current = head;
        System.out.println("Linked List : ");
        while (current != null) {
            System.out.print(current.data + " ");
            current = current.next;
        }
        System.out.println();
    }

}

// A Linked List is a linear data structure where each element (called a node) contains:
//Data (the value)
//Reference (or link) to the next node in the sequence
//Unlike arrays, linked lists don’t store elements in contiguous memory, and their size can grow or shrink dynamically.
//Node Creation:
//Each Node object holds an integer and a reference to the next node.
//Head Node:
//head points to the first node (10).
//Connecting Nodes:
//head.next points to the second node (20), and head.next.next points to the third node (30).
//Traversal:
//Start from head, print data, move to next node until current becomes null (end of list).