package Oops.misc;

// Encapsulation = data hiding. We make variables private and provide public getters/setters to access them.

public class Main1 {
    public static void main(String[] args) {

        Employee e = new Employee();
        e.setName("Ravikant");
        e.setSalary(75000);

        System.out.println(e.getName());
        System.out.println(e.getSalary());

    }
}
