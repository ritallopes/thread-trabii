import java.math.BigDecimal;
import java.math.MathContext;
import java.util.List;

public class CalculationTerms implements Runnable{
    private int i;
    private List<BigDecimal> terms;
    public CalculationTerms(int i, List<BigDecimal> terms){
        this.i = i;
        this.terms = terms;
    }
    public BigDecimal calculeFatorial(BigDecimal n){
        if(n.compareTo(new BigDecimal(1)) == 0){
            return n;
        }else{
            return n.multiply(calculeFatorial(n.subtract(new BigDecimal(1))));
        }
    }

    @Override
    public void run(){
        BigDecimal divisor = calculeFatorial(new BigDecimal(i));
        BigDecimal term = new BigDecimal(1).divide(divisor, MathContext.DECIMAL128);
        terms.add(term);
    }
}
