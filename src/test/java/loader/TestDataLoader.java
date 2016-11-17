package loader;

import data.Data;
import org.junit.Before;
import org.junit.Test;

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
    private final List<String> arrayList = new ArrayList<>();

    @Before
    public void setList() {
        arrayList.add("Company Code\tAccount\tCity\tCountry\tCredit Rating\tCurrency\tAmount");
        arrayList.add("2316\t1520670\tArhus\tDK\tNR\tGBP\t617755137");
        arrayList.add("2309\t6849224\tLondon\tUK\tA\tGBP\t-398659337.540115");
    }

    @Test
    public void readFile() {
        String file = "src/test/resources/FILE.DAT";
        DataLoader loadData = new DataLoader();
        assertNotNull(loadData.readFileAsString(file));
    }

    @Test
    public void readBogusFile() {
        DataLoader loadData = new DataLoader();
        assertNull(loadData.readFileAsString("NOFILE.DAT"));
    }

    @Test
    public void testMap() {
        DataLoader loadData = new DataLoader();
        Map<String, Data> map = loadData.load(new HashMap<>(), arrayList);
        Data data = map.get("UKA");
        assert (data.toString().contains("UK A"));
        data = map.get("DKNR");
        assert (data.toString().contains("DK NR"));
        map = loadData.load(new HashMap<>(), null);
        assertNull(map);
    }
}
