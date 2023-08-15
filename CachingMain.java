import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Map;
public class CachingMain {

    private static final Map<String, SoftReference<String>> cache = new HashMap<>();

    public static String getDataFromCache(String key) {
        SoftReference<String> cachedValue = cache.get(key);

        if (cachedValue != null && cachedValue.get() != null) {
            System.out.println("Cache hit for key: " + key);
            return cachedValue.get();
        } else {
            System.out.println("Cache miss for key: " + key);
            String newValue = fetchDataFromDatabase(key); // Simulated database fetch
            cache.put(key, new SoftReference<>(newValue));
            return newValue;
        }
    }
    private static String fetchDataFromDatabase(String key) {
        // Simulated database fetch
        System.out.println("Fetching data from database for key: " + key);
        return "Data for " + key;
    }
    public static void main(String[] args) {
        System.out.println(getDataFromCache("Key1")); // Cache miss
        System.out.println(getDataFromCache("Key1")); // Cache hit
        System.out.println(getDataFromCache("Key2")); // Cache miss
        System.out.println(getDataFromCache("Key2")); // Cache hit
    }

}
