package strings;

public class Vowels_Consonants {
    public static void main(String[] args) {
        int vowels = 0;
        int consonants = 0;
        String str = "India is Great ";

        //convert all character to lowercase to avoid mismatch
        str = str.toLowerCase();

        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
//           check whether its character
            if (ch >= 'a' && ch <= 'z') {
                if (ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u') { // check its vowel
                    vowels++;
                } else {
                    consonants++;
                }
            }
        }
        System.out.println(" The number of vowels is : " + vowels);
        System.out.println("The number of consonants are : " + consonants);
    }
}
