package loops;

public class ArrayDemo {
    public static void main(String[] args) {
        int[] numbers = {10, 20, 30, 40, 50};

        System.out.println("Using for Loop");
        System.out.println();
        for (int i = 0; i < numbers.length; i++) {
            System.out.println("numbers =" + numbers[i]);
        }

        System.out.println();
        System.out.println("Using for each");
        System.out.println();
        for (int num : numbers) {
            System.out.println("Number Using for Each are = " + num);
        }
    }
}
