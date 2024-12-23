package pacer.aluno;

import java.io.IOException;
import java.io.InputStream;
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
import pacer.data.dao.PontosDAO;
import pacer.data.dao.SprintDAO;
import pacer.data.models.Aluno;
import pacer.data.models.AlunosParaAvaliacao;
import pacer.data.models.Avaliacao;
import pacer.data.models.Criterios;
import pacer.data.models.Pontos;
import pacer.data.models.Sprint;
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
    
    private long avaliadorRa;
    private long avaliadoRa;
    private List<ToggleGroup> toggleGroups;

    private Aluno alunoAvaliado;

    private Pontos pontosDoGrupo;

    private Sprint sprintSelecionada;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
            toggleGroups = new ArrayList<>();
            avaliadorRa = Aluno.AlunoLogado.getAluno().getRa();

            alunoAvaliado = AlunosParaAvaliacao.getAvaliado();
            
            nomeField.setText(alunoAvaliado.getNome());
            grupoField.setText(alunoAvaliado.getGrupoNome());
            InputStream fotoStream = convertImage.imageToInputStream(alunoAvaliado.getFoto());
            if (fotoStream != null) {
            Image fotoAluno = new Image(fotoStream);
            fotoAlunoImageView.setImage(fotoAluno);
            }
            avaliadoRa = alunoAvaliado.getRa();

            carregarCriterios();
    }

    public void carregaSprintSelecionada(Sprint sprint) {
        sprintSelecionada = sprint;
        carregaPontos();
    }

    private void carregaPontos() {
        pontosDoGrupo = PontosDAO.getPontosBySprintAndGrupo(sprintSelecionada.getSprintId(), alunoAvaliado.getGrupoId());
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
        try {
            for (int i = 0; i < toggleGroups.size(); i++) {
                ToggleGroup group = toggleGroups.get(i);
                int criterioId = criterios.get(i).getId();
                int sprintId = sprintSelecionada.getSprintId();
    
                ToggleButton selectedToggle = (ToggleButton) group.getSelectedToggle();
            
                if (selectedToggle != null) {
                    double nota = Double.parseDouble(selectedToggle.getText());
    
                    Avaliacao avaliacaoExistente = AvaliacaoDAO.getAvaliacaoPorAlunoECriterio(avaliadorRa, avaliadoRa, criterioId, sprintId);
    
                    if (avaliacaoExistente != null) {
                        int notaAntiga = (int)avaliacaoExistente.getNota();
                        int notaDif = ((int)nota - notaAntiga);
                        avaliacaoExistente.setNota(nota);
                        avaliacaoExistente.setData(LocalDateTime.now());
                        avaliacaoExistente.setSprintId(sprintId);
                        AvaliacaoDAO.update(avaliacaoExistente);
                        pontosDoGrupo.setPontosAtuais(pontosDoGrupo.getPontosAtuais() - notaDif);
                    } 
                    else {
                        Avaliacao avaliacao = new Avaliacao();
                        avaliacao.setAvaliadorAlunoRa(avaliadorRa);
                        avaliacao.setAvaliadoAlunoRa(avaliadoRa);
                        avaliacao.setCriterioId(criterioId);
                        avaliacao.setNota(nota);
                        avaliacao.setSprintId(sprintId);
                        avaliacao.setData(LocalDateTime.now());
                        pontosDoGrupo.setPontosAtuais(pontosDoGrupo.getPontosAtuais() - (int) nota);

                        AvaliacaoDAO.create(avaliacao);
                    }
                    PontosDAO.updatePontos(pontosDoGrupo);
                }
            }
            mbox.ShowMessageBox(AlertType.INFORMATION, "Avaliação", "Avaliação realizada com sucesso");
        } catch (Exception e) {
            e.printStackTrace();
            mbox.ShowMessageBox(AlertType.ERROR, "Erro", "Erro ao enviar a avaliação: " + e.getMessage());
        }
        finally{
            voltarTela(event);
        }
    }

    @FXML
    private void voltarTela(ActionEvent event) throws IOException {
        sceneSwitcher.switchScene("/FXML/AlunoAvaliacaoView.fxml", event);
    }
}
