package Practise.easy;

import java.util.Scanner;

public class SwapNum {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter first no");
        int a = sc.nextInt();

        System.out.println("Enter 2nd num ");
        int b = sc.nextInt();

//        int temp =a;
//        a = b;
//        b = temp;

        // Swap without temp

        a = a + b;
        b = a - b;
        a = a - b;

        System.out.println("After swapping : ");
        System.out.println("a : " + a);
        System.out.println("b : " + b);
    }
}
//[Start]
//   |
//   v
//[Prompt user: "Enter first no"]
//   |
//   v
//[Read a]
//   |
//   v
//[Prompt user: "Enter 2nd num"]
//   |
//   v
//[Read b]
//   |
//   v
//[Swap values without temp variable]
//   |
//   v
//[a = a + b]
//[b = a - b]
//[a = a - b]
//   |
//   v
//[Print "After swapping:"]
//[Print "a : " + a]
//[Print "b : " + b]
//   |
//   v
//[End]