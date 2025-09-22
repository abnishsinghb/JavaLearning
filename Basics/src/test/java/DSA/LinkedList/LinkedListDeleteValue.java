package DSA.LinkedList;



public class LinkedListDeleteValue {
    public static void main(String[] args) {
        Node head = new Node(10);
        head.next = new Node(20);
        head.next.next = new Node(30);

        int deleteValue =20;
        Node curr = head, prev = null;
        while(curr!=null && curr.data!=deleteValue){
            prev=curr;
            curr=curr.next;
        }
        if(curr!=null){  // Found node to delete
            if(prev==null){
                head = curr.next; // Deleting head
            }
            else{
                prev.next = curr.next;
            }
        }
        // Traverse and print
        curr = head;
        while (curr!=null){
            System.out.println(curr.data+ " ");
            curr=curr.next;
        }
    }
}
