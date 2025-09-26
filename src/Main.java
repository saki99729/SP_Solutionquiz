import java.util.Arrays;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        //problem 01
        int[] numbers = {1, 2, 3, 4, 5}; // Example input array

        System.out.println("problem 01. Sum with for loop : " + sumWithForLoop(numbers));
        System.out.println("problem 01. Sum with while loop : " + sumWithWhileLoop(numbers));
        System.out.println("problem 01. Sum with recursion : " + sumWithRecursion(numbers));

        // Demonstration for Problem 02
        String[] arr1 = {"a", "b", "c"}; //sample input
        int[] arr2 = {1, 2, 3}; //sample input

        //function
        String[] alternated = alternateArrays(arr1, arr2);

        System.out.print("problem 02 .Alternated array: [");
        for (int i = 0; i < alternated.length; i++) {
            System.out.print(alternated[i]);
            if (i < alternated.length - 1) System.out.print(", ");
        }
        System.out.println("]");

        // Problem 03

        int n = 10; // Example input for Fibonacci terms
        printFibonacci(n);

        // Problem 04
        // Example input for largest number
        int[] numArray = {50, 2, 1, 9};
        System.out.println(" problem 04 .Largest number: " + largestNumber(numArray));

        // Problem 05
        // Find expressions that evaluate to 100
        System.out.println("problem 05. Expressions for 100:");
        findExpressions("123456789", 100);
    }



    /*
    Problem Statement: Problem 01
     */

    // Function to compute sum using a for loop
    public static int sumWithForLoop(int[] numbers) {
        int sum = 0;
        for (int num : numbers) {
            sum += num;
        }
        return sum;
    }

    // Function to compute sum using a while loop
    public static int sumWithWhileLoop(int[] numbers) {
        int sum = 0;
        int i = 0;
        while (i < numbers.length) {
            sum += numbers[i];
            i++;
        }
        return sum;
    }

    // Function to compute sum using recursion
    public static int sumWithRecursion(int[] numbers) {
        return sumRec(numbers, 0);
    }

    private static int sumRec(int[] numbers, int index) {
        if (index >= numbers.length) {
            return 0;
        }
        return numbers[index] + sumRec(numbers, index + 1);
    }

    /*
    Problem Statement: Problem 02
    */

    // Function to alternate elements from two arrays
    public static String[] alternateArrays(String[] arr1, int[] arr2) {
        int len1 = arr1.length;
        int len2 = arr2.length;
        String[] result = new String[len1 + len2];
        int i = 0, j = 0, k = 0;
        while (i < len1 && j < len2) {
            result[k++] = arr1[i++];
            result[k++] = String.valueOf(arr2[j++]);
        }
        while (i < len1) {
            result[k++] = arr1[i++];
        }
        while (j < len2) {
            result[k++] = String.valueOf(arr2[j++]);
        }
        return result;
    }

    /*
     Problem Statement: Problem 03
     */

    // Function to print Fibonacci numbers up to n terms
    public static void printFibonacci(int n) {
        if (n <= 0) {
            System.out.println("No Fibonacci numbers to show.");
            return;
        }
        int a = 0, b = 1;
        System.out.print("problem 03. Fibonacci numbers: [");
        for (int i = 0; i < n; i++) {
            System.out.print(a);
            if (i < n - 1) System.out.print(", ");
            int next = a + b;
            a = b;
            b = next;
        }
        System.out.println("]");
    }

    /*
    Problem Statement: Problem 04
     */

    // Function to form the largest number from an array of integers
    public static String largestNumber(int[] nums) {
        if (nums == null || nums.length == 0) {
            return "";
        }else {
            String[] arr = new String[nums.length];
            for (int i = 0; i < nums.length; i++) {
                arr[i] = String.valueOf(nums[i]);
            }
            java.util.Arrays.sort(arr, (a, b) -> (b + a).compareTo(a + b));
            if (arr[0].equals("0")) return "0";
            StringBuilder sb = new StringBuilder();
            for (String s : arr) sb.append(s);
            return sb.toString();
        }
    }

    /*
    Problem Statement: Problem 05
     */

    // Function to find all expressions that evaluate to 100
    public static void findExpressions(String digits, int target) {
        findExpressionsHelper(digits, target, 0, "", 0, 0);
    }

    // Improved helper for correct concatenation handling
    private static void findExpressionsHelper(String digits, int target, int pos, String expr, long lastOperand, long currValue) {
        if (pos == digits.length()) {
            if (currValue == target) {
                System.out.println(expr + " = " + target);
            }
            return;
        }
        for (int i = pos + 1; i <= digits.length(); i++) {
            String part = digits.substring(pos, i);
            long num = Long.parseLong(part);
            if (pos == 0) {
                findExpressionsHelper(digits, target, i, part, num, num);
            } else {
                findExpressionsHelper(digits, target, i, expr + "+" + part, num, currValue + num);
                findExpressionsHelper(digits, target, i, expr + "-" + part, -num, currValue - num);
                long newLastOperand = (lastOperand >= 0 ? lastOperand * (long)Math.pow(10, part.length()) + num : lastOperand * (long)Math.pow(10, part.length()) - num);
                long newCurrValue = currValue - lastOperand + newLastOperand;
                findExpressionsHelper(digits, target, i, expr + part, newLastOperand, newCurrValue);
            }
        }
    }
}
