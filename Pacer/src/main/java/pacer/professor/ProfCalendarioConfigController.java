package pacer.professor;

import java.time.LocalDate;
import java.util.Optional;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import pacer.data.dao.CalendarioDAO;
import pacer.data.models.Calendario;
public class ProfCalendarioConfigController {

    @FXML
    private TextField sprintIdField; // Campo para o Sprint ID
    @FXML
    private DatePicker dataInicioPicker; // Data de Início
    @FXML
    private DatePicker dataFimPicker; // Data de Fim
    @FXML
    private Button salvarBtn;

    @FXML
    private void salvarCalendario(ActionEvent event) {
        // Obter os valores inseridos pelo professor
        String sprintIdText = sprintIdField.getText();
        LocalDate dataInicio = dataInicioPicker.getValue();
        LocalDate dataFim = dataFimPicker.getValue();

        // Validar os campos
        if (sprintIdText.isEmpty() || dataInicio == null || dataFim == null) {
            showAlert("Erro", "Por favor, preencha todos os campos.");
            return;
        }

        int sprintId;
        try {
            sprintId = Integer.parseInt(sprintIdText);
        } catch (NumberFormatException e) {
            showAlert("Erro", "Por favor, insira um número válido para o Sprint ID.");
            return;
        }

        if (dataInicio.isAfter(dataFim)) {
            showAlert("Erro", "A data de início não pode ser posterior à data de entrega.");
            return;
        }
        if(dataInicio.isBefore(LocalDate.now()) || dataFim.isBefore(LocalDate.now())){
            showAlert("Erro", "Nenhuma data pode ser marcada anterior ao dia de hoje.");
            return;
        }

        // Criar objeto de Calendario e salvar no banco
        Calendario calendario = new Calendario(0, sprintId, dataInicio, dataFim);

        CalendarioDAO.addCalendario(calendario);

        // Exibir mensagem de sucesso
        showAlert("Sucesso", "Calendário salvo com sucesso!");

        
        sprintIdField.clear();
        dataInicioPicker.setValue(LocalDate.now());
        dataFimPicker.setValue(LocalDate.now());
    }

    @FXML
    private void voltarTela(ActionEvent event) {
        // Criar um alerta de confirmação
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Confirmar retorno");
        alert.setHeaderText(null);
        alert.setContentText("Você irá retornar para a tela inicial. Tem certeza?");

        // Esperar a resposta do usuário
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            // Fechar a tela atual (ou realizar outra ação)
            Stage stage = (Stage) dataInicioPicker.getScene().getWindow();
            stage.close(); // Fecha a janela atual
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
