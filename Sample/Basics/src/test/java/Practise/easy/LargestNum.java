package Practise.easy;

import java.util.Scanner;

public class LargestNum {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter the first num");
        int num1 = sc.nextInt();

        System.out.println("Enter second number");
        int num2 = sc.nextInt();

        if (num1 > num2) {
            System.out.println(+num1 + " Is largest");
        } else
            System.out.println(+num2 + " Is largest");
    }
}

//[Start]
//   |
//   v
//[Prompt user: "Enter the first num"]
//   |
//   v
//[Read num1]
//   |
//   v
//[Prompt user: "Enter second number"]
//   |
//   v
//[Read num2]
//   |
//   v
//[Is num1 > num2?]
//   |              \
//  Yes              No
//   |                \
//   v                 v
//[Print               [Print
//"num1 Is largest"]   "num2 Is largest"]
//   |                 |
//   v                 v
//                [End]
//   |
//   v
// [End]