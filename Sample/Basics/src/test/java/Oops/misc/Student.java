package Oops.misc;

//Concept	       Meaning
//Class	     :   A blueprint for objects
//Object	 :   An instance of a class
//Constructor:	 Initializes an object
//               Encapsulation	Hiding internal details using private fields and public methods
//Inheritance:	 One class inherits from another
//Polymorphism:	 Many forms: method overloading/overriding
//Abstraction:	 Hiding implementation, exposing only essential features
//A constructor is a special method that is called when an object is created.
//
//It has the same name as the class and no return type.


public class Student {
    String name;
    int age;

    // Creating Constructor
    Student(String n, int a) {
        name = n;
        age = a;
    }

    void display() {
        System.out.println("Name : " + name);
        System.out.println("Age : " + age);
    }
}
