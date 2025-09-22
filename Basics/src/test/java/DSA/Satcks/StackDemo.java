package DSA.Satcks;

class Stack {
    int maxSize;
    int stackArray[];
    int top;

    // Constructor
    Stack(int size) {
        maxSize = size;
        stackArray = new int[maxSize];
        top = -1; // Stack empty
    }

    //Push Operation
    void push(int value) {
        if (top == maxSize - 1) {
            System.out.println(" Stack overflow");
            return;
        }
        stackArray[++top] = value;
        System.out.println(value + " Pushed to stack");
    }

    // Pop Operation
    int pop() {
        if (top == -1) {
            System.out.println(" Stack UnderFlow");// Stack under flow
            return -1;
        }
        int popped = stackArray[top--];
        System.out.println(popped + " Poped from Stack");
        return popped;
    }

    // peek Operation
    int peek() {
        if (top == -1) {
            System.out.println(" Stack is empty");
            return -1;
        }
        return stackArray[top];
    }

    //isEmpty Operation
    boolean isEmpty() {
        return (top == -1);
    }
}

public class StackDemo {
    public static void main(String[] args) {
        Stack stack = new Stack(5);
        stack.push(10);
        stack.push(20);
        stack.push(30);

        System.out.println("The top element is : " + stack.peek()); // prints top element 30

        stack.pop(); // removes 30

        stack.pop();
        stack.pop();
        stack.pop(); // Stack Underflow


    }
}

// A stack is a linear data structure that follows the Last In, First Out (LIFO) principle:
//The last element added (pushed) is the first one to be removed (popped).
//Real-Life Examples
//Stacking plates: The last plate you put on the stack is the first one you take off.
//Undo operation in editors.
//Basic Stack Operations
//Push: Add (insert) an element to the top of the stack.
//Pop: Remove (delete) the top element from the stack.
//Peek (Top): View (but not remove) the top element.
//isEmpty: Check if the stack is empty.