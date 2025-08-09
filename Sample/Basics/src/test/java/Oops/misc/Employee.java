package Oops.misc;

// Encapsulation = data hiding. We make variables private and provide public getters/setters to access them.

public class Employee {
    private String name;
    private double salary;

    //Getter
    public String getName() {
        return name;
    }

    //setter
    public void setName(String n) {
        name = n;
    }

    //Getter
    public double getSalary() {
        return salary;
    }

    //Setter
    public void setSalary(double s) {
        salary = s;
    }

}
