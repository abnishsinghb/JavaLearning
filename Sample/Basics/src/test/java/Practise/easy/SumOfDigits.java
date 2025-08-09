package Practise.easy;

import java.util.Scanner;

public class SumOfDigits {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter the total sum digit");
        int num = sc.nextInt();

        int sum = 0;
        while (num != 0) {
            int digit = num % 10;
            sum += digit;  // sum = sum + digit;
            num = num / 10;

        }
        System.out.println("Sum of digits " + sum);
    }
}
//[Start]
//   |
//   v
//[Prompt user: "Enter the total sum digit"]
//   |
//   v
//[Read num]
//   |
//   v
//[sum = 0]
//   |
//   v
//[While num != 0]
//   |
//   v
//[digit = num % 10]
//[sum = sum + digit]
//[num = num / 10]
//   |
//   v
//(repeat until num == 0)
//   |
//   v
//[Print "Sum of digits " + sum]
//   |
//   v
//[End]
