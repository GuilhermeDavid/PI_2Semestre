package br.senac.pi;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class TelaVendaController implements Initializable {

    @FXML
    private TableView<?> tabelaVenda;
    @FXML
    private TableColumn<?, ?> colunaCliente;
    @FXML
    private TableColumn<?, ?> colunaProduto;
    @FXML
    private TableColumn<?, ?> colunaQtd;
    @FXML
    private TableColumn<?, ?> colunaPreco;
    @FXML
    private TextField txtCodProduto;
    @FXML
    private TextField txtQnt;
    @FXML
    private TextField txtPrecoTotal;
    @FXML
    private ComboBox comboFormaPagamento;
    @FXML
    private ComboBox comboParcelas;
    @FXML
    private TextField txtCpfCliente;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        comboFormaPagamento.getItems().add("Dinheiro");
        comboFormaPagamento.getItems().add("Debito");
        comboFormaPagamento.getItems().add("Credito");
        comboFormaPagamento.getItems().add("Pix");
        
        comboParcelas.getItems().add("1X");
        comboParcelas.getItems().add("2X");
        comboParcelas.getItems().add("3X");
        comboParcelas.getItems().add("4X");
        comboParcelas.getItems().add("6X");
        comboParcelas.getItems().add("7X");
        comboParcelas.getItems().add("8X");
        comboParcelas.getItems().add("9X");
        comboParcelas.getItems().add("10X");
        comboParcelas.getItems().add("11X");
        comboParcelas.getItems().add("12X");
       
    }    

    @FXML
    private void excluir(ActionEvent event) {
    }

    @FXML
    private void adicionar(ActionEvent event) {
    }

    @FXML
    private void actionComboPagamentos(ActionEvent event) {
        int creditoPagamento = comboFormaPagamento.getSelectionModel().getSelectedIndex();
        
        if(creditoPagamento == 2){
            comboParcelas.setDisable(false);
            
        }else {
            comboParcelas.setDisable(true);
            comboParcelas.getSelectionModel().clearSelection();
        }
        
    }

    @FXML
    private void finalizarCompra(ActionEvent event) {
    }
    
}
