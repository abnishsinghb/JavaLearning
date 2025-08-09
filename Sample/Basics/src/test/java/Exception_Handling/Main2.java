package Exception_Handling;

//| Keyword   | Use                               |
//| --------- | --------------------------------- |
//| `try`     | Wrap risky code                   |
//| `catch`   | Handle exceptions                 |
//| `finally` | Always executes                   |
//| `throw`   | Manually throw an exception       |
//| `throws`  | Declare that a method might throw |

public class Main2 {

    public static void riskyMethod() throws InterruptedException {
        Thread.sleep(1000); //throws a checked Exception
        System.out.println("Task Completed ");
    }

    public static void main(String[] args) {
        try {
            riskyMethod();
        } catch (InterruptedException e) {
            System.out.println("Interrupted " + e.getMessage());
        }
    }
}

