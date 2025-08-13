package Oops.Interface;
//Create Flyable and Swimmable interfaces → Implement both in Duck → Create and call methods

interface Swimable {
    void swim(); // // abstract method without implementation
}

interface Flyable1 {
    void fly();
}

class Duck implements Swimable, Flyable1 { // The Duck class showcases this by implementing two interfaces.(Multiple Inheritance)
    public void swim() {
        System.out.println("Duck can fly");
    }

    public void fly() {
        System.out.println("Duck can Swim");
    }

    public static void main(String[] args) {
        Duck d = new Duck();
        d.fly();
        d.swim();
    }
}
//An interface in Java is a reference type, similar to a class, that can contain only abstract methods (methods without bodies) and constants
// (public, static, and final variables). Interfaces are used to specify a contract that other classes must follow.
//Interfaces specify behavior contracts.
//Supports multiple inheritance via interfaces (but not class-level inheritance).
//All contract methods must be implemented by the concrete class.