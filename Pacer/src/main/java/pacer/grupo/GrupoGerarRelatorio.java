package pacer.grupo;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javafx.scene.control.Alert.AlertType;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import pacer.data.dao.GrupoDAO;
import pacer.data.models.Aluno;
import pacer.data.models.Grupo;
import pacer.utils.mbox;

public class GrupoGerarRelatorio {
    
    public static void GenRelatorio(Grupo grupoSelecionado, Stage stage) {
        Grupo grupo = GrupoDAO.getGrupoComAlunos(grupoSelecionado.getId());
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Salvar Relatório");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Excel Files", "*.xlsx"));
    
        // Definindo um nome padrão para o arquivo
        fileChooser.setInitialFileName("Relatorio - " + grupoSelecionado.getNome() + ".xlsx");
        File arquivo = fileChooser.showSaveDialog(stage);
    
        if (arquivo != null) {
            try (Workbook workbook = new XSSFWorkbook()) {
                Sheet sheet = workbook.createSheet("Relatório de Grupo");
    
                var rowNum = 0;
    
                // Add informações do grupo
                if (grupo != null) {
                    //#region Inf. grupo
                    sheet.createRow(rowNum++).createCell(0).setCellValue("Grupo: " + grupo.getNome());
                    sheet.createRow(rowNum++).createCell(0).setCellValue("Repositório: " + grupo.getReposLink());
                    sheet.createRow(rowNum++).createCell(0).setCellValue("Curso: " + grupo.getCursoSigla());
                    sheet.createRow(rowNum++).createCell(0).setCellValue("Semestre: " + grupo.getSemestre());
                    sheet.createRow(rowNum++).createCell(0).setCellValue("");
                    //#endregion 

                    //#region Inf. integrantes
                    // Adicionar título para a seção de alunos
                    Row alunosTitleRow = sheet.createRow(rowNum++);
                    Cell alunosTitleCell = alunosTitleRow.createCell(0);
                    alunosTitleCell.setCellValue("Alunos integrantes do grupo");
                    // Mesclar celulas
                    sheet.addMergedRegion(new CellRangeAddress(rowNum - 1, rowNum - 1, 0, 2));
                    // Estilo centralizado e negrito
                    CellStyle titleStyle = workbook.createCellStyle();
                    titleStyle.setAlignment(HorizontalAlignment.CENTER);
                    Font titleFont = workbook.createFont();
                    titleFont.setBold(true);
                    titleStyle.setFont(titleFont);
                    alunosTitleCell.setCellStyle(titleStyle);
    
                    // Add cabeçalho da tabela
                    Row headerRow = sheet.createRow(rowNum++);
                    headerRow.createCell(0).setCellValue("RA");
                    headerRow.createCell(1).setCellValue("Nome");
                    headerRow.createCell(2).setCellValue("Email");
    
                    // Definir estilo para as células do cabeçalho
                    CellStyle headerStyle = workbook.createCellStyle();
                    headerStyle.setAlignment(HorizontalAlignment.CENTER);
                    for (Cell cell : headerRow) {
                        cell.setCellStyle(headerStyle);
                    }
    
                    // Add informações dos alunos
                    CellStyle leftAlignStyle = workbook.createCellStyle();
                    leftAlignStyle.setAlignment(HorizontalAlignment.LEFT);
    
                    if (grupo.getAlunos() != null && !grupo.getAlunos().isEmpty()) {
                        for (Aluno aluno : grupo.getAlunos()) {
                            Row alunoRow = sheet.createRow(rowNum++);
                            Cell raCell = alunoRow.createCell(0);
                            raCell.setCellValue(aluno.getRa());
                            raCell.setCellStyle(leftAlignStyle);
    
                            Cell nomeCell = alunoRow.createCell(1);
                            nomeCell.setCellValue(aluno.getNome());
                            nomeCell.setCellStyle(leftAlignStyle);
    
                            Cell emailCell = alunoRow.createCell(2);
                            emailCell.setCellValue(aluno.getEmail());
                            emailCell.setCellStyle(leftAlignStyle);
                        }
                    } else {
                        Row alunoRow = sheet.createRow(rowNum++);
                        alunoRow.createCell(0).setCellValue("Nenhum aluno associado ao grupo.");
                    }
                } else {
                    Row errorRow = sheet.createRow(1);
                    errorRow.createCell(0).setCellValue("Grupo não encontrado.");
                }
                //#endregion
    
                // Tamanho das colunas
                for (int i = 0; i < 3; i++) {
                    sheet.autoSizeColumn(i);
                }
    
                // Salvar arquivo
                try (FileOutputStream fileOut = new FileOutputStream(arquivo)) {
                    workbook.write(fileOut);
                    mbox.ShowMessageBox(AlertType.INFORMATION, "Relatório", "Relatório do grupo " + grupoSelecionado.getNome() + " salvo com sucesso!");
                } catch (IOException e) {
                    mbox.ShowMessageBox(AlertType.ERROR, "Relatório", "Ocorreu um erro ao salvar o relatório do grupo " + grupoSelecionado.getNome() + ".");
                    e.printStackTrace();
                }
            } catch (IOException e) {
            }
        }
    }
}