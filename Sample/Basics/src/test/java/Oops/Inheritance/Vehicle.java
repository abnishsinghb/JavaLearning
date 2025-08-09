package Oops.Inheritance;
//Create a Vehicle base class and extend it with a Car subclass. Show method inheritance and overriding.

class Vehicle {
    void start() {
        System.out.println("Vehicle started");
    }
}

class Car extends Vehicle {
    @Override
    void start() {
        System.out.println("Car Started");
    }

    public static void main(String[] args) {
        Car c = new Car();
        c.start();
    }
}
//Inheritance allows the Car to reuse and specialize the Vehicle features.
//Method overriding lets the child class change behavior as needed.
//Promotes code reuse and supports the DRY principle.