package pacer.utils;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import javafx.scene.image.Image;

public class convertImage {

    public static Image bytesToImage(byte[] imageBytes) {
        if (imageBytes != null && imageBytes.length > 0) {
            ByteArrayInputStream inputStream = new ByteArrayInputStream(imageBytes);
            return new Image(inputStream);
        }
        return null;
    }
    public static InputStream imageToInputStream(byte[] imageBytes) {
        if (imageBytes != null && imageBytes.length > 0) {
            return new ByteArrayInputStream(imageBytes);
        }
        return null;
    }
}
