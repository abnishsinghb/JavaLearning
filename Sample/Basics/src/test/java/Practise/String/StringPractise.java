package Practise.String;

import java.util.Scanner;

public class StringPractise {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // Input strings
        System.out.println("Enter the first String");
        String s1 = sc.nextLine();
        System.out.println("Enter the second String");
        String s2 = sc.nextLine();

        // Concatenation
        String concat = s1 + s2;
        System.out.println("\nThe concatenated String is : " + concat);

        // Length
        System.out.println("\nThe String length of s1 is : " + s1.length());
        System.out.println("\nThe String length of s2 is : " + s2.length());

        // Reverse
        String rev1 = new StringBuilder(s1).reverse().toString();
        System.out.println(" The s1 rev is : " + rev1);
        String rev2 = new StringBuilder(s2).reverse().toString();
        System.out.println(" The s2 rev is : " + rev2);

        // Upper and lower case
        System.out.println("Convert s1 to lowercase : " + s1.toLowerCase());
        System.out.println("Convert s2 to upppercase : " + s2.toUpperCase());

        // Substring check
        System.out.println("enter the substring to check in first string : ");
        String sub = sc.nextLine();
        if (s1.contains(sub)) {
            System.out.println("Substring is : " + sub);
        } else {
            System.out.println("Not present");
        }
    }
}
