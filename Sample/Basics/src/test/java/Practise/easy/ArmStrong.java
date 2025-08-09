package Practise.easy;

// A number is Armstrong if the sum of its digits raised to the power of number of digits equals the number itself.

public class ArmStrong {
    public static void main(String[] args) {

        int num = 153;
        int sum = 0;
        int count = 0;
        int original = num;

        //calculate number of digits

        int temp = num;
        while (temp != 0) {
            count++;
            temp = temp / 10;
        }

        // calculate sum of digits raised to power 3
        temp = num;
        while (temp != 0) {
            int digit = temp % 10;
            sum += Math.pow(digit, count);
            temp = temp / 10;
        }

        // Check and print result
        if (sum == original) {
            System.out.println("The number is Armstrong");
        } else {
            System.out.println("Not an Armstromg number");
        }
    }
}

//```
//[Start]
//   |
//   v
//[Initialize: num=153, sum=0, count=0, original=num]
//   |
//   v
//[Set temp = num]
//   |
//   v
//+-------------------------------+
//|   Count number of digits      |
//+-------------------------------+
//   |
//   v
//[Is temp != 0?] --No--> [Done counting]
//   |
//  Yes
//   |
//   v
//[count = count + 1]
//[temp = temp / 10]
//   |
//  (repeat until temp == 0)
//   |
//   v
//[Set temp = num]
//   |
//   v
//+----------------------------------------------+
//|   Calculate sum of digits raised to 'count'  |
//+----------------------------------------------+
//   |
//   v
//[Is temp != 0?] --No--> [Done summing]
//   |
//  Yes
//   |
//   v
//[digit = temp % 10]
//[sum = sum + Math.pow(digit, count)]
//[temp = temp / 10]
//   |
//  (repeat until temp == 0)
//   |
//   v
//[Is sum == original?]
//   |             \
//  Yes             No
//   |               \
//   v                v
//[Print              [Print
//"The number         "Not an Armstrong
//is Armstrong"]      number"]
//   |                |
//   v                v
//               [End]
//   |
//   v
// [End]
//```