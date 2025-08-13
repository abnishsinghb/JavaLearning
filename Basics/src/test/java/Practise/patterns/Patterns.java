package Practise.patterns;

public class Patterns {
    public static void main(String[] args) {
        int n = 5;
        for (int i = 1; i <= n; i++) {  // for inverted use for( int i = n;i>=1;i--)
            for (int j = 1; j <= i; j++) {
                System.out.print("* "); // This does not move to nextline after printing
            }
            System.out.println();
        }
        System.out.println();

        //Outer Loop (i): Controls the number of rows. Runs from 1 to n (number of rows).
        //Inner Loop (j): For each row i, prints i stars, separated by spaces.
        //Print Statement: After printing all stars for a row, System.out.println(); moves to the next line.
        //Control Statements Used: Nested for loops, which allow for repeating a block (printing stars) inside another block (printing rows).
        // Outer loop: for (int i = 1; i <= n; i++) controls the rows, so there are n rows.
        //Inner loop: for (int j = 1; j <= i; j++) prints i stars for row i.
        //The number of stars increases with each row because the inner loop depends on the outer loop’s counter.
        //Line break: System.out.println(); moves to the next line after each row.

        // Inverted Right angled Triangle
        for (int i = n; i >= 1; i--) {
            for (int j = 1; j <= i; j++) {
                System.out.print("@ "); // This does not move to nextline after printing
            }
            System.out.println();
        }

        System.out.println();

        //Outer Loop (i): Starts from n (total rows) and decrements to 1.
        //Inner Loop (j): For each row, prints i stars.
        //Print Statement: Moves to the next line after each row.
        //Control Statements Used: Nested for loops, where the outer loop counts down.
        //Outer loop: for (int i = n; i >= 1; i--) starts at n and decreases to 1, creating rows in descending order.
        //Inner loop: for (int j = 1; j <= i; j++) prints i stars on each row, so the number of stars decreases as rows go down.
        //Line break: Moves to the next line after each row.

        // Pyramid
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n - i; j++) {
                System.out.print(" ");
            }
            // Print stars
            for (int k = 1; k <= i; k++) {
                System.out.print("# ");
            }
            System.out.println();

            //First Inner Loop (j): Prints spaces to center-align the pyramid, decreasing as rows increase.
            //Second Inner Loop (k): Prints stars, with the count equal to the current row number.
            //Print Statement: Moves to the next line after each row.
            //Control Statements Used: Two nested for loops inside the main row loop, one for spaces and one for stars.
            //Outer loop: Controls the number of rows (i from 1 to n).
            //First inner loop (spaces): for (int j = 1; j <= n - i; j++) prints spaces before the stars. More spaces are printed at the top, fewer at the bottom.
            //Second inner loop (stars): for (int k = 1; k <= i; k++) prints i stars, growing with each row.
            //Result: The combination of spaces and stars makes a centered pyramid.

            // number triangle

            for (i = 1; i <= n; i++) {
                for (int j = 1; j <= i; j++) {
                    System.out.print(j + " ");
                }
                System.out.println();
            }

            // Outer Loop (i): Runs from 1 to n (rows).
            //Inner Loop (j): Prints numbers from 1 up to the current row number (i).
            //Print Statement: Moves to the next line after each row.
            //Control Statements Used: Nested for loops, with the inner loop using its counter as the number to print.
            //Outer loop: for (int i = 1; i <= n; i++) controls rows.
            //Inner loop: for (int j = 1; j <= i; j++) prints numbers from 1 up to i for each row.
            //Each row displays a sequence starting from 1 and ending at that row’s number.


            // Floyd’s Triangle

            int num = 1;
            for (i = 1; i <= n; i++) {
                for (int j = 1; j <= i; j++) {
                    System.out.print(num + " ");
                }
                System.out.println();
            }

            // Outer Loop (i): Controls the number of rows.
            //Inner Loop (j): For each row, prints as many numbers as the row number (i).
            //Variable num: Keeps track of the current number to print, incremented with every print.
            //Print Statement: Moves to the next line after each row.
            //Control Statements Used: Nested for loops, and a variable that changes with each iteration.
            //Outer loop: Controls the number of rows.
            //Inner loop: Runs for the row’s length; for each cell, prints the next number in sequence (num).
            //Variable num: Starts at 1 and is incremented after each print, so numbers increase sequentially from row to row.
        }

    }
}

//Summary
// Outer Loops: Always control the number of rows (vertical dimension).
//Inner Loops: Control what gets printed in each row (horizontal dimension).
//If Statements (sometimes needed): For patterns with conditions (like hollow patterns or alternating).
//Loop Indexing: The relationship between the outer and inner loop indices determines the pattern.
//For loops: All patterns rely on nested for loops.
//Loop variables: The outer loop always controls the row, and the inner loop controls the content (stars or numbers).
//Conditionals: Not used in these basic patterns, but essential for more advanced/hollow patterns.
//Print statements: Used inside the inner loop for each value, and outside (after inner loop) for new lines.
