package Oops.Encapsulation;
//Create an Employee class with private fields. Use a constructor for initialization and getters/setters for encapsulation.
//Use private fields → Initialize with constructor → Access via getters/setters → Print values

public class Employee {
    private String name;
    private int id;

    public Employee(String name, int id) {
        this.name = name;
        this.id = id;
    }

    // Use getter and setter
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public static void main(String[] args) {
        Employee emp = new Employee("David", 101);
        System.out.println("The employee name is " + emp.getName());
        emp.setId(102);
        System.out.println("The employee id is : " + emp.getId());
    }
}

//Use private fields → Initialize with constructor → Access via getters/setters → Print values
//Encapsulation ensures that name and id can’t be accessed directly from outside.
//Constructor sets the initial state.
//Getters and setters manage access and updates, which helps in maintaining data integrity and making code safer.