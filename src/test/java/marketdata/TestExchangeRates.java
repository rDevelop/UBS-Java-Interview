package marketdata;

import org.junit.Test;

import java.util.logging.Logger;

import static org.junit.Assert.assertTrue;

/**
 * Test the basic functionality of the Exchange rates.
 */
public class TestExchangeRates {
    /**
     * ensure string value of a new exchange rate matches what we expect
     */
    @Test
    public void francsToDollarStringValue() {
        double dFrancToDollar = 1.01;
        ExchangeRate exchangeRate = new ExchangeRate(
                "CHF", "USD", dFrancToDollar);
        assertTrue("CHF/USD : 1.01".equalsIgnoreCase(exchangeRate.toString()));
    }

    /**
     * When creating the string from a double we realize the trailing zeros will be lost.
     */
    @Test
    public void poundsToDollarStringValueWithTrailingZeros() {
        double dPoundsToDollar = 1.10;
        ExchangeRate exchangeRate = new ExchangeRate(
                "GBP", "USD", dPoundsToDollar);
        assertTrue("GBP/USD : 1.1".equals(exchangeRate.toString()));
    }

    /**
     * Try to convert different curr2 rates  - testing InvalidExchangeRateException
     */
    @Test
    public void invalidExchangeRate() {
        boolean pass = false;
        ExchangeRate e1 = new ExchangeRate("USD", "USD", 1.0);
        ExchangeRate e2 = new ExchangeRate("USD", "GBP", .8);
        try {
            ExchangeRate.getExchangeRate(e1, e2);
        } catch (ExchangeRateException e) {
            Logger.getLogger(TestExchangeRates.class.getName()).info(String.valueOf(e));
            assertTrue("Rate second currency don't equal, can't convert. From Rate:USD/USD : 1.0, To Rate:USD/GBP : 0.8"
                    .equals(e.getMessage()));
            pass = true;
        }
        assertTrue(pass);
    }

    /**
     * test NullExchangeRateException
     */
    @Test
    public void nullExchangeRate() {
        boolean pass = false;
        ExchangeRate e1 = new ExchangeRate("USD", "USD", 1.0);
        try {
            ExchangeRate.getExchangeRate(e1, null);
        } catch (ExchangeRateException e) {
            Logger.getLogger(TestExchangeRates.class.getName()).info(String.valueOf(e));
            assertTrue("Null rate(s) can't convert. From Rate:USD/USD : 1.0, To Rate:null"
                    .equals(e.getMessage()));
            pass = true;
        }
        assertTrue(pass);
    }


}
