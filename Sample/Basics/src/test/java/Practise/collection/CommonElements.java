package Practise.collection;
//Given two ArrayLists, find and print the elements that exist in both.
//Use either:retainAll() method (simplest)
//Or manual loop with Set for better performance.

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CommonElements {
    public static void main(String[] args) {
        // Step 1: Create two lists
        List<String> list1 = new ArrayList<>();
        list1.add("Alice");
        list1.add("Bob");
        list1.add("Charlie");
        list1.add("David");
        list1.add("Eddie");

        List<String> list2 = new ArrayList<>();
        list2.add("Eddie");
        list2.add("David");
        list2.add("Alice");
        list2.add("Freddie");


        // Step 2: Find common elements using retainAll() and print
        List<String> common = new ArrayList<>(list1); // make a copy of list1
        common.retainAll(list2); // keep only elements that are also in list2

//        Set<String> set = new HashSet<>(list1);  can use Set as well
//        set.retainAll(list2);

        System.out.println(" The common list is : " + common);

    }
}
//| Step | Code Part                | Explanation                                                    |
//| ---- | ------------------------ | -------------------------------------------------------------- |
//| 1    | `retainAll()`            | Modifies `list1` copy to keep only elements present in `list2` |
//| 2    | `new ArrayList<>(list1)` | Important to copy original if you want to keep list1 unchanged |
//| 3    | Print result             | Shows overlapping items only                                   |