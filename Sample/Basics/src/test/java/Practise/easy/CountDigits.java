package Practise.easy;

import java.util.Scanner;

// Count number of Digits , Input a number and print how many digits it has.

public class CountDigits {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter the number of digits to be counted");
        int num = sc.nextInt();
        int count = 0;
        if (num == 0) {
            count = 1;
        }
        while (num != 0) {
            count++;
            num = num % 10;
        }
        System.out.println("Total number of digits : " + count);
    }
}
//[Start]
//   |
//   v
//[Prompt user for input]
//   |
//   v
//[Read num]
//   |
//   v
//[count = 0]
//   |
//   v
//[Is num == 0?]
//   |         \
//  Yes         No
//   |           \
//[count = 1]    [Proceed to while loop]
//   |           |
//   v           v
//            [while (num != 0)]
//                |
//                v
//            [count++]
//            [num = num / 10]
//                |
//           (repeat until num == 0)
//   |
//   v
//[Print "Total number of digits: " + count]
//   |
//   v
//[End]
//```