package pacer.aluno;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class AlunoHomeController implements Initializable {
	
	@FXML
	private AnchorPane anchorPane; 

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    @FXML
    private void handleRealizarAval(javafx.event.ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/AlunoAvaliacaoView.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    
    private void CentralizarJanela(AnchorPane no) {
    	no.setOnMouseEntered(event -> {
            Stage stage = (Stage) no.getScene().getWindow();
            if (stage != null) {
                stage.setX((java.awt.Toolkit.getDefaultToolkit().getScreenSize().getWidth() - stage.getWidth()) / 2);
                stage.setY((java.awt.Toolkit.getDefaultToolkit().getScreenSize().getHeight() - stage.getHeight()) / 2);
            }
        });
    }
}
