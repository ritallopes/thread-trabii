import java.math.BigDecimal;
import java.math.MathContext;
import java.util.List;
import java.util.concurrent.Callable;
/**
 * Calculo de numero 1 dividido por fatorial de um número implementando a interface Callable
 * @see java.util.concurrent.Callable
 * @author <a href="mailto:rita.lopes.705@ufrn.edu.br">Rita Lopes</a>
 */
public class CalculationTermsCallable implements Callable {
    private int i;
    private List<BigDecimal> terms;
    public CalculationTermsCallable(int i){
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
    public BigDecimal call(){
        BigDecimal divisor = calculeFatorial(new BigDecimal(i));
        BigDecimal term = new BigDecimal(1).divide(divisor, MathContext.DECIMAL128);
        return term;
    }
}
