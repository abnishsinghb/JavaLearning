package FileHandling;

import javax.imageio.IIOException;
import java.io.FileWriter;
import java.io.IOException;

public class WrriteFileExample {
    public static void main(String[] args) {
        try {
            FileWriter writer = new FileWriter("students.txt");
            writer.write("Abhin, 85 \n");
            writer.write("sneha,  68 \n");
            writer.write("rahul, 58 \n");
            writer.close();
            System.out.println("File written succesfully");
        } catch (IOException e) {
            System.out.println("Error : " + e.getMessage());
        }
    }
}
