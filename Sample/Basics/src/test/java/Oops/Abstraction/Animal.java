package Oops.Abstraction;

//Abstract class : A class that cannot be instantiated.
//It can have abstract methods (no body) and regular methods.

abstract class Animal {
    abstract void makeSound();

    void breathe() {
        System.out.println("I can Breathe");
    }

}

class Dog extends Animal {

    @Override
    void makeSound() {
        System.out.println("Bark!");
    }

}
