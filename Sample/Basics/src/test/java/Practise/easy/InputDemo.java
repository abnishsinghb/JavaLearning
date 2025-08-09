package Practise.easy;

import java.util.Scanner;

public class InputDemo {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the name: ");
        String name = scanner.nextLine(); // nextInt() for int and nextLine() for String
        System.out.println("Hello name is " + name + "!");
    }
}
