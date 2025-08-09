package Practise.easy;

import java.util.Scanner;

public class ReverseNumber {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number : ");
        int num = sc.nextInt();

        int reverse = 0;

        while (num != 0) {
            int digit = num % 10; // get last digit
            reverse = reverse * 10 + digit;  // add digit to reverse
            num = num / 10;    // remove last digit
        }
        System.out.println("The reversed number is : " + reverse);
    }
}

//[Start]
//   |
//   v
//[Prompt user: "Enter the number:"]
//   |
//   v
//[Read num]
//   |
//   v
//[Set reverse = 0]
//   |
//   v
//[While num != 0]
//   |
//   v
//[digit = num % 10]         // get last digit
//[reverse = reverse * 10 + digit] // add digit to reverse
//[num = num / 10]           // remove last digit
//   |
//   v
//(repeat until num == 0)
//   |
//   v
//[Print "The reversed number is: " + reverse]
//   |
//   v
//[End]