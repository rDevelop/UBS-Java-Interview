package marketdata;

import org.junit.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Test the basic functionality of the Exchange rates.
 */
public class TestExchangeRates {
    @Test
    public void printExchangeRates() {
        ExchangeRate CHF_USD = new ExchangeRate("CHF", "USD", BigDecimal.valueOf(1.10));
        ExchangeRate EUR_USD = new ExchangeRate("EUR", "USD", BigDecimal.valueOf(1.35));
        ExchangeRate GBP_USD = new ExchangeRate("GBP", "USD", BigDecimal.valueOf(1.654));
        System.out.println("Exchange rate " + CHF_USD);
        System.out.println("Exchange rate " + EUR_USD);
        System.out.println("Exchange rate " + GBP_USD);
        System.out.println(CHF_USD.printConversions());
        System.out.println(EUR_USD.printConversions());
        System.out.println(GBP_USD.printConversions());

        assert (CHF_USD.toString().equals("CHF/USD : 1.1"));
        assert (EUR_USD.toString().equals("EUR/USD : 1.35"));
        assert (GBP_USD.toString().equals("GBP/USD : 1.654"));
    }

    /**
     * 1 British pound equals U.S. dollar = 1.22339
     * 1 US Dollar equals 0.82 British Pound
     * <p>
     * 1 Euro equal 1.09 US Dollar
     * 1 US Dollar equals 0.92 Euro
     * <p>
     * 1 Swiss Franc equals 1.01 US Dollar
     * 1 US Dollar equals 0.99 Swiss Franc
     */
    @Test
    public void conversions() {
        ExchangeRate CHF_USD = new ExchangeRate("CHF", "USD", BigDecimal.valueOf(1.01));
        ExchangeRate EUR_USD = new ExchangeRate("EUR", "USD", BigDecimal.valueOf(1.09));
        ExchangeRate GBP_USD = new ExchangeRate("GBP", "USD", BigDecimal.valueOf(1.22339));
        assert (GBP_USD.toCurr1(BigDecimal.valueOf(1)).setScale(2, RoundingMode.CEILING).doubleValue() == BigDecimal.valueOf(.82).doubleValue());
        assert (CHF_USD.toCurr1(BigDecimal.valueOf(1)).setScale(2, RoundingMode.CEILING).doubleValue() == BigDecimal.valueOf(1).doubleValue());
        assert (EUR_USD.toCurr1(BigDecimal.valueOf(1)).setScale(2, RoundingMode.CEILING).doubleValue() == BigDecimal.valueOf(.92).doubleValue());

    }
}
