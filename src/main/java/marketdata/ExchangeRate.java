package marketdata;

/**
 * This class is designed to hold currency pairs. The design only allows the constructor to create a new object.<br>
 * Once the object is instantiated the toCurr1 and toCurr2 methods can be called.<br>
 * Rate 1 is passed in. Rate 2 is derived from 1/Rate 1<br>
 * toCurr methods take value of amounts in a currency to be converted to the opposite currency.<br>
 * Getters and Setters can be added and the curr1 and curr2 strings aren't used for anything.
 */
public class ExchangeRate {
    private final String curr1;
    private final String curr2;
    private final Double rate;


    /**
     * Constructor creates the ExchangeRate. This is the only way to instantiate an ExchangeRate<br>
     * Rate 2 is created when the constructor is called.
     *
     * @param curr1 Currency 1
     * @param curr2 Currency 2
     * @param rate  Rate to convert from Currency 1 to Currency 2
     */
    public ExchangeRate(String curr1, String curr2, Double rate) {
        this.curr1 = curr1;
        this.curr2 = curr2;
        this.rate = rate;
    }

    /**
     * getExchangeRate requires 2 not-null rates. Both rates 2nd currency must match, and Rate 2 can't be zero.
     *
     * @param from ExchangeRate
     * @param to   ExchangeRate
     * @return Double - converted exchangeRate value
     * @throws ExchangeRateException
     */
    public static double getExchangeRate(ExchangeRate from, ExchangeRate to) throws ExchangeRateException {
        if (to == null || from == null) {
            throw new NullExchangeRateException("Null rate(s) can't convert. From Rate:" + from + ", To Rate:" + to);
        } else if (!from.getCurr2().equals(to.getCurr2())) {
            throw new InvalidExchangeRateException(
                    "Rate second currency don't equal, can't convert. From Rate:" + from + ", To Rate:" + to);
        } else if (to.getRate() == 0) {
            throw new InvalidExchangeRateException("To rate is zero. Can't convert.");
        }
        return from.getRate() / to.getRate();
    }

    /**
     * Need to check currency to for exchange rate conversions
     *
     * @return curr2
     */
    public String getCurr2() {
        return curr2;
    }

    /**
     * Rates will be used to get conversions.
     *
     * @return converted rate.
     */
    public Double getRate() {
        return rate;
    }

    @Override
    public String toString() {
        return curr1 + "/" + curr2 + " : " + rate;
    }
}
