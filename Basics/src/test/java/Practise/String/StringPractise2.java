package Practise.String;

import java.util.Scanner;

public class StringPractise2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the String : ");
        String s = sc.nextLine();

        // Split string into words
        String[] words = s.split("\\s+");
        for (String word : words) {
            System.out.println(word);
        }

        // Replace characters
        System.out.println("\nEnter the old char");
        char oldchar = sc.next().charAt(0);
        System.out.println("\nEnter new char");
        char newchar = sc.next().charAt(0);
        String replaced = s.replace(oldchar, newchar);
        System.out.println("Replaced character is : " + replaced);

        // Trim whitespace
        String trimmed = s.trim(); // Removes leading and trailing space
        System.out.println("Trimmed String is: '" + trimmed + "'");

        // Compare two strings (case-sensitive and insensitive)
        System.out.println("Enter another String to compare : ");
        String s2 = sc.nextLine();
        System.out.println("Case Sensitive : " + s2.equals(s));
        System.out.println("Case Insensitive : " + s2.equalsIgnoreCase(s));

        // Convert string to char array and print
        char[] arr = s.toCharArray();
        System.out.println("Characters in the String : ");
        for (char c : arr) {
            System.out.print(c + " ");
        }
        System.out.println();

        // Convert number to string
        int num = 12345;
        String strNum = Integer.toString(num);
        System.out.println(" number to String is " + strNum);

        // Convert string to integer
        System.out.println("Enter the numeric String : ");
        String numericString = sc.nextLine();
        try {
            int val = Integer.parseInt(numericString);
            System.out.println(" Converted to String : " + val);
        } catch (NumberFormatException e) {
            System.out.println("Invalid numeric String");
        }

    }
}
