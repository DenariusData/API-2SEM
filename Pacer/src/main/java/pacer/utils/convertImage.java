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
        // Retorna uma imagem padrão ou uma imagem vazia
        return new Image("@../images/placeholder-user.png"); // Exemplo de imagem padrão
    }

    public static InputStream imageToInputStream(byte[] imageBytes) {
        if (imageBytes != null && imageBytes.length > 0) {
            return new ByteArrayInputStream(imageBytes);
        }
        return null;
    }
}
