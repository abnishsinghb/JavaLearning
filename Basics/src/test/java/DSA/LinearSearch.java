package DSA;

public class LinearSearch {
    public static void main(String[] args) {
        int[] arr = {10, 20, 30, 50, 15};
        int target = 43;
        boolean found = false;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == target) {
                System.out.println("Element found at location " + i);
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("Element not present");
        }
    }
}

//Linear Search is the simplest searching algorithm:
//You start at the beginning of the array and check each element one by one
// until you find the value you’re looking for (or reach the end).
//Time Complexity: O(n) (n = number of elements).