package Basics;

public class TypeCastingDemo {
    public static void main(String[] args) {
        // Implicit casting (widening)
        int num = 10;
        double result = num;
        System.out.println("Implicit casting (widening): " + result);


        // Explicit casting (narrowing)
        double pi = 3.1459;
        int intPi = (int) pi; // Explicitly casting double to int
        System.out.println("Explicit casting (narrowing): " + intPi);

    }
}
