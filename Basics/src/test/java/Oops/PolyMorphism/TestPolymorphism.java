package Oops.PolyMorphism;

class Animal {  // Animal is a base (parent/superclass) class.It has a method makeSound() that prints "Animal sound".
    void makesound() {
        System.out.println("Animal Sound");
    }
}

class Dog extends Animal { // Both Dog and Cat extend Animal, which means they inherit its properties and methods.

    void makesound() { // Both classes override the makeSound() method with their own version:
        System.out.println("Boow");
    }
}

class Cat extends Animal {

    void makesound() {
        System.out.println("meow");
    }
}

public class TestPolymorphism {
    public static void main(String[] args) {
        Animal a = new Dog(); // Here, variable a is of type Animal, but it's used to refer to objects of different subclasses
        a.makesound();
        Animal a2 = new Cat();
        a2.makesound();

    }
}

// Inheritance (extends)
//Method overriding
//Polymorphism (via reference of superclass type pointing to objects of subclass)
//Dynamic method dispatch (correct overridden method selected at runtime)