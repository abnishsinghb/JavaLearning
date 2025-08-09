package Practise.collection;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FindDuplicates {
    public static void main(String[] args) {

        // Step 1: Input list with duplicates
        List<String> items = new ArrayList<>();
        items.add("Java");
        items.add("Python");
        items.add("Ruby");
        items.add("Java");
        items.add("Python");
        items.add("Perl");

        System.out.println("Original List : " + items);

        // Step 2: Create sets for tracking
        Set<String> unique = new HashSet<>();
        Set<String> duplicate = new HashSet<>();

        for (String item : items) {
            if (!unique.add(item)) {
                duplicate.add(item);
            }
        }

        // Step 3: Print duplicate elements
        System.out.println(" Duplicate items are : " + duplicate);  // This step is enough or below
//        System.out.println("\n Duplicate items are : ");
//        for (String dup : duplicate){
//            System.out.println(dup);
//        }

    }
}

//| Step | Code Part                | Explanation                                        |
//| ---- | ------------------------ | -------------------------------------------------- |
//| 1    | `Set<String> unique`     | Stores unique items                                |
//| 2    | `Set<String> duplicates` | Stores repeated items                              |
//| 3    | `!unique.add(item)`      | If element already exists in set, it's a duplicate |
//| 4    | Print duplicates         | Shows only repeated elements                       |