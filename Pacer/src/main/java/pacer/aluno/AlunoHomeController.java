package Pacer.Aluno;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class AlunoHomeController implements Initializable {

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private GridPane calendarGrid;  // Vinculado ao novo calendário no FXML

    @FXML
    private Label monthYearLabel;  // Novo Label para exibir o mês e ano

    // Mapa para armazenar dias coloridos
    private Map<LocalDate, Color> coloredDays = new HashMap<>();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        CentralizarJanela(anchorPane);

        // Configurar o calendário customizado
        setupColoredDays();
        YearMonth currentMonth = YearMonth.now();  // Mês e ano atuais
        populateCalendar(currentMonth);
    }

    // Método para preencher o calendário com os dias do mês
    private void populateCalendar(YearMonth yearMonth) {
        LocalDate firstOfMonth = yearMonth.atDay(1);
        int daysInMonth = yearMonth.lengthOfMonth();
        int startDayOfWeek = firstOfMonth.getDayOfWeek().getValue() % 7;  // Ajuste para domingo=0

        // Limpar o GridPane antes de preencher
        calendarGrid.getChildren().clear();

        // Definir o texto do mês e ano
        monthYearLabel.setText(yearMonth.getMonth().name() + "  " + yearMonth.getYear());

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

        Rectangle background = new Rectangle(60, 60);  // Tamanho da célula aumentado
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
        LocalDate avaliacaoStart = LocalDate.now().plusDays(3);  // Início da avaliação
        LocalDate avaliacaoEnd = avaliacaoStart.plusDays(6);     // Fim da avaliação

        // Definir o período de avaliação com uma cor
        for (LocalDate date = avaliacaoStart; !date.isAfter(avaliacaoEnd); date = date.plusDays(1)) {
            coloredDays.put(date, Color.LIGHTBLUE);  // Azul claro para o período de avaliação
        }

        // Destacar o dia atual
        coloredDays.put(LocalDate.now(), Color.BLUE);  // Azul para o dia atual

        // Adicionar outros dias especiais conforme necessário
    }

    @FXML
    private void handleRealizarAval(javafx.event.ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/AlunoAvaliacaoView.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage;
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
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
