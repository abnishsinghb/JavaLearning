package DSA.LinkedList;

public class LinkedListSearch {
    public static void main(String[] args) {
        Node head = new Node(10);
        head.next = new Node(20);
        head.next.next = new Node(30);

        int searchValue = 20;
        Node curr = head;
        boolean found = false;
        while (curr!=null){
            if(curr.data==searchValue){
                found = true;
                break;
            }
            curr = curr.next;
        }
        if(found)
            System.out.println(searchValue+ "is found ");
        else
            System.out.println(searchValue+ "is not found");

    }
}
