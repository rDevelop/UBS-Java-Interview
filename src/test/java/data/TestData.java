package data;

import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

/**
 * Test the {@link Data} object methods and functionality
 */
public class TestData {
    /**
     * Key must be country + creditRating if country exists.
     */
    @Test
    public void getCountryRatingKey() {
        Data data = new Data();
        String line = "2316\t1520670\tArhus\tDK\tNR\tUSD\t100";
        data.insertDataLine(line);
        assertTrue("DKNR".equals(data.getKey()));
    }

    /**
     * Key must be city + creditRating if country is missing.
     */
    @Test
    public void getCityRatingKey() {
        Data data = new Data();
        String line = "2302\t4535851\tLondon\t\tA\tGBP\t456.85";
        data.insertDataLine(line);
        assertTrue("LondonA".equals(data.getKey()));
    }

    /**
     * No data should be populated upon Data object creation.
     */
    @Test
    public void nullData() {
        Data data = new Data();
        assertNull(data.getCountry());
    }

    /**
     * Getting and setting the country
     */
    @Test
    public void country() {
        Data data = new Data();
        data.setCountry("US");
        assertTrue("US".equals(data.getCountry()));
    }

    /**
     * When country is null, the getCountry method will equal the getCity
     * country = city, when country is null.
     */
    @Test
    public void nullCountry() {
        Data data = new Data();
        String line = "2316\t1520670\tArhus\t\tNR\tUSD\t100";
        data.insertDataLine(line);
        assertTrue(data.getCountry().equals(data.getCity()));
    }

    /**
     * Average the data
     */
    @Test
    public void average() {
        Data data = new Data();
        String line = "2316\t1520670\tArhus\tDK\tNR\tUSD\t100";
        data.insertDataLine(line);
        data.average(BigDecimal.valueOf(200));
        assertTrue(data.getAverage().intValue() == 150);
    }

}
