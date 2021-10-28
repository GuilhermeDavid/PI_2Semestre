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
    private TextField txtEmailCliente;
    @FXML
    private TextField txtCodProduto;
    @FXML
    private TextField txtQnt;
    @FXML
    private TextField txtPrecoTotal;
    @FXML
    private ComboBox comboFormaPagamento;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        comboFormaPagamento.getItems().add("Dinheiro");
        comboFormaPagamento.getItems().add("Debito");
        comboFormaPagamento.getItems().add("Credito");
        comboFormaPagamento.getItems().add("Pix");
       
    }    

    @FXML
    private void excluir(ActionEvent event) {
    }

    @FXML
    private void adicionar(ActionEvent event) {
    }
    
}
