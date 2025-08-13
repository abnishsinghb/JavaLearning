package Collections.HashMap;
//A HashMap is a powerful tool for storing data in key-value pairs. Unlike an ArrayList which uses integer indexes, a HashMap allows you to use a unique key (like a String or Integer) to access its corresponding value.
//
//Think of it like a dictionary: you look up a word (the key) to find its definition (the value). This makes retrieving data very fast.

import java.util.HashMap;

public class fruitprices {
    public static void main(String[] args) {
        // // Create a HashMap with String keys and Double values
        HashMap<String, Double> fruitPrice = new HashMap<>();

        // Add key-value pairs to the map
        fruitPrice.put("Apple", 1.50);
        fruitPrice.put("Orange", 1.25);
        fruitPrice.put("Banana", 1.75);

        System.out.println(fruitPrice);

        // Access a value using its key
        double orangeprice = fruitPrice.get("Orange");
        System.out.println(" The price of Orange in $ is : $" + orangeprice); // Output: $1.25


        // Check if a key exists
        boolean hasApple = fruitPrice.containsKey("Apple");
        System.out.println(" Does Apple has key and value ? : " + hasApple); // Output: tru

        // Remove a key-value pair
        fruitPrice.remove("Banana");
        System.out.println(" The Updated fruit list is : " + fruitPrice); // Output: {Apple=1.5, Orange=1.25}

    }
}
