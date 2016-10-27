package marketdata;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * This class is designed to hold currency pairs. The design only allows the constructor to create a new object.<br>
 *     Once the object is instantiated the toCurr1 and toCurr2 methods can be called.<br>
 *         Rate 1 is passed in. Rate 2 is derived from 1/Rate 1<br>
 *         toCurr methods take value of amounts in a currency to be converted to the opposite currency.<br>
 *             Getters and Setters can be added and the curr1 and curr2 strings aren't used for anything.
 */
public class ExchangeRate {
    private final String curr1;
    private final String curr2;
    private final BigDecimal rate1;
    private final BigDecimal rate2;


    /**
     * Constructor creates the ExchangeRate. This is the only way to instantiate an ExchangeRate<br>
     * Rate 2 is created when the constructor is called.
     * @param curr1 Currency 1
     * @param curr2 Currency 2
     * @param rate Rate to convert from Currency 1 to Currency 2
     */
    public ExchangeRate(String curr1, String curr2, BigDecimal rate) {
        this.curr1 = curr1;
        this.curr2 = curr2;
        this.rate1 = rate;
        this.rate2 = BigDecimal.valueOf(1 / rate1.doubleValue()).setScale(3, RoundingMode.CEILING);
    }

    public BigDecimal toCurr1(BigDecimal curr2value) {
        return curr2value.multiply(rate2).setScale(3, RoundingMode.CEILING);
    }

    public BigDecimal toCurr2(BigDecimal curr1value) {
        return curr1value.multiply(rate1).setScale(3, RoundingMode.CEILING);
    }

    @Override
    public String toString() {
        return curr1 + "/" + curr2 + " : " + rate1;
    }

    public String printConversions() {
        String  s = this.toString();
        s += "\n" + curr2 + "/" + curr1 + " : " + rate2;
        return s;
    }

}
