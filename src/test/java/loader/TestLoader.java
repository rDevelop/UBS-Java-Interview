package loader;

import data.Data;
import org.junit.Test;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertTrue;

/**
 * TestLoader will create a class that implements Loader to test the default method
 */
public class TestLoader {
    /**
     * Use an implementer of an interface to test the default method that loads a file correctly.
     */
    @Test
    public void readFileAsString() {
        LoaderImpl imp = new LoaderImpl();
        List<String> list = imp.readFileAsString("src/test/resources/FILE.DAT");
        assertTrue(list != null && list.get(0).contains("Company Code"));
    }

    /**
     * Implement the Loader interface.
     */
    class LoaderImpl implements Loader {
        @Override
        public <T extends Map<String, Data>> T load(Map<String, Data> map, List<String> lines) {
            return null;
        }
    }
}
