package Oops.Interface;

interface Flyable {
    void fly();
}

class Bird implements Flyable {
    public void fly() {
        System.out.println("Bird is Flying");
    }

}

