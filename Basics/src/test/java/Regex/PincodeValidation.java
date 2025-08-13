package Regex;

import java.util.Scanner;

public class PincodeValidation {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the pincode :");

        String pin = sc.nextLine();

        String regex = "^[1-9][0-9]{5}$";

        if (pin.matches(regex)) {
            System.out.println("Valid pin code");
        } else
            System.out.println("Invalid ");
    }
}
