package Practise.easy;
//A leap year is divisible by 4 but not by 100, or divisible by 400.
//The condition combines if and logical operators to check the rule.

public class LeapYear {
    public static void main(String[] args) {
        int year = 2012;
        if((year % 4==0 && year %100 !=0) || (year % 400 == 0)){
            System.out.println("leap year");
        }else {
            System.out.println("Not leap year");
        }
    }
}
//[Input year]
//      |
//      v
//[Is year % 4 == 0?]
//      |        \
//     Yes        No
//      |           \
//[Is year % 100 != 0?]    [Not leap year]
//      |         \
//     Yes         No
//      |            \
//[Leap year]   [Is year % 400 == 0?]
//                  |          \
//                 Yes         No
//                  |            \
//           [Leap year]   [Not leap year]