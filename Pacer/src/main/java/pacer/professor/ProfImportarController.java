package pacer.professor;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import pacer.data.DatabaseConnection;
import pacer.utils.sceneSwitcher;

public class ProfImportarController {

    @FXML
    private TableView<Aluno> csvTableView;

    @FXML
    private TableColumn<Aluno, String> columnRa;
    @FXML
    private TableColumn<Aluno, String> columnNome;
    @FXML
    private TableColumn<Aluno, String> columnEmail;
    @FXML
    private TableColumn<Aluno, String> columnCurso;
    @FXML
    private TableColumn<Aluno, String> columnSemestre;

    @FXML
    private TextField RaField;
    @FXML
    private TextField nomeField;
    @FXML
    private TextField emailField;
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
        private String curso;
        private String semestre;

        public Aluno(String ra, String nome, String email, String curso, String semestre) {
            this.ra = ra;
            this.nome = nome;
            this.email = email;
            this.curso = curso;
            this.semestre = semestre;
        }

        public String getRa() {
            return ra;
        }

        public void setRa(String ra) {
            this.ra = ra;
        }

        public String getNome() {
            return nome;
        }

        public void setNome(String nome) {
            this.nome = nome;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getCurso() {
            return curso;
        }

        public void setCurso(String curso) {
            this.curso = curso;
        }

        public String getSemestre() {
            return semestre;
        }

        public void setSemestre(String semestre) {
            this.semestre = semestre;
        }
    }

