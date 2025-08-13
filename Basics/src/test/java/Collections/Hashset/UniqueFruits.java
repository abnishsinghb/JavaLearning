package Collections.Hashset;
//A HashSet is a collection that stores only unique items. It's useful when you need to make sure there are no duplicates in your data. Think of it like a list of unique email addresses; you wouldn't want the same address listed twice.
//
//The main features of a HashSet are:
//
//It does not allow duplicate elements.
//
//It's very fast for checking if an item exists.
//
//It does not guarantee the order of elements.

import java.util.HashSet;

public class UniqueFruits {
    public static void main(String[] args) {
        HashSet<String> uniquefruits = new HashSet<>();

        uniquefruits.add("Apple");
        uniquefruits.add("Orange");
        uniquefruits.add("Mango");
        uniquefruits.add("Banana");
        uniquefruits.add("Banana"); // It will not add duplicate

        System.out.println(" The list of fruits are : " + uniquefruits);
        boolean hasKiwi = uniquefruits.contains("Kiwi");
        System.out.println("Does fruit list contain kiwi : " + hasKiwi);  // false
    }
}
