import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Map;
public class CachingMain {

    private static final Map<String, SoftReference<byte[]>> imageCache = new HashMap<>();

    public static byte[] getImage(String imageName) {
        SoftReference<byte[]> cachedImage = imageCache.get(imageName);
        if (cachedImage != null && cachedImage.get() != null) {
            System.out.println("Cache hit for image: " + imageName);
            return cachedImage.get();
        } else {
            System.out.println("Cache miss for image: " + imageName);
            byte[] newImage = fetchImageFromDatabase(imageName); // Simulated image fetch
            imageCache.put(imageName, new SoftReference<>(newImage));
            return newImage;
        }
    }
    private static byte[] fetchImageFromDatabase(String imageName) {
        // Simulated image fetch from database or storage
        System.out.println("Fetching image from database: " + imageName);
        return new byte[1024 * 1024]; // Simulated image data (1 MB)
    }
    public static void main(String[] args) {
        byte[] image1 = getImage("image1.jpg"); // Cache miss
        byte[] image2 = getImage("image2.jpg"); // Cache miss
        byte[] image1Again = getImage("image1.jpg"); // Cache hit
    }

}
