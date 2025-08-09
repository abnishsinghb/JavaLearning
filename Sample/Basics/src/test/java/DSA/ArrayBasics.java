package DSA;

public class ArrayBasics {
    // Declare and initialize
    public static void main(String[] args) {


    int arr[] = {1,2,3,4,5};

    // Access element
    System.out.println("Third element: " + arr[2]);

    // Modify element
        arr[2] = 10;

    // Traverse and print
        for(int i =0;i< arr.length;i++){
            System.out.print(arr[i]+ " ");
        }
        System.out.println();

    // Sum of elements
        int sum = 0;
        for(int num : arr){
            sum = sum + num;
        }
        System.out.println("The sum of array is : "+sum);
    }

}
