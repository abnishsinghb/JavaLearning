package Collections.TreeSet_Map;

import java.util.ArrayList;
import java.util.Collections;

public class SortStringList {
    public static void main(String[] args) {
        ArrayList<String> names = new ArrayList<>();
        names.add("Ananya");
        names.add("Srikant");
        names.add("Zorawar");
        Collections.sort(names); // sorts in ascending order

        System.out.println("Sorted names are : " + names);
    }
}
