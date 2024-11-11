package pacer.grupo;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javafx.stage.FileChooser;
import javafx.stage.Stage;
import pacer.data.dao.AlunoDAO;
import pacer.data.dao.AvaliacaoDAO;
import pacer.data.dao.CriteriosDAO;
import pacer.data.dao.GrupoDAO;
import pacer.data.models.Aluno;
import pacer.data.models.Avaliacao;
import pacer.data.models.Criterios;
import pacer.data.models.Grupo;

public class GrupoGerarRelatorio {
    
    public static void GenRelatorio(Grupo grupoSelecionado, Stage stage) {
        Grupo grupo = GrupoDAO.getGrupoComAlunos(grupoSelecionado.getId());
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Salvar Relatório");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Excel Files", "*.xlsx"));
    
        fileChooser.setInitialFileName("Relatorio - " + grupoSelecionado.getNome() + ".xlsx");
        File arquivo = fileChooser.showSaveDialog(stage);
    
        if (arquivo != null) {
            try (Workbook workbook = new XSSFWorkbook()) {
                Sheet sheet = workbook.createSheet("Relatório de Grupo");
    
                int rowNum = 0;
    
                if (grupo != null) {
                    sheet.createRow(rowNum++).createCell(0).setCellValue("Grupo: " + grupo.getNome());
                    sheet.createRow(rowNum++).createCell(0).setCellValue("Repositório: " + grupo.getReposLink());
                    sheet.createRow(rowNum++).createCell(0).setCellValue("Curso: " + grupo.getCursoSigla());
                    sheet.createRow(rowNum++).createCell(0).setCellValue("Semestre: " + grupo.getSemestre());
                    sheet.createRow(rowNum++).createCell(0).setCellValue("");
    
                    Row alunosTitleRow = sheet.createRow(rowNum++);
                    Cell alunosTitleCell = alunosTitleRow.createCell(0);
                    alunosTitleCell.setCellValue("Alunos integrantes do grupo");
    
                    CellStyle titleStyle = workbook.createCellStyle();
                    titleStyle.setAlignment(HorizontalAlignment.CENTER);
                    alunosTitleCell.setCellStyle(titleStyle);
    
                    Row headerRow = sheet.createRow(rowNum++);
                    CellStyle headerStyle = workbook.createCellStyle();
                    headerStyle.setAlignment(HorizontalAlignment.CENTER);
    
                    Cell raHeader = headerRow.createCell(0);
                    raHeader.setCellValue("RA");
                    raHeader.setCellStyle(headerStyle);
    
                    Cell nomeHeader = headerRow.createCell(1);
                    nomeHeader.setCellValue("Nome");
                    nomeHeader.setCellStyle(headerStyle);
    
                    Cell emailHeader = headerRow.createCell(2);
                    emailHeader.setCellValue("Email");
                    emailHeader.setCellStyle(headerStyle);
    
                    List<Criterios> criteriosList = CriteriosDAO.getAll().stream()
                            .filter(Criterios::isAtivo)
                            .collect(Collectors.toList());
    
                    int numColunas = 3;
                    for (Criterios criterio : criteriosList) {
                        Cell criterioHeader = headerRow.createCell(numColunas++);
                        criterioHeader.setCellValue(criterio.getNome());
                        criterioHeader.setCellStyle(headerStyle);
                    }
    
                    sheet.addMergedRegion(new CellRangeAddress(rowNum - 2, rowNum - 2, 0, numColunas - 1));
    
                    List<Aluno> alunos = AlunoDAO.getAlunosDoGrupo(grupoSelecionado.getId());
                    for (Aluno aluno : alunos) {
                        Row row = sheet.createRow(rowNum++);
                        Cell raCell = row.createCell(0);
                        raCell.setCellValue(aluno.getRa());
    
                        CellStyle leftAlignStyle = workbook.createCellStyle();
                        leftAlignStyle.setAlignment(HorizontalAlignment.LEFT);
                        raCell.setCellStyle(leftAlignStyle);
    
                        row.createCell(1).setCellValue(aluno.getNome());
                        row.createCell(2).setCellValue(aluno.getEmail());
    
                        int colIndex = 3;
                        for (Criterios criterio : criteriosList) {
                            double somaNotas = 0;
                            int totalAvaliacoes = 0;
    
                            List<Aluno> alunosDoGrupo = AlunoDAO.getAlunosDoGrupo(grupoSelecionado.getId());
                            for (Aluno avaliador : alunosDoGrupo) {
                                if (avaliador.getRa() != aluno.getRa()) {
                                    Avaliacao avaliacao = AvaliacaoDAO.getAvaliacaoPorAlunoECriterio(avaliador.getRa(), aluno.getRa(), criterio.getId());
                                    if (avaliacao != null) {
                                        somaNotas += avaliacao.getNota();
                                        totalAvaliacoes++;
                                    }
                                }
                            }
                            double mediaNota = (totalAvaliacoes > 0) ? somaNotas / totalAvaliacoes : 0;
                            row.createCell(colIndex++).setCellValue(mediaNota);
                        }
                    }
                    for (int i = 0; i < numColunas; i++) {
                        sheet.autoSizeColumn(i);
                    }
                }
                try (FileOutputStream fileOut = new FileOutputStream(arquivo)) {
                    workbook.write(fileOut);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
