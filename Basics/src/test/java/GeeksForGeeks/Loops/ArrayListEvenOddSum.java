package GeeksForGeeks.Loops;

import java.util.ArrayList;

class Solutions {
    static ArrayList<Integer> getSum(int N) {
        // code here

        int evenSum = 0;
        int oddSum = 0;
        for (int i = 0; i <= N; i++) {
            if (i % 2 == 0) {
                evenSum = evenSum + i;
            } else {
                oddSum = oddSum + i;
            }
        }
        ArrayList<Integer> result = new ArrayList<>();
        result.add(evenSum);
        result.add(oddSum);
        return result;
    }
}

public class ArrayListEvenOddSum {
    public static void main(String[] args) {
        int N = 1; // You can change this value for different test cases
        ArrayList<Integer> res = Solutions.getSum(N);
        System.out.println(res.get(0) + " " + res.get(1));
    }

}
