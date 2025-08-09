package Collections.TreeSet_Map;

public class Student implements Comparable<Student> {    // Comparable is java Interface and comes from java.lang package

    private int rollNo;
    private String name;
    private int marks;

    public Student(int rollNo, String name, int marks) {
        this.rollNo = rollNo;
        this.name = name;
        this.marks = marks;
    }

    public int getMarks() {
        return marks;
    }

    public String getName() {
        return name;
    }

    @Override
    public int compareTo(Student other) {
        return this.marks - other.marks;   // Ascending by marks
    }

    public void printInfo() {
        System.out.println(" Name : " + name + " : Marks : " + marks);
    }

}
