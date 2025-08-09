package Collections.ArrayList;

// A resizable array (dynamic array) from the java.util package.
//
//Maintains insertion order.
//
//Allows duplicate elements.
//
//Provides index-based access (like arrays).

import java.util.ArrayList;

public class ArraylistExample {
    public static void main(String[] args) {
        // Step 1: Create an ArrayList of Strings
        ArrayList<String> fruits = new ArrayList<>();

        // Step 2: Add elements
        fruits.add("Apple");
        fruits.add("Banana");
        fruits.add("Orange");
        fruits.add("Banana"); // allows duplicate

        // Step 3: Print the ArrayList
        System.out.println("Fruits list is : " + fruits);

        // Step 4: Get element at index 2
        System.out.println("Fruit at index 2 : " + fruits.get(2));

        // Step 5: Remove element by index
        fruits.remove(2);

        // Step 6: Print after removal

        System.out.println("Fruit list : " + fruits);

        // Step 7: Check if it contains "Apple"
        if (fruits.contains("Apple")) {
            System.out.println(" Apple is in list");
        }

        // Step 8: Loop through all items
        System.out.println(" Final list of fruits are : ");
        for (String fruit : fruits) {
            System.out.println(fruit);
        }
    }
}
