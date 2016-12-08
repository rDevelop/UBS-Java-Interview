package loader;

import data.Data;
import marketdata.ExchangeRate;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.ExpectedSystemExit;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

/**
 * Test the {@link DataLoader} methods and functionality
 */
public class TestDataLoader {
    /**
     * Allow an exit command rule for junit testing only.
     */
    @Rule
    public final ExpectedSystemExit exit = ExpectedSystemExit.none();

    /**
     * Test the read file method
     */
    @Test
    public void readFile() {
        String file = "src/test/resources/FILE.DAT";
        DataLoader loadData = new DataLoader();
        assertNotNull(loadData.readFileAsString(file));
    }

    /**
     * Test trying to read a file that doesn't exist.
     */
    @Test
    public void readBogusFile() {
        exit.expectSystemExit();
        // Will exit
        DataLoader loadData = new DataLoader();
        loadData.readFileAsString("NOFILE.DAT");
    }

    /**
     * Load a map with the DataLoader from a List
     */
    @Test
    public void loadMap() {
        DataLoader loader = new DataLoader();
        loader.addExchangeRate("GBP/USD", new ExchangeRate("GBP", "USD", 1.245));
        loader.addExchangeRate("EUR/USD", new ExchangeRate("EUR", "USD", 1.1));
        List<String> arrayList = new ArrayList<>();
        arrayList.add("Company Code\tAccount\tCity\tCountry\tCredit Rating\tCurrency\tAmount");
        arrayList.add("2316\t1520670\tArhus\tDK\tNR\tGBP\t617755137");
        arrayList.add("2309\t6849224\tLondon\tUK\tA\tGBP\t-398659337.540115");
        Map<String, Data> map = loader.load(new HashMap<>(), arrayList);
        assertNotNull(map);
    }

    /**
     * Ensure no major flaws when loading a null List
     */
    @Test
    public void loadNUllMap() {
        DataLoader loadData = new DataLoader();
        Map<String, Data> map = loadData.load(new HashMap<>(), null);
        assertNull(map);
    }

}
