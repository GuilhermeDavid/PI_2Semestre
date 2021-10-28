package br.senac.pi;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class TelaRelatorioController implements Initializable {

    @FXML
    private TableView<?> tabelaRelatorio;
    @FXML
    private TableColumn<?, ?> colunaCliente;
    @FXML
    private TableColumn<?, ?> colunaProduto;
    @FXML
    private TableColumn<?, ?> colunaValor;
    @FXML
    private TableColumn<?, ?> colunaData;
    @FXML
    private DatePicker dataInicio;
    @FXML
    private DatePicker dataFinal;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    

    @FXML
    private void validar(ActionEvent event) {
    }
}
