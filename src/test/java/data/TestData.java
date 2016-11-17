package data;

import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertNull;

/**
 * Test the {@link Data} object methods and functionality
 */
public class TestData {
    private Data data;

    @Before
    public void setData() {
        data = new Data();
    }

    @Test
    public void insertDataWithCountryRatingKey() {
        data = new Data();
        String line = "2316\t1520670\tArhus\tDK\tNR\tUSD\t100";
        data.insertDataLine(line);
        assert (data.getKey().equals("DKNR"));
    }


    @Test
    public void nullData() {
        data = new Data();
        assertNull(data.getCountry());
        assertNull(data.getCountry());
    }

    @Test
    public void country() {
        data.setCountry("US");
        System.out.println(data.getCountry());
        assert (data.getCountry().equals("US"));
    }

    @Test
    public void nullCountry() {
        data = new Data();
        String line = "2316\t1520670\tArhus\t\tNR\tUSD\t100";
        data.insertDataLine(line);
        System.out.println(data.getCountry());
        System.out.println(data.getCity());
        assert (data.getCountry().equals(data.getCity()));
    }

    @Test
    public void average() {
        data = new Data();
        String line = "2316\t1520670\tArhus\tDK\tNR\tUSD\t100";
        data.insertDataLine(line);
        data.average(BigDecimal.valueOf(200));
        System.out.println(data.getAverage());
        assert (data.getAverage().doubleValue() == 150.0);
    }

}
