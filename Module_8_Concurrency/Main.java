import java.util.Random;

public class Main extends Thread  {

    static int SIZE = 200000000;
    static int THREADS = 4;

    private static int[] array = new int[SIZE];
    private static int[] sumArray = new int[THREADS];

    public static void main(String[] args) throws InterruptedException {
        Random random = new Random();
        for(int i = 0; i < SIZE; i++) {
            array[i] = random.nextInt(10) + 1;
        }

        long start = System.currentTimeMillis();
        Main[] threads = new Main[THREADS];
        for(int i = 0; i < THREADS; i++) {
            threads[i] = new Main(i * (SIZE / THREADS), ((i + 1) * (SIZE / THREADS)));
            threads[i].start();
        }
        for(int i = 0; i < THREADS; i++) {
            threads[i].join();
        }
        int sum = 0;
        for(int i = 0; i < THREADS; i++) {
            sum += sumArray[i];
        }
        System.out.println("Sum of Array elements = " + sum);
        System.out.println("Total Time in millis = " + (System.currentTimeMillis() - start));

        start = System.currentTimeMillis();
        int sumTwo = 0;
        for(int i = 0; i < SIZE; i++) {
            sumTwo += array[i];
        }
        System.out.println("Sum of Array elements = " + sumTwo);
        System.out.println("Total Time in millis = " + (System.currentTimeMillis() - start));
    }

    private int start, end;

    private Main(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public void run() {
        int sum = 0;
        for(int i = start; i < end; i++) {
            sum += array[i];
        }
        sumArray[(int)(Thread.currentThread().getId() % THREADS)] = sum;
    }
}