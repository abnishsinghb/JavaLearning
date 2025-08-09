package Practise.medium;

import java.util.Scanner;

// A Strong number is a number for which the sum of the factorials of its digits equals the number itself.
// | Code Part          | Role                          |
//| ------------------ | ----------------------------- |
//| `factorial(int n)` | Method definition             |
//| `factorial(digit)` | Method call with actual value |
//| `n`                | Receives the value of `digit` |
// The value of digit (extracted from the number) is passed to the factorial method.
//
//That value becomes n inside the method.


public class StrongNum {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter the num : ");
        int num = sc.nextInt();

        int original = num;
        int sum = 0;

// Step 2: Loop through each digit
        while (num != 0) {
            int digit = num % 10;  // get last digit
            sum += factorial(digit); // add factorial of digit to sum
            num = num / 10; // remove last digit
        }
        // Step 3: Check if sum of factorials equals original number
        if (sum == original) {
            System.out.println("Its Strong number");
        } else {
            System.out.println("Not a Strong number");
        }

    }

    // Helper method to calculate factorial of a digit
    public static int factorial(int n) {
        int fact = 1;
        for (int i = 1; i <= n; i++) {
            fact *= i;
        }
        return fact;
    }
}
//[Start]
//   |
//   v
//[Prompt user: "Enter the num:"]
//   |
//   v
//[Read num]
//   |
//   v
//[Set original = num]
//[Set sum = 0]
//   |
//   v
//[While num != 0]
//   |
//   v
//[digit = num % 10]          // get last digit
//[sum += factorial(digit)]   // add factorial of digit to sum
//[num = num / 10]            // remove last digit
//   |
//   v
//(repeat until num == 0)
//   |
//   v
//[Is sum == original?]
//   |               \
//  Yes               No
//   |                 \
//   v                  v
//[Print "Its          [Print "Not a Strong number"]
//Strong number"]
//   |                  |
//   v                  v
//                  [End]
//   |
//   v
// [End]
//
//--------------------------
//Helper Method: factorial(int n)
//--------------------------
//[Input: n]
//   |
//   v
//[fact = 1]
//   |
//   v
//[For i = 1 to n]
//   |
//   v
//[fact = fact * i]
//   |
//   v
//(repeat until i > n)
//   |
//   v
//[Return fact]