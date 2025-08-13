package Basics;

public class OperatorsDemo {
    public static void main(String[] args) {
        // Arithmetic Operators
        int a = 10, b = 5;
        System.out.println("a + b ?= " + (a + b)); // Addition
        System.out.println("a > b ? " + (a > b));
        System.out.println("a - b ?= " + (a - b)); // Subtraction
        System.out.println("a == b ?" + (a == b));
        System.out.println("a != b ? " + (a != b));

        boolean cond1 = true;
        boolean cond2 = false;

        System.out.println("cond1 && cond2 ? " + (cond1 && cond2));
        System.out.println("cond1 || cond2 ? " + (cond1 || cond2));
        System.out.println("!cond1 ?" + (!cond1));

        int marks = 35;
        if (marks >= 90) {
            System.out.println("A Grade");
        } else if (marks >= 60) {
            System.out.println("B Grade");
        } else {
            System.out.println(" C Grade");
        }

        int day = 1;

        switch (day) {

            case 1:
                System.out.println("Monday");
                break;

            case 2:
                System.out.println("Tuesday");
                break;

            case 3:
                System.out.println("Wednesday");
                break;

        }
    }
}
