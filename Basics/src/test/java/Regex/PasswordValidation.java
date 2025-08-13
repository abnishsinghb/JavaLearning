package Regex;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
// | Part                         | Meaning                                |
//| ---------------------------- | -------------------------------------- |
//| `^`                          | Start of string                        |
//| `(?=.*[a-z])`                | At least one **lowercase** letter      |
//| `(?=.*[A-Z])`                | At least one **uppercase** letter      |
//| `(?=.*\\d)`                  | At least one **digit**                 |
//| `(?=.*[@#$%^&+=!])`          | At least one **special character**     |
//| `[A-Za-z\\d@#$%^&+=!]{8,16}` | All allowed characters, length 8 to 16 |
//| `$`                          | End of string                          |

public class PasswordValidation {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the password : ");
        String pwd = sc.nextLine();

        String regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*_+])[A-Za-z\\d@#$%^&+=!]{8,16}$";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(pwd);

        if (matcher.matches()) {
            System.out.println(" ✅  Strong Password");
        } else {
            System.out.println(" ❌  Weak password");
        }
    }
}
