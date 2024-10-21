package pacer.utils;

import java.util.Optional;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextInputDialog;

public class mbox {
    // alertType: AlertType.[NONE, INFORMATION, WARNING, CONFIRMATION, ERROR];
    public static void ShowMessageBox(AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    public static String ShowInputMessageBox(String title, String message) {
        Optional<String> result = ShowInputDialog(title, message);
        
        return result.orElse(null);
    }
    private static Optional<String> ShowInputDialog(String title, String message) {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle(title);
        dialog.setHeaderText(null);
        dialog.setContentText(message);
        
        return dialog.showAndWait();
    }
}
