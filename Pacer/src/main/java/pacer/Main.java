package pacer;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/FXML/ProfImportarView.fxml"));
        primaryStage.setTitle("Importar Alunos");
        primaryStage.setScene(new Scene(root, 600, 400)); // Define o tamanho inicial da janela
        primaryStage.setResizable(true); // Permite que a janela seja redimensionada
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
