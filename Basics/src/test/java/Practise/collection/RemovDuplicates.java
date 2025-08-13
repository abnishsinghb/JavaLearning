package Practise.collection;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
//| Step | Code Part                                         | Explanation                                                  |
//| ---- | ------------------------------------------------- | ------------------------------------------------------------ |
//| 1    | `List<String> names = new ArrayList<>();`         | Create a list with duplicate values                          |
//| 2    | `Set<String> uniqueNames = new HashSet<>(names);` | HashSet removes duplicates automatically                     |
//| 3    | `new ArrayList<>(uniqueNames)`                    | Convert the Set back to List if order or list type is needed |

public class RemovDuplicates {
    public static void main(String[] args) {

        // Step 1: Create a list with duplicate elements
        List<String> names = new ArrayList<>();
        names.add("John");
        names.add("Alice");
        names.add("Bob");
        names.add("John"); // Adding duplicate
        names.add("Alice"); // Adding duplicate
        System.out.println("The complete list is " + names);

        // Step 2: Remove duplicates using Set

        Set<String> uniqueNames = new HashSet<>(names);  // Removing duplicates as set does not accept duplicates

        // Step 3: Convert back to List if needed

        List<String> finalList = new ArrayList<>(uniqueNames);  // Again converting into List
        System.out.println(" The FinalList after removing Duplicates is : " + finalList);


    }
}
