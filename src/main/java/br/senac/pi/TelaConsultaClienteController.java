
package br.senac.pi;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

public class TelaConsultaClienteController implements Initializable {

    @FXML
    private TextField txtNome;
    @FXML
    private TextField txtEmail;
    @FXML
    private TextField txtSobreNome;
    @FXML
    private TextField txtRg;
    @FXML
    private TextField txtCpf;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }    

    @FXML
    private void voltarTelaPrincipal(ActionEvent event) {
    }

    @FXML
    private void consultarCliente(ActionEvent event) {
    }

    
    
}
