package pacer.aluno;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import pacer.data.models.Aluno;
import pacer.data.models.Criterios;
import pacer.utils.convertImage;
import pacer.utils.sceneSwitcher;

public class AlunoRealizarAvalController implements Initializable {

    @FXML
    private Label nomeField;
    @FXML
    private Label grupoField;
    @FXML
    private ImageView fotoAlunoImageView;
    @FXML
    private VBox criteriosContainer;
    @FXML
    private Button enviarAvaliacaoBtn;
    @FXML
    private Button voltar;

    private Aluno alunoAvaliado;
    private String nomeGrupo;
    private List<Criterios> criterios;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    public void carregarDadosAluno(String nomeAluno, String nomeGrupo, byte[] foto) {
        nomeField.setText(nomeAluno);
        grupoField.setText(nomeGrupo);
        Image imgFoto = new Image(convertImage.imageToInputStream(foto));
        fotoAlunoImageView.setImage(imgFoto);
    }

    public void carregarCriterios(List<Criterios> criterios) {
        this.criterios = criterios;
        criteriosContainer.getChildren().clear();

        for (Criterios criterio : criterios) {
            VBox vBoxCriterio = new VBox(5);
            vBoxCriterio.getStyleClass().add("criterio-container");

            Text criterioText = new Text(criterio.getNome());
            criterioText.setStyle("-fx-font-size: 12px; -fx-font-weight: bold;");

            TextField notaField = new TextField();
            notaField.setPromptText("Insira a nota");

            vBoxCriterio.getChildren().addAll(criterioText, notaField);

            criteriosContainer.getChildren().add(vBoxCriterio);
        }
    }

    @FXML
    private void enviarAvaliacao() {
        // Lógica para coletar as notas e enviar a avaliação
        for (int i = 0; i < criteriosContainer.getChildren().size(); i++) {
            VBox vBox = (VBox) criteriosContainer.getChildren().get(i);
            TextField notaField = (TextField) vBox.getChildren().get(1);

            // Aqui você pode salvar as notas ou fazer outras operações necessárias
            System.out.println("Nota do critério " + criterios.get(i).getNome() + ": " + notaField.getText());
        }
        System.out.println("Avaliação enviada para o aluno: " + nomeField.getText());
    }

    @FXML
    private void voltarTela(ActionEvent event) throws IOException {
        sceneSwitcher.switchScene("/FXML/AlunoAvaliacaoView.fxml", event);
    }
}
