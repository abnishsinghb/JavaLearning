package Practise.easy;

// A prime number is a number greater than 1 that has no divisors other than 1 and itself.
//Examples: 2, 3, 5, 7, 11, 13, ...

import java.util.Scanner;

public class PrimeNum {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter the num");
        int num = sc.nextInt();

        boolean isPrime = true;

        if (num <= 1) {
            isPrime = false;
        } else {
            for (int i = 1; i <= num / 2; i++) {
                if (num % i == 0) {
                    isPrime = false;
                    break;
                }

            }
        }

        if (isPrime) {
            System.out.println(num + " Is a prime num");
        } else {
            System.out.println(num + " Is not prime num");
        }

    }
}

// [Start]
//   |
//   v
//[Prompt user: "Enter the num"]
//   |
//   v
//[Read num]
//   |
//   v
//[isPrime = true]
//   |
//   v
//[Is num <= 1?]
//   |             \
//  Yes             No
//   |               \
//   v                v
//[isPrime = false]   [For i = 2 to num/2]
//   |                |
//   v                [Is num % i == 0?]
//                    |            \
//                   Yes           No
//                    |             \
//            [isPrime = false]    [continue loop]
//            [break]
//   |                |
//   v                v
//[Is isPrime true?]
//   |             \
//  Yes             No
//   |               \
//   v                v
//[Print              [Print
//"num is prime"]     "num is not prime"]
//   |                |
//   v                v
//               [End]
//   |
//   v
// [End]