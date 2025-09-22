package DSA.LinkedList;

public class LinkedListInsertEnd {
    static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    public static void main(String[] args) {
        Node head = new Node(10);
        head.next = new Node(20);

        //Insert at the end 30
        Node current = head;
        while (current.next != null) {
            current = current.next;
        }
        current.next = new Node(30);

        //Traverse and Print

        current = head;
        System.out.println(" Linked list Insertion at end ");
        while (current != null) {
            System.out.println(current.data + " ");
            current = current.next;
        }
    }
}
