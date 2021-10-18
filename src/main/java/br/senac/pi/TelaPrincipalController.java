package br.senac.pi;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;

public class TelaPrincipalController implements Initializable {

    @FXML
    private BorderPane container;
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
    }    

    @FXML
    private void abrirCadastroCliente(ActionEvent event) throws IOException {
        App.setRoot("telaCadastroCliente", container);
    }

    @FXML
    private void abrirColsultaCliente(ActionEvent event) throws IOException {
        App.setRoot("telaConsultaCliente", container);
    } 

    @FXML
    private void sair(ActionEvent event) {
        System.exit(0);
        
    }

    @FXML
    private void abrirTelaProdutos(ActionEvent event) throws IOException {
        App.setRoot("telaProdutos", container);
    }
    
}
