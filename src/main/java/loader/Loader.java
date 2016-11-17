package loader;

import data.Data;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

/**
 * Loader interface
 */
public interface Loader {
    /**
     * The load method will allow any <tt>Type</tt> Map contain the List of Strings read from the file<br>
     * The Map is the first parameter so that it can be defined from the calling class.
     *
     * @param map   The Type of Map used to contain the List of Strings
     * @param lines The List of Strings
     * @param <T>   Any Type Map
     * @return map The populated Type Map
     */
    <T extends Map<String, Data>> T load(Map<String, Data> map, List<String> lines);

    /**
     * This is implemented so that the concrete classes don't have to worry about file IO<br>
     * A String that represents the file location will be opened and read as a an List of Strings.
     *
     * @param file File
     * @return the ArrayList of the file or null on IOException or incorrect file.
     */
    default List<String> readFileAsString(String file) {
        Path path = Paths.get(file);
        if (!Files.exists(path)) {
            System.out.println("Can't open file. app.App exiting.");
        }
        try {
            return Files.readAllLines(path);
        } catch (IOException e) {
            System.out.println("Error " + e.toString());
            e.printStackTrace();
        }
        return null;
    }
}
