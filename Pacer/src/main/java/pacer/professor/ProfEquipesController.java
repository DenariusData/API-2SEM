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
    
}