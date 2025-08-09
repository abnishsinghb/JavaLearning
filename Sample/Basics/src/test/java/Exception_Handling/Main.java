package Exception_Handling;
// An exception is an unexpected event that disrupts normal flow of code.
//
//Common Java exceptions:
//
//ArithmeticException (e.g. divide by 0)
//
//NullPointerException
//
//ArrayIndexOutOfBoundsException
//
//NumberFormatException

//try {
//    // Code that may throw an exception
//} catch (ExceptionType e) {
//    // Handle the exception
//} finally {
//    // Optional: always runs
//}

public class Main {
    public static void main(String[] args) {
        try {

            int a = 10;
            int b = 0;
            int result = a / b;

            System.out.println("Result :  " + result);
        } catch (ArithmeticException e) {
            System.out.println("Error cannot divide by zero");
        } finally {
            System.out.println("Successfully divided by zero");
        }
    }
}
