package DSA.Array;

public class ArrayDemo {
    public static void main(String[] args) {
        // method 1
        int a[] = {10, 20, 30, 40, 50};  // method 1

        // Method 2
    /*    int[] a = new int[5];
        a[0]=10;
        a[1]=20;
        a[2]=30;
        a[3]=40;
        a[4]=50;*/

        for (int i = 0; i < a.length; i++) {
            System.out.println(+a[i]);
        }
    }
}

// An array is a collection of elements, all of the same type, stored in a contiguous block of memory.
// Each element can be accessed by its index (position in the array).
//Arrays are fixed in size.
//Indexing starts from 0 in most programming languages, including Java.
//Visualization: