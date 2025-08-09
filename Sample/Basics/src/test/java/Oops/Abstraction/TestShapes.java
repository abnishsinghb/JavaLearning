package Oops.Abstraction;
//Abstract class Shape (with area()) → Subclasses Circle, Rectangle implement area() → Use polymorphism to calculate area
abstract class Shapes {
    abstract double area();
}

class Circle extends Shapes {
    double radius;

    Circle(double r) {
        radius = r;
    }

    double area() {
        return Math.PI * radius * radius;
    }
}

class Rectangle extends Shapes {
    double length, width;

    Rectangle(double l, double w) {
        length = l;
        width = w;
    }


    double area() {
        return length * width;
    }
}

public class TestShapes {
    public static void main(String[] args) {
        Shapes s1 = new Circle(3);
        Shapes s2 = new Rectangle(2, 4);
        s1.area();
        s2.area();
    }
}

//Abstraction hides implementation details and exposes a contract via abstract methods.
//Abstract classes cannot be instantiated directly.
//Forces uniformity in subclasses, making the codebase flexible and future-proof.