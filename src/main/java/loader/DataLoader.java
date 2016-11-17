package loader;

import data.Data;
import marketdata.ExchangeRate;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Implementing Loader to create Map of {@link Data} objects.<br>
 * The default method in Interface {@link Loader#readFileAsString(String)} is hidden from implementation.<br>
 */
public class DataLoader implements Loader {

    private final HashMap<String, ExchangeRate> rates = new HashMap<>();

    /**
     * This generic load method uses and Type {@link Map} to maintain the data.
     *
     * @param map   The Type of Map used to contain the List of Strings
     * @param lines The List of Strings
     * @param <T>   Any Type Map
     * @return The map that was created from data lines, or null if List parameter is null.
     */
    @Override
    @SuppressWarnings("unchecked")
    public <T extends Map<String, Data>> T load(Map<String, Data> map, List<String> lines) {
        if (lines == null) {
            return null;
        }
        map = createMap(map, lines);
        return (T) map;
    }


    /**
     * Implementing a mapper class that takes and Type {@link Map}<br>
     * <b>Operations</b><br>
     * Return null if lines is null, or the first line doesn't contain the expected header<br>
     * Read the lines one by one.<br>
     * If this is the header line, then continue to next line.<br>
     * Create a data object.<br>
     * Insert the line into the data object.<br>
     * Get the current amount of the line(s) (This will be Overridden if valid currency is passed<br>
     * Switch on the currency and check for CHF and GBP<br>
     * Convert the amount based on currency<br>
     * Set the converted amount.<br>
     * Use the {@link Data#getKey()} method to use as the hash key<br>
     * Check for key in Map, before creating a new Data object.<br>
     * If the object exists, then get the existing object and average the data.<br>
     * Set the existing object with new average and amounts to the original Data object<br>
     * Add the Data object to the map<br>
     * Return the map when all lines are iterated through.
     *
     * @param map   Map that will hold the List of Strings
     * @param lines Data lines from file
     * @return The populated map or null
     */
    private Map<String, Data> createMap(Map<String, Data> map, List<String> lines) {
        if (lines == null || !lines.get(0).contains("Company Code")) {
            return null;
        }
        for (String line : lines) {
            if (line.contains("Company Code")) {
                continue;
            }
            Data d = new Data();
            d.insertDataLine(line);
            BigDecimal bd = d.getAmount();
            switch (d.getCurrency()) {
                case "CHF":
                    bd = d.triangulateCurrency(rates.get("CHF"), rates.get("EUR"), d.getAmount());
                    break;
                case "GBP":
                    bd = d.triangulateCurrency(rates.get("GBP"), rates.get("EUR"), d.getAmount());
                    break;
                default:
                    break;
            }
            d.setAmounts(bd);
            String key = d.getKey();
            if (map.get(key) != null) {
                Data existingKey = map.get(key);
                existingKey.average(d.getAmount());
                d = existingKey;
            }
            map.put(key, d);
        }
        return map;
    }

    /**
     * Method to add Exchange rates to the instance field {@link DataLoader#rates}
     *
     * @param curr         String representation of Currency. This is the hash key
     * @param exchangeRate exchange rate that attaches to the key
     */
    public void addExchangeRate(String curr, ExchangeRate exchangeRate) {
        rates.put(curr, exchangeRate);
    }

}


