package pacer.professor;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
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
import pacer.utils.sceneSwitcher;



public class ProfImportarController {

    @FXML
    private TableView<Aluno> csvTableView;

    @FXML
    private TableColumn<Aluno, String> columnRa;
    @FXML
    private TableColumn<Aluno, String> columnNome;
    @FXML
    private TableColumn<Aluno, String> columnGrupo;
    @FXML
    private TableColumn<Aluno, String> columnEmail;
    @FXML
    private TableColumn<Aluno, String> columnLink;
    @FXML
    private TableColumn<Aluno, String> columnCurso;
    @FXML
    private TableColumn<Aluno, String> columnSemestre;

    @FXML
    private TextField RaField;
    @FXML
    private TextField nomeField;
    @FXML
    private TextField grupoField;
    @FXML
    private TextField emailField;
    @FXML
    private TextField linkField;
    @FXML
    private TextField cursoField;
    @FXML
    private TextField semestreField;

    // Mapa para armazenar os valores antigos antes da limpeza
    private Map<Aluno, Aluno> oldValuesMap = new HashMap<>();

    public class Aluno {
        private String ra;
        private String nome;
        private String email;
        private String grupo;
        private String link;
        private String curso;
        private String semestre;

        public Aluno(String ra, String nome, String email, String grupo, String link, String curso, String semestre) {
            this.ra = ra;
            this.nome = nome;
            this.email = email;
            this.grupo = grupo;
            this.link = link;
            this.curso = curso;
            this.semestre = semestre;
        }

        public String getRa() { return ra; }
        public void setRa(String ra) { this.ra = ra; }

        public String getNome() { return nome; }
        public void setNome(String nome) { this.nome = nome; }

        public String getEmail() { return email; }
        public void setEmail(String email) { this.email = email; }

        public String getGrupo() { return grupo; }
        public void setGrupo(String grupo) { this.grupo = grupo; }

        public String getLink() { return link; }
        public void setLink(String link) { this.link = link; }

        public String getCurso() { return curso; }
        public void setCurso(String curso) { this.curso = curso; }

        public String getSemestre() { return semestre; }
        public void setSemestre(String semestre) { this.semestre = semestre; }
    }

    @FXML
    private void initialize() {
        // Configura as colunas para exibir dados
        columnRa.setCellValueFactory(new PropertyValueFactory<>("ra"));
        columnNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        columnEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        columnGrupo.setCellValueFactory(new PropertyValueFactory<>("grupo"));
        columnLink.setCellValueFactory(new PropertyValueFactory<>("link"));
        columnCurso.setCellValueFactory(new PropertyValueFactory<>("curso"));
        columnSemestre.setCellValueFactory(new PropertyValueFactory<>("semestre"));

        // Permite edição nas células
        csvTableView.setEditable(true);
        columnRa.setCellFactory(TextFieldTableCell.forTableColumn());
        columnNome.setCellFactory(TextFieldTableCell.forTableColumn());
        columnEmail.setCellFactory(TextFieldTableCell.forTableColumn());
        columnGrupo.setCellFactory(TextFieldTableCell.forTableColumn());
        columnLink.setCellFactory(TextFieldTableCell.forTableColumn());
        columnCurso.setCellFactory(TextFieldTableCell.forTableColumn());
        columnSemestre.setCellFactory(TextFieldTableCell.forTableColumn());
    }

