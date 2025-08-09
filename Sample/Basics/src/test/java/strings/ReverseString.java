package strings;

public class ReverseString {
    public static void main(String[] args) {
        String str = "abnish";

        StringBuilder sb = new StringBuilder(str); // String is immutable hence using S Builder so that can use inbuilt reverse() method
        String rev = sb.reverse().toString();
        System.out.println("The orginal String : " + str);
        System.out.println("Reversed String is : " + rev);
    }
}

//[Start]
//   |
//   v
//[Set str = "abnish"]
//   |
//   v
//[Create StringBuilder(sb) with str]
//   |
//   v
//[rev = sb.reverse().toString()]    // Reverse using inbuilt method
//   |
//   v
//[Print "The original String : " + str]
//[Print "Reversed String is : " + rev]
//   |
//   v
//[End]