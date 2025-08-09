package Practise.collection;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//| Step | Code Part                                              | Explanation                                    |
//| ---- | ------------------------------------------------------ | ---------------------------------------------- |
//| 1    | `List<String> colors = new ArrayList<>();`             | Create a list with some duplicate values       |
//| 2    | `Map<String, Integer> frequencyMap = new HashMap<>();` | Initialize a map to hold frequency             |
//| 3    | `getOrDefault(color, 0) + 1`                           | If not found, take 0 as default, then add 1    |
//| 4    | `entrySet()`                                           | Loop over key-value pairs to print frequencies |

public class CountFrequency {
    public static void main(String[] args) {

        // Step 1: Create a list with repeated values

        List<String> colors = new ArrayList<>();
        colors.add("Red");
        colors.add("Blue");
        colors.add("Red");
        colors.add("Blue");
        colors.add("Green");
        colors.add("Blue");
        System.out.println(" The Complete List : " + colors);

        // Step 2: Use HashMap to store frequencies
        Map<String, Integer> frequencyMap = new HashMap<>();
        for (String color : colors) {
            frequencyMap.put(color, frequencyMap.getOrDefault(color, 0) + 1);
        }

        // Step 3: Print frequency of each element
        System.out.println("\n Frequency of each element");
        for (Map.Entry<String, Integer> entry : frequencyMap.entrySet()) {
            System.out.println(entry.getKey() + " --> " + entry.getValue());   // entry.getKey() and entry.getValue(): In each iteration, we access the key
            // (the color name) and the value (the count) for the current entry and print them in a readable format.
        }
    }
}
