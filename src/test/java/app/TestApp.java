package app;

import data.Data;
import loader.DataLoader;
import marketdata.ExchangeRate;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Test class to test main method from {@link App}<br>
 * <b>Untested</b>Set filename from arguments if they are passed, or use the default location from exercise zip file.
 * Create a {@link DataLoader} object.<br>
 * Loads three {@link ExchangeRate} objects per requirements. {@link DataLoader#addExchangeRate(String, ExchangeRate)}<br>
 * Implement a {@link HashMap} and a {@link TreeMap} to contain the file contents, from {@link DataLoader#readFileAsString(String)}<br>
 * Print the map using the overridden {@link Data#toString()} method.
 */
public class TestApp {
    /**
     * Run the application
     */
    @Test
    public void appMain() {
        App.main(new String[]{});
    }

    /**
     * Create an exchangeRate and ensure the string output matches expected output
     */
    @Test
    public void createValidExchangeRate() {
        ExchangeRate exchangeRate = new ExchangeRate("GBP", "USD", 1.65);
        assertTrue("GBP/USD : 1.65".equals(exchangeRate.toString()));
    }

    /**
     * Use a new data loader object with good rates to create a hash map
     */
    @Test
    public void loadWithHashMap() {
        DataLoader loader = new DataLoader();
        loader.addExchangeRate("GBP/USD", new ExchangeRate("GBP", "USD", 1.245));
        loader.addExchangeRate("CHF/USD", new ExchangeRate("CHF", "USD", .99));
        loader.addExchangeRate("EUR/USD", new ExchangeRate("EUR", "USD", 1.1));
        Map<String, Data> map = loader.load(new HashMap<>(), loader.readFileAsString("src/test/resources/FILE.DAT"));
        assertNotNull(map);
    }

    /**
     * Use a new data loader object with good rates to create a tree map
     */
    @Test
    public void loadWithTreeMap() {
        DataLoader loader = new DataLoader();
        loader.addExchangeRate("GBP/USD", new ExchangeRate("GBP", "USD", 1.245));
        loader.addExchangeRate("CHF/USD", new ExchangeRate("CHF", "USD", .99));
        loader.addExchangeRate("EUR/USD", new ExchangeRate("EUR", "USD", 1.1));
        Map<String, Data> map = loader.load(new TreeMap<>(), loader.readFileAsString("src/test/resources/FILE.DAT"));
        assertNotNull(map);
    }

    /**
     * Loading a bogus file, should properly return null.
     */
    @Test
    public void loadBadFile() {
        DataLoader loader = new DataLoader();
        Map<String, Data> map = loader.load(new TreeMap<>(), loader.readFileAsString("src/test/java/app/TestApp.java"));
        assertTrue(map.isEmpty());
    }
}