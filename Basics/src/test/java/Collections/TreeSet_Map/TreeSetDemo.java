package Collections.TreeSet_Map;

import java.util.TreeSet;

public class TreeSetDemo {
    public static void main(String[] args) {
        TreeSet<String> cities = new TreeSet<>();
        cities.add("Chennai");
        cities.add("Bombay");
        cities.add("Agra");

        System.out.println("Sorted cities are : " + cities);
    }
}
