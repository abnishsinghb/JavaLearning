package Basics;

import java.util.Locale;

public class StringDemo {
    public static void main(String[] args) {
        String name = "Java Learner";

        System.out.println("Lowercase " + name.toLowerCase());
        System.out.println("UpperCase " + name.toUpperCase(Locale.ROOT));
        System.out.println("String contains " + name.contains("ni"));
        System.out.println("String Character @ " + name.charAt(0));
        System.out.println("String Contains Learn " + name.contains("Learn"));
        System.out.println("SubString are " + name.substring(5, 12));
        System.out.println();
    }
}
