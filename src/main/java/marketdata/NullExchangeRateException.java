package marketdata;

/**
 * Thrown when an a null {@ExchangeRate} is used.
 */
public class NullExchangeRateException extends ExchangeRateException {
    private static final long serialVersionUID = 781171081086912099L;

    /**
     * Constructs a {@code NullExchangeRateException} with the specified
     * detail message.
     *
     * @param s the detail message.
     */
    public NullExchangeRateException(String s) {
        super(s);
    }
}
