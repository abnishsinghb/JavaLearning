package Collections.ArrayList;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<Student> studentlist = new ArrayList<>();

        studentlist.add(new Student(1, "Ram", 62));
        studentlist.add(new Student(2, "Ravi", 92));
        studentlist.add(new Student(3, "Raveena", 82));

        System.out.println("All Students");
        for (Student s : studentlist) {
            s.printInfo();
        }

        System.out.println("Students who have scored");
        for (Student s : studentlist) {
            if (s.getMarks() > 80) {
                s.printInfo();
            }
        }
    }
}
