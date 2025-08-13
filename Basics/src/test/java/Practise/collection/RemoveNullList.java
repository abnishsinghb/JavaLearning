package Practise.collection;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
//Given a list that may contain null values.
//Remove all null elements from the list.
//Use Java 8+ features like removeIf().

public class RemoveNullList {
    public static void main(String[] args) {
        // Step 1: Create a list with nulls
        List<String> names = new ArrayList<>();
        names.add("Alice");
        names.add("Bob");
        names.add(null);
        names.add("Charlie");
        names.add(null);

        System.out.println("Original List : " + names);

        // Step 2: Remove null values
        names.removeIf(Objects::isNull);

        // Step 3: Print the cleaned list

        System.out.println("\nThe new list without null values is : " + names);
    }
}
//| Step | Code Part                                 | Explanation                                 |
//| ---- | ----------------------------------------- | ------------------------------------------- |
//| 1    | `List<String> names = new ArrayList<>();` | Create list with some `null` values         |
//| 2    | `removeIf(Objects::isNull);`              | Java 8+ method: removes all `null` elements |
//| 3    | Print list before and after               | Observe the change in list size/content     |