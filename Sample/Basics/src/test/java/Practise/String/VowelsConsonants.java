package Practise.String;

import java.util.Scanner;

public class VowelsConsonants {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // Step 1: Input a string
        System.out.println("Enter the String");
        String str = sc.nextLine();

        // Step 2: Convert to lowercase to simplify comparison

        str = str.toLowerCase();

        int vowels = 0, consonants = 0;

        // Step 3: Loop through each character
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);

            // Step 4: Check if character is a letter
            if (ch >= 'a' && ch <= 'z') {
                // Step 5: Check if it is a vowel
                if (ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u') {
                    vowels++;
                } else {
                    consonants++;
                }
            }
        }
        System.out.println("Total count of vowels is : " + vowels);
        System.out.println("Total count of consonants is : " + consonants);
    }
}

//[Start]
//   |
//   v
//[Prompt user: "Enter the String"]
//   |
//   v
//[Read str]
//   |
//   v
//[str = str.toLowerCase()]
//   |
//   v
//[vowels = 0, consonants = 0]
//   |
//   v
//[For i = 0 to str.length()-1]
//   |
//   v
//[ch = str.charAt(i)]
//   |
//   v
//[Is ch a letter? (ch >= 'a' && ch <= 'z')]
//   |            \
//  Yes            No
//   |              \
//[Is ch a vowel?   [continue loop]
//(ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u')]
//   |            \
//  Yes            No
//   |              \
//[vowels++ ]     [consonants++ ]
//   |
//   v
//(repeat for all i)
//   |
//   v
//[Print "Total count of vowels is : " + vowels]
//[Print "Total count of consonants is : " + consonants]
//   |
//   v
//[End]