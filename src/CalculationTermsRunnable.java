import java.math.BigDecimal;
import java.math.MathContext;
import java.util.List;
/**
 * Calculo de numero 1 dividido por fatorial de um número implementando a interface Runnable
 * @see java.lang.Runnable
 * @author <a href="mailto:rita.lopes.705@ufrn.edu.br">Rita Lopes</a>
 */
public class CalculationTermsRunnable implements Runnable{
    private int i;
    private List<BigDecimal> terms;
    /**
     * Construtor parametrizado
     * @param i Numero do termo a ser calculado
     * @param terms lista de termos
     */
    public CalculationTermsRunnable(int i, List<BigDecimal> terms){
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
