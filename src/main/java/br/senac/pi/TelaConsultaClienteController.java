
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

public class TelaConsultaClienteController implements Initializable {

    @FXML
    private TextField txtNome;
    @FXML
    private TextField txtEmail;
    @FXML
    private TextField txtSobreNome;
    @FXML
    private TableColumn<LinhaTabelaCliente, String> colunaNomeCliente;
    @FXML
    private TableColumn<LinhaTabelaCliente, String> colunaSobreNomeCliente;
    @FXML
    private TableColumn<LinhaTabelaCliente, String> colunaCpfCliente;
    @FXML
    private TableColumn<LinhaTabelaCliente, String> colunaRgCliente;
    @FXML
    private TableColumn<LinhaTabelaCliente, String> colunaEmailCliente;
    @FXML
    private TableView<LinhaTabelaCliente> tabelaCliente;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        colunaNomeCliente.setCellValueFactory(new PropertyValueFactory("nome"));
        colunaSobreNomeCliente.setCellValueFactory(new PropertyValueFactory("sobrenome"));
        colunaCpfCliente.setCellValueFactory(new PropertyValueFactory("cpf"));
        colunaRgCliente.setCellValueFactory(new PropertyValueFactory("rg"));
        colunaEmailCliente.setCellValueFactory(new PropertyValueFactory("email"));
        
    }    


    @FXML
    private void consultarCliente(ActionEvent event) {
        
        
    }

    
    
}
