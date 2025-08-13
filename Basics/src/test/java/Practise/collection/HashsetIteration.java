package Practise.collection;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

//| Step | Code Part                                   | Explanation                              |
//| ---- | ------------------------------------------- | ---------------------------------------- |
//| 1    | `Set<String> cities = new HashSet<>();`     | Create HashSet (no duplicates, no order) |
//| 2    | `for (String city : cities)`                | Enhanced for-loop                        |
//| 3    | `Iterator<String> itr = cities.iterator();` | Manual control over iteration            |
//| 4    | `itr.hasNext()` & `itr.next()`              | Standard iterator usage                  |

public class HashsetIteration {
    public static void main(String[] args) {

        // Step 1: Create a HashSet and add elements
        Set<String> cities = new HashSet<>();
        cities.add("Delhi");
        cities.add("Mumbai");
        cities.add("Chennai");
        cities.add("Kolkatta");
        cities.add("Bengaluru");

        System.out.println("The List of cities is : " + cities);

        // Step 2: Iterate using for-each loop
        System.out.println("\nIterate through for each");
        for (String city : cities) {
            System.out.println(city);
        }

        // Step 3: Iterate using Iterator
        Iterator<String> itr = cities.iterator();
        while (itr.hasNext()) {
            System.out.println("The list of cities using Iterator is : " + itr.next());
        }

    }
}
