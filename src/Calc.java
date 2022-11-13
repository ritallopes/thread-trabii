import java.util.ArrayList;
import java.util.List;

public class Calc implements Runnable{
    private int i;
    private List<Double> terms;
    public Calc(int i, List<Double> terms){
        this.i = i;
        this.terms = terms;
    }

    @Override
    public void run(){
        double div = 1;
        for (int n = 1; n <= i; n++){
            div = div * n;
        }
        double term = 1/div;
        terms.add(term);
    }
}
