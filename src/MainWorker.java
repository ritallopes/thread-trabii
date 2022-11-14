import java.math.BigDecimal;
import java.util.concurrent.*;

/**
 * Calculo do numero de Euler usando Work stealing thread pool
 * @see java.util.concurrent.Executors
 * @author <a href="mailto:rita.lopes.705@ufrn.edu.br">Rita Lopes</a>
 */
public class MainWorker {
    public static void main(String [] args){
        int NUM_TERMS = 0;
        System.out.println("Concurrent calculation of an approximation for euler number\n");
        if (args.length != 1) {
            System.err.println("Invalid number of terms.");
            System.err.println("The program will be finished.");
            System.exit(1);
        }else{
            NUM_TERMS = Integer.parseInt(args[0]);
        }
        ExecutorService executor =
                Executors.newSingleThreadExecutor();
        BigDecimal euler = new BigDecimal(1);
        for (int i = 1; i <= NUM_TERMS; i++) {
            Callable<BigDecimal> calculator = new CalculationTermsCallable(i);
            Future<BigDecimal> future = executor.submit(calculator);
            try {
                euler = euler.add(future.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
        int active = Thread.activeCount();
        System.out.println("N Threads: "+ active);

        executor.shutdown();
        System.out.println(euler);
    }
}
