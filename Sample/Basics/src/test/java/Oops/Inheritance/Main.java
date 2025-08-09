package Oops.Inheritance;

public class Main {
    public static void main(String[] args) {
        Student s = new Student();

        s.name = "Ravikant";
        s.rollnumber = 101;
        s.sayHello(); // Inherited from Person
        s.displayStudentInfo(); // defined in Student

        // PolyMorphism : “Many forms” — the same method behaves differently depending on the object.

        Person p = new Student();

        p.name = "Ravikant Sharma";
        p.sayHello();

        // Even though the type is Person, it calls the Student version of sayHello() — this is dynamic method dispatch.
    }
}
