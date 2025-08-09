package Collections.TreeSet_Map;

import java.util.TreeMap;

public class TreeMapDemo {
    public static void main(String[] args) {
        TreeMap<Integer, String> empMap = new TreeMap<>();
        empMap.put(105, "Sneha");
        empMap.put(100, "Sanam");
        empMap.put(103, "Rahul");

        System.out.println(" Sorted Employees by id are :");
        for (Integer id : empMap.keySet()) {
            System.out.println(" ID :" + id + " , Name : " + empMap.get(id));
        }
    }
}
