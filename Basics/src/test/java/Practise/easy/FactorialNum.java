package Practise.easy;

import java.util.Scanner;

// Factorial of n (written as n!) is the product of all positive integers less than or equal to n.
//
//Example:
//5! = 5 × 4 × 3 × 2 × 1 = 120

public class FactorialNum {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the factorial num ");
        int n = sc.nextInt();

        if (n < 0) {
            System.out.println("Factorial not defined for negative number");
        } else {
            long fact = 1;
            for (int i = 1; i <= n; i++) {
                fact *= i;
            }
            System.out.println("Factorial of " + n + " is " + fact);
        }
    }
}
//```
//[Start]
//   |
//   v
//[Prompt user for input]
//   |
//   v
//[Read n]
//   |
//   v
//[Is n < 0?]
//   |             \
//  Yes             No
//   |               \
//   v                v
//[Print "Factorial   [fact = 1]
//not defined for     |
//negative number"]   v
//   |           [For i = 1 to n]
//   |               |
//   v           [fact = fact * i]
//   |           (repeat for all i)
//   v                |
//   v           [Print "Factorial of n is fact"]
//   |                |
//   v                v
//              [End]
//   |
//   v
//[End]