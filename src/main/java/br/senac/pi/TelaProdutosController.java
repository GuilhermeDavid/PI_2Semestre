package br.senac.pi;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class TelaProdutosController implements Initializable {

    @FXML
    private TableView<LinhaTabelaProdutos> tabelaProdutos;
    @FXML
    private TableColumn<LinhaTabelaProdutos, String> colunaNome;
    @FXML
    private TableColumn<LinhaTabelaProdutos, String> colunaMarca;
    @FXML
    private TableColumn<LinhaTabelaProdutos, String> colunaTipo;
    @FXML
    private TableColumn<LinhaTabelaProdutos, String> colunaQuantidade;
    @FXML
    private TableColumn<LinhaTabelaProdutos, Double> colunaPreco;
    @FXML
    private TextField txtNomeProduto;
    @FXML
    private TextField txtTipoProduto;
    @FXML
    private TextField txtQuantidadeProduto;
    @FXML
    private TextField txtMarcaProduto;
    @FXML
    private TextField txtPrecoProduto;
 
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        colunaNome.setCellValueFactory(new PropertyValueFactory("nome"));
        colunaMarca.setCellValueFactory(new PropertyValueFactory("marca"));
        colunaTipo.setCellValueFactory(new PropertyValueFactory("tipo"));
        colunaQuantidade.setCellValueFactory(new PropertyValueFactory("quantidade"));
        colunaPreco.setCellValueFactory(new PropertyValueFactory("preco"));
        
        tabelaProdutos.getItems().add(new LinhaTabelaProdutos("RTX 3080","Nvidia","Placa de vídeo", "3", 11324.66));
        tabelaProdutos.getItems().add(new LinhaTabelaProdutos("I7 9700K","Intel","Processador", "8", 2345.32));
        tabelaProdutos.getItems().add(new LinhaTabelaProdutos("SSD 500GB","Kingston","Memória", "13", 598.90));
        
    }    

    @FXML
    private void addProduto(ActionEvent event) {
        double precoProduto = Double.parseDouble(txtPrecoProduto.getText());
        
        tabelaProdutos.getItems().add(new LinhaTabelaProdutos(txtNomeProduto.getText(), txtMarcaProduto.getText(), 
                txtTipoProduto.getText(), txtQuantidadeProduto.getText(), precoProduto));
    }

    @FXML
    private void excluirProduto(ActionEvent event) {
        tabelaProdutos.getItems().remove(tabelaProdutos.getSelectionModel().getSelectedItem());
    }

    @FXML
    private void attProduto(ActionEvent event) {
        
    }

    @FXML
    private void consultProduto(ActionEvent event) {
    }
    
}
