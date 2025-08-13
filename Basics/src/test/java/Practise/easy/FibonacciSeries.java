package Practise.easy;

import java.util.Scanner;

public class FibonacciSeries {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter the series limit : ");

        int limit = sc.nextInt();

        int a = 0, b = 1;

        System.out.println("Printing Fibonacci series for " + limit + " upto");

        for (int i = 1; i <= limit; i++) {
            System.out.println(a + " ");

            int next = a + b;
            a = b;
            b = next;
        }
    }
}
//[Start]
//   |
//   v
//[Prompt user for series limit]
//   |
//   v
//[Read limit]
//   |
//   v
//[Initialize a = 0, b = 1]
//   |
//   v
//[Print message: "Printing Fibonacci series for limit upto"]
//   |
//   v
//[For i = 1 to limit]
//   |
//   v
//[Print a]
//   |
//   v
//[next = a + b]
//[a = b]
//[b = next]
//   |
//   v
//(repeat until i > limit)
//   |
//   v
//[End]