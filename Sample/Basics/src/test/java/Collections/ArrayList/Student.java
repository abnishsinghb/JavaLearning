package Collections.ArrayList;

//In real-world apps, we need to store:
//
//Multiple objects (e.g., many students, products)
//
//Dynamically sized data
//
//Key-value mappings (e.g., employee ID → employee object)
//
//Java Collections make this easy with powerful built-in data structures.

public class Student {
    public int rollNo;
    public String name;
    public int marks;

    public Student(int rollNo, String name, int marks) {  // Parameterised Constructor
        this.rollNo = rollNo;
        this.name = name;
        this.marks = marks;

    }

    public int getRollNo() {
        return rollNo;
    }

    public String getName() {
        return name;
    }

    public int getMarks() {
        return marks;
    }

    public void printInfo() {
        System.out.println("| RollNo :  " + rollNo + " | Name :  " + name + " | Marks :  " + marks);
    }
}
