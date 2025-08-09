package Exception_Handling;

//throw is used to explicitly throw an exception.
//
//throws is used to declare an exception might be thrown.

public class Main1 {

    public static void checkAge(int age) {
        if (age < 18) {
            throw new IllegalArgumentException("Age must be 18 atleast ");
        }
        System.out.println("Access Granted");
    }

    public static void main(String[] args) {
        checkAge(16);

    }
}
