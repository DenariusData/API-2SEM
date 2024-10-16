package pacer.aluno;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class AlunoHomeController implements Initializable {
	
	@FXML
	private AnchorPane anchorPane; 

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    	CentralizarJanela(anchorPane);
    }

    @FXML
    private void handleCriteriosAval(javafx.event.ActionEvent event) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Critérios de Avaliação");
        alert.setHeaderText("Critérios para a Avaliação");
        alert.setContentText("Aqui estará a janela que mostrará os Critérios de Avaliação");
        alert.showAndWait();
    }
    @FXML
    private void handleRealizarAval(javafx.event.ActionEvent event) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Realizar Avaliação");
        alert.setHeaderText("Realizar Avaliação");
        alert.setContentText("Aqui estará a janela para realizar a avaliação");
        alert.showAndWait();
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
