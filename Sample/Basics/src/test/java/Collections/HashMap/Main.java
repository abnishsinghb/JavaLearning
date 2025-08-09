package Collections.HashMap;

import Collections.ArrayList.Student;

import java.util.HashMap;

public class Main {
    public static void main(String[] args) {


        HashMap<Integer, Student> studentMap = new HashMap<>();

        studentMap.put(1, new Student(1, "Akash ", 99));
        studentMap.put(2, new Student(2, "Akash Singh ", 90));
        studentMap.put(3, new Student(3, "Akashvani ", 69));

        // Access by Key
        System.out.println("Student with rollNo 2");
        studentMap.get(2).printInfo();

        // Loop through all enteries
        System.out.println("All Students ");
        for (Integer roll : studentMap.keySet()) {
            studentMap.get(roll).printInfo();
        }

    }
}
