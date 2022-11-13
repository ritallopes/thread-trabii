import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {
        int NUM_THREADS = 0;
        System.out.println("Concurrent calculation of an approximation for euler number\n");
        if (args.length != 1) {
            System.err.println("Invalid number of terms.");
            System.err.println("The program will be finished.");
            System.exit(1);
        }else{
            NUM_THREADS = Integer.parseInt(args[0]);
        }
        ExecutorService executor = Executors.newFixedThreadPool(NUM_THREADS);
        List<Double> terms = Collections.synchronizedList(new ArrayList<Double>());
        for (int i = 1; i <= NUM_THREADS; i++) {
            Runnable calc = new Calc(i, terms);
            executor.execute(calc);
        }
        int active = Thread.activeCount();
        System.out.println(active);
        executor.shutdown();

        double euler = 1;
        for (Double d: terms
             ) {
            euler += d;
        }
        System.out.println(euler);
    }
}