package pacer.professor;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class ProfImportarController {

    @FXML
    private TableView<Aluno> csvTableView;

    @FXML
    private TableColumn<Aluno, String> columnNome;

    @FXML
    private TableColumn<Aluno, String> columnEmail;

    @FXML
    private TableColumn<Aluno, String> columnGrupo;

    @FXML
    private TextField nomeField;

    @FXML
    private TextField emailField;

    @FXML
    private TextField grupoField;

    // Mapa para armazenar os valores antigos antes da limpeza
    private Map<Aluno, Aluno> oldValuesMap = new HashMap<>();

    public class Aluno {
        private String nome;
        private String email;
        private String grupo;

        public Aluno(String nome, String email, String grupo) {
            this.nome = nome;
            this.email = email;
            this.grupo = grupo;
        }

        public String getNome() { return nome; }
        public void setNome(String nome) { this.nome = nome; }

        public String getEmail() { return email; }
        public void setEmail(String email) { this.email = email; }

        public String getGrupo() { return grupo; }
        public void setGrupo(String grupo) { this.grupo = grupo; }
    }

    @FXML
    private void initialize() {
        // Configura as colunas para exibir dados
        columnNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        columnEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        columnGrupo.setCellValueFactory(new PropertyValueFactory<>("grupo"));

        // Permite edição nas células
        csvTableView.setEditable(true);
        columnNome.setCellFactory(TextFieldTableCell.forTableColumn());
        columnEmail.setCellFactory(TextFieldTableCell.forTableColumn());
        columnGrupo.setCellFactory(TextFieldTableCell.forTableColumn());
    }

    @FXML
    public void handleImportCSV() {
        // Criando um FileChooser para abrir os arquivos CSV
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("CSV Files", "*.csv"));

        // Permitir a seleção de múltiplos arquivos
        List<File> files = fileChooser.showOpenMultipleDialog(new Stage());

        if (files != null) {
            for (File file : files) {
                loadCSV(file);
            }
        }
    }

    private void loadCSV(File file) {
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;

            // Ignora a primeira linha (cabeçalho)
            br.readLine();

            while ((line = br.readLine()) != null) {
                String[] fields = line.split(",");

                // Criando um objeto Aluno com os campos lidos
                Aluno aluno = new Aluno(fields[0], fields[1], fields[2]);

                // Adiciona o aluno à tabela
                csvTableView.getItems().add(aluno);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void handleAddAluno() {
        String nome = nomeField.getText();
        String email = emailField.getText();
        String grupo = grupoField.getText();

        if (!nome.isEmpty() && !email.isEmpty() && !grupo.isEmpty()) {
            Aluno novoAluno = new Aluno(nome, email, grupo);
            csvTableView.getItems().add(novoAluno);

            // Limpa os campos após a adição
            nomeField.clear();
            emailField.clear();
            grupoField.clear();
        }
    }

    @FXML
    public void handleEditNome(TableColumn.CellEditEvent<Aluno, String> event) {
        Aluno aluno = event.getRowValue();
        aluno.setNome(event.getNewValue());
    }

    @FXML
    public void handleEditEmail(TableColumn.CellEditEvent<Aluno, String> event) {
        Aluno aluno = event.getRowValue();
        aluno.setEmail(event.getNewValue());
    }

    @FXML
    public void handleEditGrupo(TableColumn.CellEditEvent<Aluno, String> event) {
        Aluno aluno = event.getRowValue();
        aluno.setGrupo(event.getNewValue());
    }

    @FXML
    public void handleClearNome() {
        Aluno selectedAluno = csvTableView.getSelectionModel().getSelectedItem();
        if (selectedAluno != null) {
            oldValuesMap.put(selectedAluno, new Aluno(selectedAluno.getNome(), selectedAluno.getEmail(), selectedAluno.getGrupo()));
            selectedAluno.setNome("");
            csvTableView.refresh();
        }
    }

    @FXML
    public void handleClearEmail() {
        Aluno selectedAluno = csvTableView.getSelectionModel().getSelectedItem();
        if (selectedAluno != null) {
            oldValuesMap.put(selectedAluno, new Aluno(selectedAluno.getNome(), selectedAluno.getEmail(), selectedAluno.getGrupo()));
            selectedAluno.setEmail("");
            csvTableView.refresh();
        }
    }

    @FXML
    public void handleClearGrupo() {
        Aluno selectedAluno = csvTableView.getSelectionModel().getSelectedItem();
        if (selectedAluno != null) {
            oldValuesMap.put(selectedAluno, new Aluno(selectedAluno.getNome(), selectedAluno.getEmail(), selectedAluno.getGrupo()));
            selectedAluno.setGrupo("");
            csvTableView.refresh();
        }
    }

    @FXML
    public void handleUndoClear() {
        for (Aluno aluno : oldValuesMap.keySet()) {
            Aluno oldAluno = oldValuesMap.get(aluno);
            if (oldAluno != null) {
                aluno.setNome(oldAluno.getNome());
                aluno.setEmail(oldAluno.getEmail());
                aluno.setGrupo(oldAluno.getGrupo());
            }
        }
        oldValuesMap.clear();
        csvTableView.refresh();
    }
}
