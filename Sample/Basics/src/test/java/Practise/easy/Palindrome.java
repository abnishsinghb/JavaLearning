package Practise.easy;

import java.util.Scanner;

public class Palindrome {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the num : ");

        int num = sc.nextInt();
        int original = num;
        int reverse = 0;

        while (num != 0) {
            int digit = num % 10;
            reverse = reverse * 10 + digit;
            num = num / 10;
        }
        if (original == reverse) {
            System.out.println("Palindrome ");
        } else {
            System.out.println("Not a Palindrome");
        }
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
//[Set reverse = 0]
//   |
//   v
//[While num != 0]
//   |
//   v
//[digit = num % 10]
//[reverse = reverse * 10 + digit]
//[num = num / 10]
//   |
//   v
//(repeat until num == 0)
//   |
//   v
//[Is original == reverse?]
//   |                   \
//  Yes                   No
//   |                     \
//   v                      v
//[Print "Palindrome"]   [Print "Not a Palindrome"]
//   |                     |
//   v                     v
//                     [End]
//   |
//   v
// [End]