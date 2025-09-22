package DSA.LinkedList;

class Node {
    int data;
    Node next;

    Node(int data) {
        this.data = data;
    }
}

public class LinkedListInsertBegin {
    public static void main(String[] args) {
        Node head = new Node(10);
        head.next = new Node(20);

        //insert 5 at the beginning
        Node newNode = new Node(5);
        newNode.next = head;
        head = newNode;

        //Traverse and print

        Node current = head;
        System.out.println("Linked list is :");
        while (current != null) {
            System.out.println(current.data + " ");
            current = current.next;
        }
    }
}
