package pacer.aluno;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import pacer.data.dao.AvaliacaoDAO;
import pacer.data.dao.CriteriosDAO;
import pacer.data.models.Aluno;
import pacer.data.models.Avaliacao;
import pacer.data.models.Criterios;
import pacer.utils.convertImage;
import pacer.utils.mbox;
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

    private List<Criterios> criterios;
    
    private Long avaliadorRa;
    private Long avaliadoRa;
    private List<ToggleGroup> toggleGroups;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        toggleGroups = new ArrayList<>();
        carregarCriterios();
    }

    public void carregarDadosAluno(Aluno alunoAvaliado) {
        nomeField.setText(alunoAvaliado.getNome());
        grupoField.setText(alunoAvaliado.getGrupoNome());
        Image imgFoto = new Image(convertImage.imageToInputStream(alunoAvaliado.getFoto()));
        fotoAlunoImageView.setImage(imgFoto);
        
        avaliadorRa = Aluno.AlunoLogado.getAluno().getRa();
        avaliadoRa = alunoAvaliado.getRa();
    }

    private void carregarCriterios() {
        criteriosContainer.getChildren().clear();
        criterios = CriteriosDAO.getAll();
    
        for (Criterios criterio : criterios) {
            if (criterio.isAtivo()) {
                VBox vBoxCriterio = new VBox(5);
                vBoxCriterio.getStyleClass().add("criterio-container");
        
                Text criterioText = new Text(criterio.getNome());
                criterioText.setStyle("-fx-font-size: 12px; -fx-font-weight: bold;");
        
                HBox notaBox = new HBox(10);
                ToggleGroup group = new ToggleGroup();
        
                for (int i = 0; i <= 3; i++) {
                    var rb = new RadioButton(String.valueOf(i));
                    rb.setToggleGroup(group);
                    rb.setUserData(i);
                    notaBox.getChildren().add(rb);
                }
        
                vBoxCriterio.getChildren().addAll(criterioText, notaBox);
                criteriosContainer.getChildren().add(vBoxCriterio);
        
                toggleGroups.add(group);
            }     
        }
    }
    
    @FXML
    public void enviarAvaliacao(ActionEvent event) throws IOException {
        for (int i = 0; i < toggleGroups.size(); i++) {
            ToggleGroup group = toggleGroups.get(i);
            int criterioId = criterios.get(i).getId();

            ToggleButton selectedToggle = (ToggleButton) group.getSelectedToggle();
        
            if (selectedToggle != null) {
                double nota = Double.parseDouble(selectedToggle.getText());

                Avaliacao avaliacaoExistente = AvaliacaoDAO.getAvaliacaoPorAlunoECriterio(avaliadorRa, avaliadoRa, criterioId);

                if (avaliacaoExistente != null) {
                    avaliacaoExistente.setNota(nota);
                    avaliacaoExistente.setData(LocalDateTime.now());
                    AvaliacaoDAO.update(avaliacaoExistente);
                } 
                else {
                    Avaliacao avaliacao = new Avaliacao();
                    avaliacao.setAvaliadorAlunoRa(avaliadorRa);
                    avaliacao.setAvaliadoAlunoRa(avaliadoRa);
                    avaliacao.setCriterioId(criterioId);
                    avaliacao.setNota(nota);
                    avaliacao.setData(LocalDateTime.now());

                    AvaliacaoDAO.create(avaliacao);
                }
            }
        }
        mbox.ShowMessageBox(AlertType.INFORMATION, "Avaliação", "Avaliação realizada com sucesso");
        voltarTela(event);
    }

    @FXML
    private void voltarTela(ActionEvent event) throws IOException {
        sceneSwitcher.switchScene("/FXML/AlunoAvaliacaoView.fxml", event);
    }
}