    @FXML
    private void initialize() {
        // Configura as colunas para exibir dados
        columnRa.setCellValueFactory(new PropertyValueFactory<>("ra"));
        columnNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        columnEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        columnCurso.setCellValueFactory(new PropertyValueFactory<>("curso"));
        columnSemestre.setCellValueFactory(new PropertyValueFactory<>("semestre"));

        // Permite edição nas células
        csvTableView.setEditable(true);
        columnRa.setCellFactory(TextFieldTableCell.forTableColumn());
        columnNome.setCellFactory(TextFieldTableCell.forTableColumn());
        columnEmail.setCellFactory(TextFieldTableCell.forTableColumn());
        columnCurso.setCellFactory(TextFieldTableCell.forTableColumn());
        columnSemestre.setCellFactory(TextFieldTableCell.forTableColumn());

        // Define o comportamento de commit para cada coluna
        columnRa.setOnEditCommit(event -> event.getRowValue().setRa(event.getNewValue()));
        columnNome.setOnEditCommit(event -> event.getRowValue().setNome(event.getNewValue()));
        columnEmail.setOnEditCommit(event -> event.getRowValue().setEmail(event.getNewValue()));
        columnCurso.setOnEditCommit(event -> event.getRowValue().setCurso(event.getNewValue()));
        columnSemestre.setOnEditCommit(event -> event.getRowValue().setSemestre(event.getNewValue()));
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
                // Filtra caracteres indesejados, como controle de linha ou caracteres não imprimíveis
                line = line.replaceAll("[^\\x00-\\x7F]", ""); // Remove caracteres não ASCII
    
                String[] fields = line.split(";"); // Usando ponto e vírgula como delimitador
    
                // Verifique se o número de campos é suficiente e se os dados não estão vazios
                if (fields.length == 5 && !fields[0].isEmpty() && !fields[1].isEmpty()) {
                    Aluno aluno = new Aluno(
                            fields[0], // RA
                            fields[1], // Nome
                            fields[2], // Email
                            fields[3], // Curso
                            fields[4]  // Semestre
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
        String curso = cursoField.getText();
        String semestre = semestreField.getText();

        if (!ra.isEmpty() && !nome.isEmpty() && !email.isEmpty()) {
            Aluno novoAluno = new Aluno(ra, nome, email, curso, semestre);
            csvTableView.getItems().add(novoAluno);

            RaField.clear();
            nomeField.clear();
            emailField.clear();
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
            oldValuesMap.put(selectedAluno, new Aluno(selectedAluno.getRa(), selectedAluno.getNome(), selectedAluno.getEmail(), selectedAluno.getCurso(), selectedAluno.getSemestre()));
            selectedAluno.setRa("");
            csvTableView.refresh();
        }
    }

    @FXML
    public void handleClearNome() {
        Aluno selectedAluno = csvTableView.getSelectionModel().getSelectedItem();
        if (selectedAluno != null) {
            oldValuesMap.put(selectedAluno, new Aluno(selectedAluno.getRa(), selectedAluno.getNome(), selectedAluno.getEmail(), selectedAluno.getCurso(), selectedAluno.getSemestre()));
            selectedAluno.setNome("");
            csvTableView.refresh();
        }
    }

    @FXML
    public void handleClearEmail() {
        Aluno selectedAluno = csvTableView.getSelectionModel().getSelectedItem();
        if (selectedAluno != null) {
            oldValuesMap.put(selectedAluno, new Aluno(selectedAluno.getRa(), selectedAluno.getNome(), selectedAluno.getEmail(), selectedAluno.getCurso(), selectedAluno.getSemestre()));
            selectedAluno.setEmail("");
            csvTableView.refresh();
        }
    }

    @FXML
    public void handleClearGrupo() {
        Aluno selectedAluno = csvTableView.getSelectionModel().getSelectedItem();
        if (selectedAluno != null) {
            oldValuesMap.put(selectedAluno, new Aluno(selectedAluno.getRa(), selectedAluno.getNome(), selectedAluno.getEmail(), selectedAluno.getCurso(), selectedAluno.getSemestre()));
            csvTableView.refresh();
        }
    }

    @FXML
    public void handleClearLink() {
        Aluno selectedAluno = csvTableView.getSelectionModel().getSelectedItem();
        if (selectedAluno != null) {
            oldValuesMap.put(selectedAluno, new Aluno(selectedAluno.getRa(), selectedAluno.getNome(), selectedAluno.getEmail(), selectedAluno.getCurso(), selectedAluno.getSemestre()));
            csvTableView.refresh();
        }
    }

    @FXML
    public void handleClearCurso() {
        Aluno selectedAluno = csvTableView.getSelectionModel().getSelectedItem();
        if (selectedAluno != null) {
            oldValuesMap.put(selectedAluno, new Aluno(selectedAluno.getRa(), selectedAluno.getNome(), selectedAluno.getEmail(), selectedAluno.getCurso(), selectedAluno.getSemestre()));
            selectedAluno.setCurso("");
            csvTableView.refresh();
        }
    }

    @FXML
    public void handleClearSemestre() {
        Aluno selectedAluno = csvTableView.getSelectionModel().getSelectedItem();
        if (selectedAluno != null) {
            oldValuesMap.put(selectedAluno, new Aluno(selectedAluno.getRa(), selectedAluno.getNome(), selectedAluno.getEmail(), selectedAluno.getCurso(), selectedAluno.getSemestre()));
            selectedAluno.setSemestre("");
            csvTableView.refresh();
        }
    }

    @FXML
    private void handleVoltar(ActionEvent event) throws IOException {
        sceneSwitcher.switchScene("/FXML/ProfEquipesView.fxml", event);

    }

    // Outros métodos de limpeza para as colunas foram implementados similares
    @FXML
    public void handleUndoClear() {
        for (Aluno aluno : oldValuesMap.keySet()) {
            Aluno oldAluno = oldValuesMap.get(aluno);
            aluno.setRa(oldAluno.getRa());
            aluno.setNome(oldAluno.getNome());
            aluno.setEmail(oldAluno.getEmail());
            aluno.setCurso(oldAluno.getCurso());
            aluno.setSemestre(oldAluno.getSemestre());
        }
        oldValuesMap.clear();
        csvTableView.refresh();
    }

    // Método para carregar alunos do banco de dados
    private void loadAlunosFromDatabase() {
        String sql = "SELECT * FROM aluno"; // Substitua 'alunos' pelo nome da sua tabela
        try (Connection connection = DatabaseConnection.getConnection(); PreparedStatement stmt = connection.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                String ra = rs.getString("aluno_ra");
                String nome = rs.getString("aluno_nome");
                String email = rs.getString("aluno_email");
                String curso = rs.getString("curso_sigla");
                String semestre = rs.getString("semestre");

                Aluno aluno = new Aluno(ra, nome, email, curso, semestre);
                csvTableView.getItems().add(aluno);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para salvar alunos no banco de dados
    private void saveAlunosToDatabase() {
        String sql = "INSERT INTO aluno (aluno_ra, aluno_nome, aluno_email, curso_sigla, semestre) VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = DatabaseConnection.getConnection(); PreparedStatement stmt = connection.prepareStatement(sql)) {

            for (Aluno aluno : csvTableView.getItems()) {
                stmt.setString(1, aluno.getRa());
                stmt.setString(2, aluno.getNome());
                stmt.setString(3, aluno.getEmail());
                stmt.setString(4, aluno.getCurso());
                stmt.setString(5, aluno.getSemestre());

                stmt.addBatch();
            }

            stmt.executeBatch(); // Executa todas as inserções de uma vez

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para atualizar aluno no banco de dados
    private void updateAlunoInDatabase(Aluno aluno) {
        String sql = "UPDATE aluno SET aluno_nome = ?, aluno_email = ?, curso_sigla = ?, semestre = ? WHERE aluno_ra = ?";
        try (Connection connection = DatabaseConnection.getConnection(); PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, aluno.getNome());
            stmt.setString(2, aluno.getEmail());
            stmt.setString(5, aluno.getCurso());
            stmt.setString(6, aluno.getSemestre());
            stmt.setString(7, aluno.getRa());

            stmt.executeUpdate(); // Atualiza o aluno no banco

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void handleSalvar() {
        saveAlunosToDatabase();
    }

}
