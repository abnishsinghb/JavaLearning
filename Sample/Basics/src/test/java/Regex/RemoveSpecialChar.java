package Regex;

public class RemoveSpecialChar {
    public static void main(String[] args) {
        String str = " Hello@#4545World";
        String cleaned = str.replaceAll("[^a-zA-Z0-9]", ""); // removing Special character

        System.out.println(" Cleaned String " + cleaned);


    }
}