    @FXML
    public void handleImportCSV() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("CSV Files", "*.csv"));
        List<File> files = fileChooser.showOpenMultipleDialog(new Stage());

        if (files != null) {
            for (File file : files) {
                loadCSV(file);
            }
        }
    }

  private void loadCSV(File file) {
    try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8))) {
        String line;
        br.readLine(); // Ignora o cabeçalho

        while ((line = br.readLine()) != null) {
            String[] fields = line.split(";"); // Usando tabulação para separar os campos

            // Verifique se o número de campos é suficiente
            if (fields.length >= 7) {
                Aluno aluno = new Aluno(
                    fields[0], // Nome
                    fields[1], // Email
                    fields[2], // Grupo
                    fields[3], // Link
                    fields[4], // RA
                    fields[5], // Semestre
                    fields[6]  // Curso
                );
                csvTableView.getItems().add(aluno);
            } else {
                // Caso o número de campos seja insuficiente, você pode tratar da forma que preferir
                System.out.println("Linha inválida ou incompleta: " + line);
            }
        }
    } catch (IOException e) {
        e.printStackTrace();
    }
}


    @FXML
    public void handleAddAluno() {
        String ra = RaField.getText();
        String nome = nomeField.getText();
        String email = emailField.getText();
        String grupo = grupoField.getText();
        String link = linkField.getText();
        String curso = cursoField.getText();
        String semestre = semestreField.getText();

        if (!ra.isEmpty() && !nome.isEmpty() && !email.isEmpty()) {
            Aluno novoAluno = new Aluno( ra,nome, email, grupo, link, curso, semestre);
            csvTableView.getItems().add(novoAluno);

            RaField.clear();
            nomeField.clear();
            emailField.clear();
            grupoField.clear();
            linkField.clear();
            cursoField.clear();
            semestreField.clear();
        }
    }

    @FXML
    public void handleEditRa(TableColumn.CellEditEvent<Aluno, String> event) {
        Aluno aluno = event.getRowValue();
        aluno.setRa(event.getNewValue());
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
    public void handleEditLink(TableColumn.CellEditEvent<Aluno, String> event) {
        Aluno aluno = event.getRowValue();
        aluno.setLink(event.getNewValue());
    }

    @FXML
    public void handleEditCurso(TableColumn.CellEditEvent<Aluno, String> event) {
        Aluno aluno = event.getRowValue();
        aluno.setCurso(event.getNewValue());
    }

    @FXML
    public void handleEditSemestre(TableColumn.CellEditEvent<Aluno, String> event) {
        Aluno aluno = event.getRowValue();
        aluno.setSemestre(event.getNewValue());
    }

    // Implementação dos métodos de edição para as outras colunas

    @FXML
    public void handleClearRa() {
        Aluno selectedAluno = csvTableView.getSelectionModel().getSelectedItem();
        if (selectedAluno != null) {
            oldValuesMap.put(selectedAluno, new Aluno(selectedAluno.getRa(), selectedAluno.getNome(), selectedAluno.getEmail(), selectedAluno.getGrupo(), selectedAluno.getLink(), selectedAluno.getCurso(), selectedAluno.getSemestre()));
            selectedAluno.setRa("");
            csvTableView.refresh();
        }
    }

    @FXML
    public void handleClearNome() {
        Aluno selectedAluno = csvTableView.getSelectionModel().getSelectedItem();
        if (selectedAluno != null) {
            oldValuesMap.put(selectedAluno, new Aluno(selectedAluno.getRa(), selectedAluno.getNome(), selectedAluno.getEmail(), selectedAluno.getGrupo(), selectedAluno.getLink(), selectedAluno.getCurso(), selectedAluno.getSemestre()));
            selectedAluno.setNome("");
            csvTableView.refresh();
        }
    }

    @FXML
    public void handleClearEmail() {
        Aluno selectedAluno = csvTableView.getSelectionModel().getSelectedItem();
        if (selectedAluno != null) {
            oldValuesMap.put(selectedAluno, new Aluno(selectedAluno.getRa(), selectedAluno.getNome(), selectedAluno.getEmail(), selectedAluno.getGrupo(), selectedAluno.getLink(), selectedAluno.getCurso(), selectedAluno.getSemestre()));
            selectedAluno.setEmail("");
            csvTableView.refresh();
        }
    }

    @FXML
    public void handleClearGrupo() {
        Aluno selectedAluno = csvTableView.getSelectionModel().getSelectedItem();
        if (selectedAluno != null) {
            oldValuesMap.put(selectedAluno, new Aluno(selectedAluno.getRa(), selectedAluno.getNome(), selectedAluno.getEmail(), selectedAluno.getGrupo(), selectedAluno.getLink(), selectedAluno.getCurso(), selectedAluno.getSemestre()));
            selectedAluno.setGrupo("");
            csvTableView.refresh();
        }
    }

    @FXML
    public void handleClearLink() {
        Aluno selectedAluno = csvTableView.getSelectionModel().getSelectedItem();
        if (selectedAluno != null) {
            oldValuesMap.put(selectedAluno, new Aluno(selectedAluno.getRa(), selectedAluno.getNome(), selectedAluno.getEmail(), selectedAluno.getGrupo(), selectedAluno.getLink(), selectedAluno.getCurso(), selectedAluno.getSemestre()));
            selectedAluno.setLink("");
            csvTableView.refresh();
        }
    }

    @FXML
    public void handleClearCurso() {
        Aluno selectedAluno = csvTableView.getSelectionModel().getSelectedItem();
        if (selectedAluno != null) {
            oldValuesMap.put(selectedAluno, new Aluno(selectedAluno.getRa(), selectedAluno.getNome(), selectedAluno.getEmail(), selectedAluno.getGrupo(), selectedAluno.getLink(), selectedAluno.getCurso(), selectedAluno.getSemestre()));
            selectedAluno.setCurso("");
            csvTableView.refresh();
        }
    }

    @FXML
    public void handleClearSemestre() {
        Aluno selectedAluno = csvTableView.getSelectionModel().getSelectedItem();
        if (selectedAluno != null) {
            oldValuesMap.put(selectedAluno, new Aluno(selectedAluno.getRa(), selectedAluno.getNome(), selectedAluno.getEmail(), selectedAluno.getGrupo(), selectedAluno.getLink(), selectedAluno.getCurso(), selectedAluno.getSemestre()));
            selectedAluno.setSemestre("");
            csvTableView.refresh();
        }
    }

    @FXML
    private void handleVoltar(javafx.event.ActionEvent event) throws IOException {
        sceneSwitcher.switchScene("/FXML/ProfEquipeAddEditView.fxml", event);
    }


    // Outros métodos de limpeza para as colunas foram implementados similares

    @FXML
    public void handleUndoClear() {
        for (Aluno aluno : oldValuesMap.keySet()) {
            Aluno oldAluno = oldValuesMap.get(aluno);
            aluno.setRa(oldAluno.getRa());
            aluno.setNome(oldAluno.getNome());
            aluno.setEmail(oldAluno.getEmail());
            aluno.setGrupo(oldAluno.getGrupo());
            aluno.setLink(oldAluno.getLink());
            aluno.setCurso(oldAluno.getCurso());
            aluno.setSemestre(oldAluno.getSemestre());
        }
        oldValuesMap.clear();
        csvTableView.refresh();
    }
}
