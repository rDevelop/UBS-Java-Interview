package data;

import java.math.BigDecimal;

/**
 * AbstractData file abstraction all concrete classes need.
 */
public abstract class AbstractData implements Comparable {
    String company;
    String account;
    String city;
    String country;
    String creditRating;
    String currency;
    BigDecimal amount;
    BigDecimal average;

    int count;


    /**
     * Implement a method that will take a data line delimited by commas
     *
     * @param dataLine String of tab delimited data
     */
    public abstract void insertDataLine(String dataLine);

    /**
     * Need to return currency to loader for conversions
     * @return currency
     */
    abstract String getCurrency();

    /**
     * return a key for grouping
     *
     * @return hash key for Maps
     */
    protected abstract String getKey();

    /**
     * average amounts for lines with same key
     *
     * @param amount amount of single line that will be added to average
     */
    public abstract void average(BigDecimal amount);

    /**
     * returns country, which will be city if the file country is null.
     *
     * @return country
     */
    public String getCountry() {
        return country;
    }

    /**
     * sets the country (when the file country is null)
     * see {@linkplain #getCity()}.
     *
     * @param country Country from the file, Could be null.
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * returns city
     *
     * @return String The city from the file
     */
    public String getCity() {
        return city;
    }

    /**
     * returns the amount
     *
     * @return BigDecimal {@link #amount}
     */
    public BigDecimal getAmount() {
        return amount;
    }

    /**
     * returns the average amounts
     *
     * @return average of grouped Data lines
     */
    public BigDecimal getAverage() {
        return average;
    }

    /**
     * Implement equals for Map keys
     *
     * @param o Data
     * @return boolean
     */
    @Override
    public boolean equals(Object o) {
        if (o instanceof AbstractData) {
            String key = getKey();
            String oKey = ((AbstractData) o).getKey();
            return key.equals(oKey);
        }
        return false;
    }

    /**
     * Implement a compareTo method for sorted Maps
     *
     * @param o Data
     * @return -1 (less than), 0 (equals), 1 (greater than)
     */
    @Override
    public int compareTo(Object o) {
        if (o instanceof AbstractData) {
            String key = getKey();
            String oKey = ((AbstractData) o).getKey();
            return key.compareTo(oKey);
        }
        return -1;
    }


}
