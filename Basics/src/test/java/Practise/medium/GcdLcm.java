package Practise.medium;

import java.util.Scanner;

public class GcdLcm {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter first num : ");
        int a = sc.nextInt();

        System.out.println("Enter second num : ");
        int b = sc.nextInt();

        int gcd = 1;

        for (int i = 1; i <= a && i <= b; i++) {
            if (a % i == 0 && b % i == 0) {
                gcd = i;
            }
        }
        System.out.println("Gcd of " + a + "& " + b + "is : " + gcd);

        int lcm = (a * b) / gcd;

        System.out.println("The lcm of " + a + " & " + b + " is " + lcm);
    }
}
//[Start]
//   |
//   v
//[Prompt user: "Enter first num:"]
//   |
//   v
//[Read a]
//   |
//   v
//[Prompt user: "Enter second num:"]
//   |
//   v
//[Read b]
//   |
//   v
//[gcd = 1]
//   |
//   v
//[For i = 1 to min(a, b)]
//   |
//   v
//[If (a % i == 0) AND (b % i == 0)]
//   |                    \
//  Yes                    No
//   |                      \
//[gcd = i]                [continue loop]
//   |
//   v
//(repeat until i > min(a, b))
//   |
//   v
//[Print "Gcd of a & b is: gcd"]
//   |
//   v
//[lcm = (a * b) / gcd]
//   |
//   v
//[Print "The lcm of a & b is lcm"]
//   |
//   v
//[End]
//```