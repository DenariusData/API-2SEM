package pacer.aluno;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import pacer.utils.sceneSwitcher;

public class AlunoAvaliacaoController implements Initializable {
    
    @FXML
    public Button realizarAvaliacaoBtn;
    @FXML
    private ImageView imgVoltar;

    @FXML
    public void abrirRealizarAvaliacao(ActionEvent event) throws IOException {
        // Alterna para a nova cena sem criar um novo Stage
        sceneSwitcher.switchScene("/FXML/AlunoRealizarAvalView.fxml", event);
    }

    @FXML
    public void confirmarVoltar(ActionEvent event) throws IOException {
        sceneSwitcher.switchScene("/FXML/AlunoHomeView.fxml", event);
    }
    

    public void setupTooltips() {
        Tooltip tooltipAutonomia = new Tooltip("Definição de autonomia.");
        Tooltip tooltipColaboracao = new Tooltip("Definição de colaboração.");
        Tooltip tooltipEntrega = new Tooltip("Definição de entrega.");
        Tooltip tooltipResultados = new Tooltip("Definição de resultados.");
        Tooltip tooltipStatus = new Tooltip("Definição de status.");

        // colAutonomia.setTooltip(tooltipAutonomia);  // precisa declarar essas variaveis col
        // colColaboracao.setTooltip(tooltipColaboracao);  // precisa declarar essas variaveis col
        // colEntrega.setTooltip(tooltipEntrega);  // precisa declarar essas variaveis col
        // colResultados.setTooltip(tooltipResultados);  // precisa declarar essas variaveis col
        // colStatus.setTooltip(tooltipStatus);  // precisa declarar essas variaveis col
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setupTooltips();
    }
    
}
