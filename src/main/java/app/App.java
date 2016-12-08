package app;

import data.Data;
import loader.DataLoader;
import marketdata.ExchangeRate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.logging.Logger;

/**
 * App is the main class of the UBS Java Interview exercise. The exercise is given to candidates
 * prior to a phone screen or face to face interview. The exercise can also be given to candidates
 * during an interview. The exercise should be completed in a three hour period and the use of the
 * internet is available as a resource. This implementation is an example solution. There are no
 * correct answers, only that the program should satisfy the conditions in <tt>requirements.txt</tt>
 */
public class App {
    static final Logger logger = Logger.getLogger(App.class.getName());

    /**
     * No public constructor for App.
     */
    private App() {
    }

    /**
     * This main method executes the {@link DataLoader#load(Map, List)}<br>
     * There are two implementations with {@link TreeMap}  and {@link HashMap} .<br>
     * Country Credit Rating and Average amount are displayed
     * <br>
     * {@link loader.Loader#readFileAsString(String)} is the interface {@link loader.Loader} default method<br>
     * Use the argument as the file if passed in.<br>
     * <br>
     * <b>Operations:</b><br>
     * Set filename from arguments if they are passed, or use the default location from exercise zip file.
     * Create a {@link DataLoader} object.<br>
     * Loads three {@link ExchangeRate} objects per requirements. {@link DataLoader#addExchangeRate(String, ExchangeRate)}<br>
     * Implement a {@link HashMap} and a {@link TreeMap} to contain the file contents, from {@link DataLoader#readFileAsString(String)}<br>
     * Print the map using the overridden {@link Data#toString()} method.
     *
     * @param args Filename
     */
    public static void main(String[] args) {
        String file = "src/test/resources/FILE.DAT";
        if (args.length > 0) {
            file = args[0];
            logger.info("File parameter: " + file);
        }
        DataLoader loader = new DataLoader();

        loader.addExchangeRate("GBP/USD", new ExchangeRate("GBP", "USD", 1.245));
        loader.addExchangeRate("CHF/USD", new ExchangeRate("CHF", "USD", .99));
        loader.addExchangeRate("EUR/USD", new ExchangeRate("EUR", "USD", 1.1));

        Map<String, Data> map = loader.load(new HashMap<>(), loader.readFileAsString(file));
        if (!map.isEmpty()) {
            System.out.println("HashMap ...");
            System.out.println("Country     \tCredit Rating\tAverage");
            System.out.println("_________________________________________________________");
            map.forEach((s, data) -> System.out.println(data));
        } else {
            System.err.println("No ouput for HashMap.");
        }

        System.out.println("\n");

        map = loader.load(new TreeMap<>(), loader.readFileAsString(file));
        if (!map.isEmpty()) {
            System.out.println("TreeMap (Sorted)..");
            System.out.println("Country     \tCredit Rating\tAverage");
            System.out.println("_________________________________________________________");
            map.forEach((s, data) -> System.out.println(data));
        } else {
            System.err.println("No ouput for TreeMap.");
        }
    }
}
