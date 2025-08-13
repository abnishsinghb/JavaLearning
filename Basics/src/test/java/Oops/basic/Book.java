package Oops.basic;
//
//Define Book class → Create object (book1) → Assign values → Call displayInfo()
public class Book {
    String title ;
    String author;

    void display(){
        System.out.println("THE book title is : "+title);
        System.out.println("The author is : "+author);
    }

    public static void main(String[] args) {
        Book book = new Book();
        book.title ="Junle Book";
        book.author="Rudyard Kripling";
        book.display();
    }
}

//Class: Template defining properties and behavior (Book)
//Object: Instance of a class (book1)
//Member Variables: title, author
//Method: displayInfo() prints details
