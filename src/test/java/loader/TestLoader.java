package loader;

import data.Data;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static junit.framework.TestCase.assertNotNull;

/**
 * TestLoader will create a class that implements Loader to test the default method
 */
public class TestLoader {
    class LoaderImpl implements Loader {

        @Override
        public <T extends Map<String, Data>> T load(Map<String, Data> map, List<String> lines) {
            return null;
        }
    }
    @Test
    public void readFileAsString() {
        LoaderImpl imp = new LoaderImpl();
        List<String> list = imp.readFileAsString("src/test/resources/FILE.DAT");
        assert(list instanceof ArrayList);
        assertNotNull(list);
        assert(list.get(0).contains("Company Code"));
        list.forEach(System.out::println);
    }
}
