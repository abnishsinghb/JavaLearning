package Regex;

import java.util.Scanner;
// | Email Part       | Accepted Characters          | Required? |
//| ---------------- | ---------------------------- | --------- |
//| Username         | Letters, digits, `. _ % + -` | ✅ Yes     |
//| `@`              | Must be present              | ✅ Yes     |
//| Domain name      | Letters, digits, dot, dash   | ✅ Yes     |
//| Dot (`.`)        | Must be after domain         | ✅ Yes     |
//| TLD (`com`, etc) | 2–6 letters                  | ✅ Yes     |

public class EmailValidation {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the email id : ");
        String email = sc.nextLine();

        String regex = "^[a-zA-z0-9.+-_%]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$";
        if (email.matches(regex)) {
            System.out.println("Valid email");
        } else {
            System.out.println("Invalid email");
        }
    }
}

// 1. ^
//This means: start of the string
//
//Ensures matching begins at the beginning of the input
//
//🔍 2. [a-zA-Z0-9._%+-]+
//This is the username part (before @)
//
//Allowed characters:
//
//a-z → lowercase letters
//
//A-Z → uppercase letters
//
//0-9 → digits
//
//. (dot), _ (underscore), %, +, -
//
//+ means 1 or more characters are required
//
//✅ Examples it allows:
//
//john.doe
//
//user_123
//
//abc%xyz
//
//❌ Examples it rejects:
//
//Empty username: @gmail.com
//
//🔍 3. @
//This must be present in all valid emails.
//
//Separates the username and domain.
//
//🔍 4. [a-zA-Z0-9.-]+
//This is the domain name part (after @, before dot)
//
//Allows:
//
//Letters (lower and upper)
//
//Digits
//
//Dots . and dashes -
//
//✅ Examples it allows:
//
//gmail
//
//yahoo.co
//
//my-server.123
//
//🔍 5. \\.
//This represents a literal dot (.).
//
//We escape dot using \\. because dot means "any character" in regex.
//
//🔍 6. [a-zA-Z]{2,6}
//This is the top-level domain (TLD) part, like:
//
//.com, .in, .net, .org, .co, .info
//
//[a-zA-Z] → only letters allowed
//
//{2,6} → must be between 2 and 6 letters
//
//✅ Examples it allows:
//
//com, in, org, co.in, email.gov
//
//❌ Examples:
//
//abc@domain.c (too short)
//
//abc@domain.companyxyz (too long)
//
//🔍 7. $
//This means: end of string
//
//Ensures there is nothing after the domain (e.g., no @gmail.com123)