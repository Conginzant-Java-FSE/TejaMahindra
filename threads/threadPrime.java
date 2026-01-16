class PrimeThread extends Thread {

    int start, end;

    PrimeThread(int start, int end) {
        this.start = start;
        this.end = end;
    }

    boolean isPrime(int num) {
        if (num <= 1)
            return false;

        for (int i = 2; i <= num / 2; i++) {
            if (num % i == 0)
                return false;
        }
        return true;
    }

    public void run() {
        for (int i = start; i <= end; i++) {
            if (isPrime(i)) {
                System.out.println(i + " printed by " + Thread.currentThread().getName());
            }
        }
    }
}

public class threadPrime {
    public static void main(String[] args) {

        int range = 100;

        PrimeThread[] threads = new PrimeThread[10];

        int start = 1;

        for (int i = 0; i < 10; i++) {
            int end = start + range - 1;
            threads[i] = new PrimeThread(start, end);
            threads[i].start();
            start = end + 1;
        }

        for (int i = 0; i < 10; i++) {
            try {
                threads[i].join();
            } catch (Exception e) {
                System.out.println(e);
            }
        }

        System.out.println("All threads completed");
    }
}
