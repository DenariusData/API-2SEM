package pacer.professor;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import pacer.utils.sceneSwitcher;

public class ProfEquipesController {
    
    @FXML
    public void initialize() {

    }
    @FXML
    public void confirmarVoltar(ActionEvent event) throws IOException {
        sceneSwitcher.switchScene("/FXML/ProfHomeView.fxml", event);
    }
    @FXML
    public void abrirImport(ActionEvent event) throws IOException {
        sceneSwitcher.switchScene("/FXML/ProfImportarView.fxml", event);
    }
    @FXML
    public void adicionarGrupo(ActionEvent event) throws IOException {
        
    }
    @FXML
    public void editarGrupo(ActionEvent event) throws IOException {
        
    }
    @FXML
    public void deletarGrupo(ActionEvent event) throws IOException {
        
    }
    @FXML
    public void limparCamposGrupo(ActionEvent event) throws IOException {
        
    }
    @FXML
    public void adicionarIntegrante(ActionEvent event) throws IOException {
        
    }
    @FXML
    public void editarIntegrante(ActionEvent event) throws IOException {
        
    }
    @FXML
    public void deletarIntegrante(ActionEvent event) throws IOException {
        
    }
    @FXML
    public void limparCamposIntegrante(ActionEvent event) throws IOException {
        
    }
    @FXML
    public void baixarRelatorio(ActionEvent event) throws IOException {
        
    }
}