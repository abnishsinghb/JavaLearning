package Practise.collection;

//| Step | Code Part                   | Explanation                               |
//| ---- | --------------------------- | ----------------------------------------- |
//| 1    | `Map<String, Integer>`      | Stores name → score                       |
//| 2    | `entrySet()` to `ArrayList` | Convert to list for sorting               |
//| 3    | `entryList.sort(...)`       | Sort using `Map.Entry.comparingByValue()` |
//| 4    | Print sorted list           | Sorted based on scores (values)           |
//This Java program demonstrates a very common and practical task: sorting the elements of a Map based on their values. It's important to understand that
// HashMaps themselves do not guarantee any order (neither insertion order nor sorted order by key or value). Therefore, to sort a map, you typically
// extract its entries into a List and then sort that List.

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SortMapByValues {
    public static void main(String[] args) {

        // Step 1: Create and populate map
        Map<String, Integer> scores = new HashMap<>(); // We declare a variable scores of type Map<String, Integer>. This indicates that the map will store
        // String keys (e.g., names) and Integer values (e.g., scores).
        scores.put("Alice", 75);   // We add four key-value pairs (entries) to the scores map.
        scores.put("Bob", 85);
        scores.put("Charlie", 95);
        scores.put("David", 65);

        System.out.println("Original Map : ");
        for (Map.Entry<String, Integer> entry : scores.entrySet()) {  // This method returns a Set of Map.Entry<String, Integer> objects. Each Map.Entry
            // object represents a single key-value pair ("Alice" -> 85, "Bob" -> 75, etc.).
            // We use an enhanced for loop to iterate through each entry in the entrySet.
            System.out.println(entry.getKey() + " --> " + entry.getValue()); // We use an enhanced for loop to iterate through each entry in the entrySet.
            // Retrieves the value (Integer score) for the current entry.
        }

        // Step 2: Convert entrySet to a list
        List<Map.Entry<String, Integer>> entryList = new ArrayList<>(scores.entrySet());

        // Step 3: Sort list by values using comparator
        entryList.sort(Map.Entry.comparingByValue()); // We create a new ArrayList and initialize it by passing the scores.entrySet() to its constructor.
        // This effectively copies all the key-value pairs from the scores map into our new entryList
        // By default, comparingByValue() uses the natural ordering of the values. Since our values are Integers, it will sort them in ascending numerical
        // order (75, 80, 85, 90).

        // Step 4: Print sorted result
        System.out.println("\n Map sorted by values in Ascending order");
        {
            for (Map.Entry<String, Integer> entry : entryList) { // We iterate through the now sorted entryList
                System.out.println(entry.getKey() + " --> " + entry.getValue()); // Each key-value pair is printed in its new, sorted order.
            }
        }
    }
}


// Maps are generally unordered: You cannot directly sort a HashMap and expect it to retain that order.
//The "Sort by Value" Strategy: The common approach is to extract Map.Entry objects into a List and then sort that List using a Comparator.
//Map.Entry.comparingByValue(): This static helper method (Java 8+) makes sorting Map.Entry objects by their values incredibly concise and readable.
//Descending Order: If you wanted to sort in descending order of values, you could use Map.Entry.comparingByValue().reversed().
//Custom Comparators: For more complex sorting rules (e.g., sorting by value, then by key for ties), you would write a custom Comparator.
//Creating a Sorted Map (if needed): If you needed a new Map that retains the sorted order (e.g., for further operations), you would typically
// put the entries from entryList into a LinkedHashMap (which maintains insertion order) or a TreeMap (if you want it sorted by keys).