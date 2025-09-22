package DSA.Array;

public class TraverseArray {
    public static void main(String[] args) {
        int arr[] = {10, 20, 30, 40, 50};
        int len = arr.length;

        System.out.println("Using a For Loop");
        //Using a For Loop
        for (int i = 0; i < len; i++) {
            System.out.print(arr[i] + " ");
        }

        System.out.println("\nUsing While Loop");
        //Using While Loop
        int i = 0;
        while (i < len) {
            System.out.print(arr[i] + " ");
            i++;
        }
        System.out.println("\nUsing a For-Each Loop");
        // Using a For-Each Loop
        for (int num : arr) {
            System.out.print(num + " ");
        }
    }
}
