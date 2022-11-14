import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainFixed {
    public static void main(String[] args) {
        int NUM_THREADS = 0;
        int NUM_TERMS = 0;
        System.out.println("Concurrent calculation of an approximation for euler number\n");
        if (args.length != 2) {
            System.err.println("Invalid number of terms.");
            System.err.println("The program will be finished.");
            System.exit(1);
        }else{
            NUM_THREADS = Integer.parseInt(args[0]);
            NUM_TERMS = Integer.parseInt(args[1]);
        }
        ExecutorService executor = Executors.newFixedThreadPool(NUM_THREADS);
        List<BigDecimal> terms = Collections.synchronizedList(new ArrayList<BigDecimal>());
        for (int i = 1; i <= NUM_TERMS; i++) {
            Runnable calc = new CalculationTerms(i, terms);
            executor.execute(calc);
        }
        int active = Thread.activeCount();
        System.out.println("Threads ativas: "+ active);
        executor.shutdown();

        BigDecimal euler = new BigDecimal(1);
        for (BigDecimal d: terms
             ) {
            euler = euler.add(d);
        }
        System.out.println(euler);
    }
}