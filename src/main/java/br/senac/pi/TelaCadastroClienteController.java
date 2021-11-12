package br.senac.pi;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class TelaCadastroClienteController implements Initializable {

    @FXML
    private TextField txtNome; 
    @FXML
    private TextField txtSobrenome; 
    @FXML
    private TextField txtRg; 
    @FXML
    private TextField txtCpf;
    @FXML
    private ComboBox comboCivil;
    @FXML
    private ComboBox comboGenero;
    @FXML
    private TextField txtOutroEC;
    @FXML
    private DatePicker pickerDataNascimento;
    @FXML
    private TextField txtOutroGenero;
    @FXML
    private TextField txtCep;
    @FXML
    private TextField txtEndereco;
    @FXML
    public TextField txtEnderecoNum;
    @FXML
    private TextField txtPontoReferencia;
    @FXML
    private TextField txtComplemento;
    @FXML
    private TextField txtBairro;
    @FXML
    private ComboBox comboEstado;
    @FXML
    private TextField txtCidade;
    @FXML
    private TextField txtTelefoneFixo;
    @FXML
    private TextField txtEmail;
    @FXML
    private TextField txtCelular;
  
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Adiciona os generos
        comboGenero.getItems().add("Masculino");
        comboGenero.getItems().add("Feminino");
        comboGenero.getItems().add("Não-binario");
        comboGenero.getItems().add("Prefiro não informar");
        comboGenero.getItems().add("Outro");
        //Adiciona os Estados Civis
        comboCivil.getItems().add("Solteiro");
        comboCivil.getItems().add("Casado");
        comboCivil.getItems().add("Viúvo");
        comboCivil.getItems().add("Separado");
        comboCivil.getItems().add("Divorciado");
        comboCivil.getItems().add("União Estável");
        comboCivil.getItems().add("Outro");
        // Adiciona os estados
        comboEstado.getItems().add("AC");
        comboEstado.getItems().add("AL");
        comboEstado.getItems().add("AP");
        comboEstado.getItems().add("AM");
        comboEstado.getItems().add("BA");
        comboEstado.getItems().add("CE");   
        comboEstado.getItems().add("DF");
        comboEstado.getItems().add("ES");
        comboEstado.getItems().add("GO");
        comboEstado.getItems().add("MA");
        comboEstado.getItems().add("MT");
        comboEstado.getItems().add("MS");
        comboEstado.getItems().add("MG");
        comboEstado.getItems().add("PA");
        comboEstado.getItems().add("PB");
        comboEstado.getItems().add("PR");
        comboEstado.getItems().add("PE");
        comboEstado.getItems().add("PI");
        comboEstado.getItems().add("RJ");
        comboEstado.getItems().add("RN");
        comboEstado.getItems().add("ES");
        comboEstado.getItems().add("RO");
        comboEstado.getItems().add("RR");
        comboEstado.getItems().add("SC");
        comboEstado.getItems().add("SP");
        comboEstado.getItems().add("TO");    
    }    

    @FXML
    private void civilAction(ActionEvent event) {
        int outroEstado = comboCivil.getSelectionModel().getSelectedIndex();
        
        if (outroEstado == 6) {
            txtOutroEC.setDisable(false);
        } else {
            txtOutroEC.setDisable(true);
            txtOutroEC.clear();
        }
    }

    @FXML
    private void generoAction(ActionEvent event) {
         int outroGenero = comboGenero.getSelectionModel().getSelectedIndex();
        
        if(outroGenero == 4){
            txtOutroGenero.setDisable(false);
            
        }else {
            txtOutroGenero.setDisable(true);
            txtOutroGenero.clear();
        }
    }
    
    @FXML
    private void finalizarCadastro(ActionEvent event) {
        
        
    } 

    @FXML
    private void validarCep(ActionEvent event) {
        WebServiceCep webServiceCep = WebServiceCep.searchCep(txtCep.getText());
        
        if (webServiceCep.wasSuccessful()) {
            txtEndereco.setText(webServiceCep.getLogradouroFull());
            txtCidade.setText(webServiceCep.getCidade());
            txtBairro.setText(webServiceCep.getBairro());
            comboEstado.getSelectionModel().select(webServiceCep.getUf());
             
       } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            
            alert.setTitle("Erro no CEP");
            alert.setHeaderText("Erro na validação do CEP");
            alert.setContentText("Por favor, verificar se o CEP foi digitado corretamente, \nCaso o erro persista,"
                    + " favor digitar o endereço manualmente!");
            alert.showAndWait();
        }
    }
}
