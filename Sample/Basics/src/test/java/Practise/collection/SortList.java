package Practise.collection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//| Step | Code Part                    | Explanation                                                    |
//| ---- | ---------------------------- | -------------------------------------------------------------- |
//| 1    | `Collections.sort(list)`     | Sorts list in natural order: A-Z for Strings, 0-9 for Integers |
//| 2    | `ArrayList`                  | Dynamic list that allows duplicates and maintains order        |
//| 3    | Output before and after sort | Helps visualize the sorting effect                             |

public class SortList {
    public static void main(String[] args) {

        // Sorting Strings
        List<String> names = new ArrayList<>();
        names.add("John");
        names.add("Alice");
        names.add("Bob");
        names.add("Zak");
        names.add("Joy");

        System.out.println(" The Original List of Strings : " + names);

        Collections.sort(names);

        System.out.println(" \n The Sorted String : " + names);

        // Sorting numbers

        List<Integer> numbers = new ArrayList<>();
        numbers.add(10);
        numbers.add(0);
        numbers.add(100);
        numbers.add(50);
        numbers.add(60);
        numbers.add(30);

        System.out.println(" \n The Original List of numbers : " + numbers); // Here \n leaves a space after output statement

        Collections.sort(numbers);

        System.out.println(" \n The Sorted numbers are : " + numbers);

    }
}
