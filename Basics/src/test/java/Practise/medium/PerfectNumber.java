package Practise.medium;

import java.util.Scanner;

// A perfect number is a positive integer that is equal to the sum of its proper divisors (excluding itself).
public class PerfectNumber {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter the number");
        int num = sc.nextInt();
        int sum = 0;

        // sum of perfect number divisor
        for (int i = 1; i <= num / 2; i++) {
            if (num % i == 0)
                sum += i;
        }
        // Check for perfect number
        if (sum == num) {
            System.out.println("Its perfect number");
        } else {
            System.out.println("Not perfect number");
        }

    }
}
//[Start]
//   |
//   v
//[Prompt user: "Enter the number"]
//   |
//   v
//[Read num]
//   |
//   v
//[sum = 0]
//   |
//   v
//[For i = 1 to num/2]
//   |
//   v
//[If num % i == 0]
//   |           \
//  Yes           No
//   |             \
//[sum += i]      [continue loop]
//   |
//   v
//(repeat until i > num/2)
//   |
//   v
//[Is sum == num?]
//   |            \
//  Yes            No
//   |              \
//   v               v
//[Print "Its       [Print "Not perfect number"]
//perfect number"]
//   |               |
//   v               v
//               [End]
//   |
//   v
// [End]