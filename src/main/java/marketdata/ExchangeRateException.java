package marketdata;

/**
 * ExchangeRageException to identify exceptions with ExchangeRates
 */
public class ExchangeRateException extends Exception {
    private static final long serialVersionUID = 691209910497110103L;

    /**
     * Constructs a {@code ExchangeRateException} with the specified
     * detail message.
     *
     * @param s the detail message.
     */
    public ExchangeRateException(String s) {
        super(s);
    }
}
