package pacer.professor;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import pacer.data.models.Professor;
import pacer.utils.convertImage;
import pacer.utils.sceneSwitcher;

public class ProfHomeController implements Initializable {

    @FXML
    ImageView pnlFoto;
    @FXML
    Label pnlNome;
    @FXML
    Label pnlEmail;
    @FXML 
    Label pnlCurso;
    
    Professor logado;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        logado = Professor.ProfessorLogado.getProfessor();
        InputStream fotoStream = convertImage.imageToInputStream(logado.getFoto());
        if (fotoStream != null) {
            Image fotoAluno = new Image(fotoStream);
            pnlFoto.setImage(fotoAluno);
        }
        pnlNome.setText(logado.getNome());
        pnlEmail.setText(logado.getEmail());
    }
    @FXML
    private void handlepnlBotaocriteriosClick(ActionEvent event) throws IOException {
        // Carregar a nova tela (substitua o caminho pelo FXML correto)
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/ProfCriteriosView.fxml"));
        Parent root = loader.load();

        // Obter a janela (Stage) atual
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        
        // Definir a nova cena no Stage
        Scene scene = new Scene(root);
        stage.setScene(scene);
        
        // Exibir a nova tela
        stage.show();

        
    }

    @FXML
    private void handleEditarFoto(ActionEvent event) throws IOException {
        // TODO EDITAR FOTO
    }

    @FXML
    private void handleSair(javafx.event.ActionEvent event) throws IOException {
        sceneSwitcher.switchScene("/FXML/LoginView.fxml", event);
    }
}
