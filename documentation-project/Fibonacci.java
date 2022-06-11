public class Fibonacci {

    static int fibonacciRecursion(int number) {
        if (number < 2) {
            return number;
        }
        return fibonacciRecursion(number - 1) + fibonacciRecursion(number - 2);
    }

    static int fibonacciIterative(int number) {
        if (number < 2) {
            return number;
        }
        int x = 0;
        int y = 1;
        int z = 1;

        for(int i = 0; i < number; i++) {
            x = y;
            y = z;
            z = x + y;
        }
        return x;
    }

}
