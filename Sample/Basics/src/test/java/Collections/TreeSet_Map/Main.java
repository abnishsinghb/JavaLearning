package Collections.TreeSet_Map;

import java.util.ArrayList;
import java.util.Collections;

public class Main {

    public static void main(String[] args) {
        ArrayList<Student> list = new ArrayList<>();
        list.add(new Student(1, "Anany", 98));
        list.add(new Student(2, "Sneha", 88));
        list.add(new Student(3, "Rahul", 58));

        Collections.sort(list);  // Uses compareTo

        System.out.println("Students sorted by marks are : ");
        for (Student s : list) {
            s.printInfo();
        }
    }
}
