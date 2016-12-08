package data;


import data.columns.Columns;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.logging.Level;
import java.util.logging.Logger;

import static java.lang.String.format;

/**
 * This class objectifies the data lines and performs the euro conversion and average.<br>
 * It overwrites the toString method so that nothing has to reformat it.<br>
 * <br>
 * <tt>count</tt> is an integer of the number of times an amount is loaded so that an average can be calculated<br>
 * <tt>amount</tt> is the amount value holding the total amount of this Data object<br>
 * <tt>average</tt> is the average amount
 */
public class Data extends AbstractData {
    private static final Logger logger = Logger.getLogger(Data.class.getName());

    /**
     * Constructor that takes no arguments and sets the amount counter and amounts to zero.
     */
    public Data() {
        count = 0;
        amount = new BigDecimal(0);
        average = new BigDecimal(0);
    }

    /**
     * Return currency for conversions
     *
     * @return currency
     */
    @Override
    public String getCurrency() {
        return currency;
    }

    /**
     * Key for grouping will be country + credit rating
     *
     * @return key
     */
    @Override
    public String getKey() {
        return country + creditRating;
    }

    /**
     * This method populates the Data class. <br>
     * It splits the dataLine on tabs and then uses the enum Column to attribute the fields.<br>
     * If country is null, it uses the city as country.
     *
     * @param dataLine String of tab delimited data
     */
    @Override
    public void insertDataLine(String dataLine) {
        String[] fields = dataLine.split("\t");
        if (fields.length == 0) {
            return;
        }
        for (Enum col : Columns.values()) {
            if ("AMOUNT".equals(col.name())) {
                try {
                    amount = new BigDecimal(fields[Columns.AMOUNT.ordinal()]);
                } catch (NumberFormatException e) {
                    logger.log(Level.WARNING, "Amount is set to zero, after this exception", e);
                    amount = new BigDecimal(0);
                }
                average = amount;
                count++;
            } else {
                city = fields[Columns.CITY.ordinal()];
                account = fields[Columns.ACCOUNT.ordinal()];
                country = fields[Columns.COUNTRY.ordinal()];
                currency = fields[Columns.CURRENCY.ordinal()];
                company = fields[Columns.COMPANY_CODE.ordinal()];
                creditRating = fields[Columns.CREDIT_RATING.ordinal()].toUpperCase();

            }
        }
        if ("".equals(country)) {
            country = city;
        }
    }

    /**
     * Averages the amount bases on number of times and amount is averaged with a new amount.
     *
     * @param amount amount of single line that will be added to average
     */
    @Override
    public void average(BigDecimal amount) {
        this.amount = this.amount.add(amount);
        count++;
        try {
            this.average = this.amount.divide(BigDecimal.valueOf(count), 2, RoundingMode.CEILING);
        } catch (Exception e) {
            this.average = this.amount;
            logger.log(Level.WARNING, "Amount is unchanged after this exception", e);
        }
    }

    /**
     * This method takes an amount and sets the amounts. Meant to set separately from averages and not count against count.
     *
     * @param amount the amount to set after currency conversions
     */
    public void setAmounts(BigDecimal amount) {
        this.amount = amount;
        this.average = amount;
    }

    /**
     * Override the toString to produce an easy printer
     *
     * @return the string formatted simply to country credit rating and average
     */
    @Override
    public String toString() {
        return format("%1$-12s", country) + "\t" + format("%1$-12s", creditRating) + "\t" + average;
    }

}

