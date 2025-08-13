package FileHandling;

// FileWriter writer = new FileWriter("students.txt", true); // true = append mode
//writer.write("Neha, 73\n");

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ReadFileExamplle {
    public static void main(String[] args) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("students.txt"));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println("Read ; " + line);
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("Error : " + e.getMessage());
        }
    }
}
