package Oops.Inheritance;
//Method Overriding : Child class redefines a method of the parent class.
//
//Used to change behavior as needed.

public class Student extends Person {
    int rollnumber;

    @Override
        // here child class redefines method from parent class
    void sayHello() {
        System.out.println("Hello I am , Student " + name);
    }

    void displayStudentInfo() {
        System.out.println("My Roll num is " + rollnumber);
    }
}
