public class Main {
    public static void main(String[] args) {

        long startTime;
        long endTime;
        long duration;

        //Calculates the fibonacci rule using the recursive process
        System.out.println("\nData for Recursive Fibonacci\nx = input, y = time in nanoseconds");
        for (int i = 0; i <= 20; i++) {
            startTime = System.nanoTime();
            int fibResultR = Fibonacci.fibonacciRecursion(i);
            endTime = System.nanoTime();
            duration = endTime - startTime;
            System.out.println("result = " + fibResultR + " x = " + i + " y = " + duration + " ns");
        }

        //Calculates the fibonacci rule using the iterative process
        System.out.println("\nData for Iterative Fibonacci\nx = input, y = time in nanoseconds");
        for (int i = 0; i <= 20; i++) {
            startTime = System.nanoTime();
            int fibResultI = Fibonacci.fibonacciIterative(i);
            endTime = System.nanoTime();
            duration = endTime - startTime;
            System.out.println("result = " + fibResultI + " x = " + i + " y = " + duration + " ns");
        }
    }
}
