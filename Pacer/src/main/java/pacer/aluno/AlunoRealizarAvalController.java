package pacer.aluno;

import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Toggle;
import pacer.utils.sceneSwitcher;
import javafx.scene.control.ToggleGroup;

public class AlunoRealizarAvalController {

    @FXML
    private RadioButton autonomia0;
    @FXML
    private RadioButton autonomia1;
    @FXML
    private RadioButton autonomia2;
    @FXML
    private RadioButton autonomia3;

    @FXML
    private RadioButton colaboracao0;
    @FXML
    private RadioButton colaboracao1;
    @FXML
    private RadioButton colaboracao2;
    @FXML
    private RadioButton colaboracao3;

    @FXML
    private RadioButton entrega0;
    @FXML
    private RadioButton entrega1;
    @FXML
    private RadioButton entrega2;
    @FXML
    private RadioButton entrega3;

    @FXML
    private RadioButton resultados0;
    @FXML
    private RadioButton resultados1;
    @FXML
    private RadioButton resultados2;
    @FXML
    private RadioButton resultados3;

    // Declare ToggleGroups
    private ToggleGroup autonomiaGroup = new ToggleGroup();
    private ToggleGroup colaboracaoGroup = new ToggleGroup();
    private ToggleGroup entregaGroup = new ToggleGroup();
    private ToggleGroup resultadosGroup = new ToggleGroup();

    @FXML
    public void initialize() {
        // Set ToggleGroups for Autonomia
        autonomia0.setToggleGroup(autonomiaGroup);
        autonomia1.setToggleGroup(autonomiaGroup);
        autonomia2.setToggleGroup(autonomiaGroup);
        autonomia3.setToggleGroup(autonomiaGroup);

        // Set ToggleGroups for Colaboração
        colaboracao0.setToggleGroup(colaboracaoGroup);
        colaboracao1.setToggleGroup(colaboracaoGroup);
        colaboracao2.setToggleGroup(colaboracaoGroup);
        colaboracao3.setToggleGroup(colaboracaoGroup);

        // Set ToggleGroups for Entrega
        entrega0.setToggleGroup(entregaGroup);
        entrega1.setToggleGroup(entregaGroup);
        entrega2.setToggleGroup(entregaGroup);
        entrega3.setToggleGroup(entregaGroup);

        // Set ToggleGroups for Resultados
        resultados0.setToggleGroup(resultadosGroup);
        resultados1.setToggleGroup(resultadosGroup);
        resultados2.setToggleGroup(resultadosGroup);
        resultados3.setToggleGroup(resultadosGroup);
    }

    @FXML
    public void enviarAvaliacao() {
        // Captura o valor do grupo 'Autonomia'
        Toggle selectedAutonomia = autonomiaGroup.getSelectedToggle();
        if (selectedAutonomia != null) {
            String autonomiaValue = ((RadioButton) selectedAutonomia).getText();
            System.out.println("Autonomia: " + autonomiaValue);
        }
    
        // Captura o valor do grupo 'Colaboração'
        Toggle selectedColaboracao = colaboracaoGroup.getSelectedToggle();
        if (selectedColaboracao != null) {
            String colaboracaoValue = ((RadioButton) selectedColaboracao).getText();
            System.out.println("Colaboração: " + colaboracaoValue);
        }
    
        // Captura o valor do grupo 'Entrega'
        Toggle selectedEntrega = entregaGroup.getSelectedToggle();
        if (selectedEntrega != null) {
            String entregaValue = ((RadioButton) selectedEntrega).getText();
            System.out.println("Entrega: " + entregaValue);
        }
    
        // Captura o valor do grupo 'Resultados'
        Toggle selectedResultados = resultadosGroup.getSelectedToggle();
        if (selectedResultados != null) {
            String resultadosValue = ((RadioButton) selectedResultados).getText();
            System.out.println("Resultados: " + resultadosValue);
        }
    }
}
