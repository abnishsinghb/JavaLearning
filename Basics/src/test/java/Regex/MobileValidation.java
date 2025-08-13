package Regex;

import java.util.Scanner;

public class MobileValidation {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number : ");
        String num = sc.nextLine();

        String regex = "^[6789]\\d{9}$"; // ^[6789][0-9]{9}$
        if (num.matches(regex)) {
            System.out.println("Valid num : ");
        } else {
            System.out.println("Invalid num : ");
        }
    }
}
