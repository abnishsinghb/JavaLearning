package DSA.Array;

public class ArrayUpdateDelete {
    public static void main(String[] args) {
        int[] arr = {10, 20, 30, 40, 50};
        System.out.println("\nThe Orginal array is :  ");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println("\nThe updated array is :  ");
        // Update index 3 to 100
        arr[3] = 100;
        for (int num : arr) {

            System.out.print(num + " ");
        }
        // Delete value at index 1 (shift left)
        for (int i = 1; i < arr.length - 1; i++) {
            arr[i] = arr[i + 1];
        }
        System.out.println("\nvalue at index[1] deleted : ");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();


        // Optional: clear last element
        arr[arr.length - 1] = 0;
        System.out.println("\nClearing the last element : ");
        for (int num : arr) {
            System.out.print(num + "  ");
        }
        System.out.println();
    }
}

//Arrays in Java (and most languages) have a fixed size, so you can't truly "delete" an element—you can only overwrite it or shift elements.
//Common ways:
//Set the value to a special marker (e.g. 0 or -1) if you don’t care about shifting.
//Shift all elements after the deleted index one position to the left.
//Example: Shifting After Deletion
//Suppose you want to remove the element at index 2 (15) from this array:
