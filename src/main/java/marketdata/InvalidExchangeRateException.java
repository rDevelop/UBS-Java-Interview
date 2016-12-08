package marketdata;

/**
 * Thrown when an an invalid {@ExchangeRate} is used.
 */
public class InvalidExchangeRateException extends ExchangeRateException {
    private static final long serialVersionUID = 691209910497110103L;

    /**
     * Constructs a {@code InvalidExchangeRateException} with the specified
     * detail message.
     *
     * @param s the detail message.
     */
    public InvalidExchangeRateException(String s) {
        super(s);
    }
}
