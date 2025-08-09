package Static_Instance;

// | Feature    | `static`             | Instance          |
//| ---------- | -------------------- | ----------------- |
//| Belongs to | Class                | Object            |
//| Memory     | Allocated once       | Per object        |
//| Called by  | `ClassName.method()` | `object.method()` |

public class Demo {
    static int count = 0;
    int id;

    Demo(int id) {
        this.id = id;
        count++;
    }

    void show() {
        System.out.println("ID : " + id);
        System.out.println("Total Objects : " + count);
    }

}
