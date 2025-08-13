package Static_Instance;

//| Modifier    | Accessible In Same Class | Same Package | Subclass | Other Packages |
//| ----------- | ------------------------ | ------------ | -------- | -------------- |
//| `private`   | ✅ Yes                    | ❌ No         | ❌ No     | ❌ No           |
//| `default`   | ✅ Yes                    | ✅ Yes        | ❌ No     | ❌ No           |
//| `protected` | ✅ Yes                    | ✅ Yes        | ✅ Yes    | ❌ No           |
//| `public`    | ✅ Yes                    | ✅ Yes        | ✅ Yes    | ✅ Yes          |

public class Main {
    public static void main(String[] args) {

        Demo d1 = new Demo(101);
        Demo d2 = new Demo(102);
        d1.show();
        d2.show();

    }
}
