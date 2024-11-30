package pacer.aluno;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import pacer.data.dao.AlunoDAO;
import pacer.data.dao.SprintDAO;
import pacer.data.models.Aluno;
import pacer.data.models.Sprint;
import pacer.utils.convertImage;
import pacer.utils.mbox;
import pacer.utils.sceneSwitcher;

public class AlunoHomeController implements Initializable {

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private GridPane calendarGrid;  // Vinculado ao novo calendário no FXML

    @FXML
    private Label monthYearLabel;  // Novo Label para exibir o mês e ano

    @FXML
    private Label lblInfoSprint; 

    @FXML
    ImageView pnlFoto;
    @FXML
    private Label nomeField;
    @FXML
    private Label raField;
    @FXML
    private Label emailField;

    private Aluno logado;

    // Mapa para armazenar dias coloridos
    private Map<LocalDate, Color> coloredDays = new HashMap<>();

    private Sprint sprintAtual;

    private LocalDate dateAvaliacaoStart = LocalDate.now();
    private LocalDate dateAvaliacaoEnd = LocalDate.now();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        CentralizarJanela(anchorPane);

        configurarAluno(); 

        if (SprintDAO.getSprintAtual() == null) mbox.ShowMessageBox(AlertType.INFORMATION, "SPRINT", "Não existe Sprint para o periodo atual.");
        if (SprintDAO.getSprintAtual() != null) configurarInfoSprint();

        // Configurar o calendário customizado
        setupColoredDays();
        YearMonth currentMonth = YearMonth.now();  // Mês e ano atuais
        populateCalendar(currentMonth);
    }
    
    private void configurarAluno() {
        logado = Aluno.AlunoLogado.getAluno();
        InputStream fotoStream = convertImage.imageToInputStream(logado.getFoto());
        if (fotoStream != null) {
            Image fotoAluno = new Image(fotoStream);
            pnlFoto.setImage(fotoAluno);
        }
        nomeField.setText(logado.getNome());
        emailField.setText(logado.getEmail());
        raField.setText(String.valueOf(logado.getRa()));
    }

    private void configurarInfoSprint() {
        sprintAtual = SprintDAO.getSprintAtual();
        dateAvaliacaoStart = sprintAtual.getDataInicio();
        dateAvaliacaoEnd = sprintAtual.getDataFim();
        int diasFaltantes = (int)ChronoUnit.DAYS.between(LocalDate.now(), sprintAtual.getDataFim());
        lblInfoSprint.setText("Você está na Sprint " + sprintAtual.getSprint() + " faltam " + diasFaltantes + " dias para o fim.");
    }

    // Método para preencher o calendário com os dias do mês
    private void populateCalendar(YearMonth yearMonth) {
        LocalDate firstOfMonth = yearMonth.atDay(1);
        int daysInMonth = yearMonth.lengthOfMonth();
        int startDayOfWeek = firstOfMonth.getDayOfWeek().getValue() % 7;  // Ajuste para domingo=0

        // Limpar o GridPane antes de preencher
        calendarGrid.getChildren().clear();

        // Definir o texto do mês e ano
        monthYearLabel.setText(yearMonth.getMonth().name() + " " + yearMonth.getYear());

        // Preencher os dias do mês no GridPane
        int dayCounter = 1;
        for (int row = 1; row <= 6; row++) {  // Ajustar para até 6 semanas
            for (int col = 0; col < 7; col++) {
                if (row == 1 && col < startDayOfWeek) {
                    // Preencher espaços vazios antes do primeiro dia do mês
                    calendarGrid.add(new Label(""), col, row);
                } else if (dayCounter <= daysInMonth) {
                    StackPane dayCell = createDayCell(dayCounter, yearMonth.atDay(dayCounter));
                    calendarGrid.add(dayCell, col, row);
                    dayCounter++;
                }
            }
        }
    }

    // Método para criar cada célula de dia
    private StackPane createDayCell(int day, LocalDate date) {
        Label dayLabel = new Label(String.valueOf(day));
        dayLabel.setStyle("-fx-font-size: 14px; -fx-text-fill: black;");

        Rectangle background = new Rectangle(60, 60);  // Tamanho da célula
        background.setFill(Color.DODGERBLUE);  // Cor padrão para um fundo azul
        background.setArcWidth(10);  // Bordas arredondadas
        background.setArcHeight(10);

        // Verificar se a data está no mapa de dias coloridos
        if (coloredDays.containsKey(date)) {
            background.setFill(coloredDays.get(date));  // Aplicar a cor do mapa
        }

        // Empilhar o rótulo do dia sobre o fundo colorido
        StackPane stackPane = new StackPane();
        stackPane.getChildren().addAll(background, dayLabel);

        // Adicionar borda para o dia atual
        if (date.equals(LocalDate.now())) {
            background.setStroke(Color.BLUE);  // Destaque azul para o dia atual
            background.setStrokeWidth(2);
        }

        return stackPane;
    }

    // Método para definir dias coloridos (exemplo: avaliação, dias especiais)
    private void setupColoredDays() {
        LocalDate avaliacaoStart = dateAvaliacaoStart;  // Início da avaliação
        LocalDate avaliacaoEnd = dateAvaliacaoEnd;    // Fim da avaliação

        // Definir o período de avaliação com uma cor
        for (LocalDate date = avaliacaoStart; !date.isAfter(avaliacaoEnd); date = date.plusDays(1)) {
            coloredDays.put(date, Color.LIGHTBLUE);  // Azul claro para o período de avaliação
        }

        coloredDays.put(LocalDate.now(), Color.BLUE);  // Azul para o dia atual
    }

        @FXML
        private void handleSair(javafx.event.ActionEvent event) throws IOException {
            // Criação do alerta de confirmação
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmação de Logout");
            alert.setHeaderText("Tem certeza de que deseja sair?");
            alert.setContentText("Você será redirecionado para a tela de login.");
        
            ButtonType buttonSim = new ButtonType("Sim");
            ButtonType buttonNao;
        buttonNao = new ButtonType("Não", ButtonBar.ButtonData.CANCEL_CLOSE);
            alert.getButtonTypes().setAll(buttonSim, buttonNao);
        
            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == buttonSim) {
                Aluno.AlunoLogado.logout();
                sceneSwitcher.switchScene("/FXML/LoginView.fxml", event);
            } else {
                
                alert.close();
            }
    }

    @FXML
    private void handlerealAvaliacao(ActionEvent event) throws IOException {
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
    @FXML
    private void handleEditarFoto(ActionEvent event) throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
            new FileChooser.ExtensionFilter("Imagens", "*.png", "*.jpg", "*.jpeg")
        );
    
        // Abrindo a janela de seleção de arquivos
        File selectedFile = fileChooser.showOpenDialog(pnlFoto.getScene().getWindow());
        if (selectedFile != null) {
            // Carregando a nova imagem
            Image image = new Image(selectedFile.toURI().toString());
            pnlFoto.setImage(image);
            // Lendo a imagem em bytes
            byte[] imageBytes = Files.readAllBytes(selectedFile.toPath());
    
            // Define a foto usando o array de bytes
            logado.setFoto(imageBytes);
            AlunoDAO.updateAluno(logado);
        } else {
            System.out.println("Nenhum arquivo selecionado.");
        }
    } 
}
