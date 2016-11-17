package app;

import data.Data;
import loader.DataLoader;
import marketdata.ExchangeRate;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertNull;

/**
 * Test class to test main method from {@link App}<br>
 * <b>Untested</b>Set filename from arguments if they are passed, or use the default location from exercise zip file.
 * Create a {@link DataLoader} object.<br>
 * Loads three {@link ExchangeRate} objects per requirements. {@link DataLoader#addExchangeRate(String, ExchangeRate)}<br>
 * Implement a {@link HashMap} and a {@link TreeMap} to contain the file contents, from {@link DataLoader#readFileAsString(String)}<br>
 * Print the map using the overridden {@link Data#toString()} method.
 */
public class TestApp {
    private final String file = "src/test/resources/FILE.DAT";

    @Test
    public void addDataLoader() {
        new DataLoader();
    }

    @Test
    public void addExchanges() {
        DataLoader loader = new DataLoader();
        loader.addExchangeRate("GBP", new ExchangeRate("GBP", "USD", BigDecimal.valueOf(1.654)));
        loader.addExchangeRate("EUR", new ExchangeRate("EUR", "USD", BigDecimal.valueOf(1.35)));
        loader.addExchangeRate("CHF", new ExchangeRate("CHF", "USD", BigDecimal.valueOf(1.10)));

    }

    @Test
    public void loadWithHashMap() {
        DataLoader loader = new DataLoader();
        loader.addExchangeRate("GBP", new ExchangeRate("GBP", "USD", BigDecimal.valueOf(1.654)));
        loader.addExchangeRate("EUR", new ExchangeRate("EUR", "USD", BigDecimal.valueOf(1.35)));
        loader.addExchangeRate("CHF", new ExchangeRate("CHF", "USD", BigDecimal.valueOf(1.10)));
        System.out.println("HashMap...");
        Map<String, Data> map = loader.load(new HashMap<>(), loader.readFileAsString(file));
        assertNotNull(map);
        map.forEach((s, data) -> System.out.println(data));
    }


    @Test
    public void loadWithTreeMap() {
        DataLoader loader = new DataLoader();
        loader.addExchangeRate("GBP", new ExchangeRate("GBP", "USD", BigDecimal.valueOf(1.654)));
        loader.addExchangeRate("EUR", new ExchangeRate("EUR", "USD", BigDecimal.valueOf(1.35)));
        loader.addExchangeRate("CHF", new ExchangeRate("CHF", "USD", BigDecimal.valueOf(1.10)));
        System.out.println("TreeMap...");
        Map<String, Data> map = loader.load(new TreeMap<>(), loader.readFileAsString(file));
        assertNotNull(map);
        map.forEach((s, data) -> System.out.println(data));
    }

    @Test
    public void loadBadFile() {
        DataLoader loader = new DataLoader();
        loader.addExchangeRate("GBP", new ExchangeRate("GBP", "USD", BigDecimal.valueOf(1.654)));
        loader.addExchangeRate("EUR", new ExchangeRate("EUR", "USD", BigDecimal.valueOf(1.35)));
        loader.addExchangeRate("CHF", new ExchangeRate("CHF", "USD", BigDecimal.valueOf(1.10)));
        System.out.println("TreeMap...");
        Map<String, Data> map = loader.load(new TreeMap<>(), loader.readFileAsString("src/test/java/app/TestApp.java"));
        assertNull(map);
    }
}