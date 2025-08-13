package Practise.easy;

import java.util.Scanner;

public class EvenOdd {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(" Enter the num : ");
        int num = scanner.nextInt();

        if (num % 2 == 0) {
            System.out.println(+num + " is even");
        } else {
            System.out.println(+num + " is odd");
        }
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
//[Is num % 2 == 0?]
//   |             \
//  Yes             No
//   |               \
//   v                v
//[Print              [Print
//"num is even"]      "num is odd"]
//   |                |
//   v                v
//               [End]
//   |
//   v
// [End]
//```