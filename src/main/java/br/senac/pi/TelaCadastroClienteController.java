package br.senac.pi;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

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
    
    public static Integer idEdição = null;
    public static boolean estaEditando = false;
    @FXML
    private Button buttonCancelarEdicao;
    
  
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
        
        if (idEdição != null) {
            buttonCancelarEdicao.setVisible(true);
            String sql = "select * from cliente where id_cliente = ?";
            
            try (PreparedStatement ps = DB.connect().prepareStatement(sql)){
                ps.setInt(1, idEdição);
                
                ResultSet rs = ps.executeQuery();
                
                if (rs.next()) {
                    txtNome.setText(rs.getString("nome_cliente"));
                    txtSobrenome.setText(rs.getString("sobrenome_cliente"));
                    txtCpf.setText(rs.getString("cpf"));
                    txtRg.setText(rs.getString("rg"));
                    pickerDataNascimento.setValue(rs.getDate("data_nascimento").toLocalDate());
                    
                    String outroCivil = rs.getString("estado_civil");
                    
                    if (!outroCivil.equalsIgnoreCase("solteiro") && !outroCivil.equalsIgnoreCase("casado") && 
                            !outroCivil.equalsIgnoreCase("viúvo") && !outroCivil.equalsIgnoreCase("separado") && 
                            !outroCivil.equalsIgnoreCase("divorciado") && !outroCivil.equalsIgnoreCase("união estável")) {
                        comboCivil.getSelectionModel().select(6);
                        txtOutroEC.setText(rs.getString("estado_civil"));
                        txtOutroEC.setDisable(false);
                        
                    }else{
                        comboCivil.setValue(rs.getString("estado_civil"));
                    }
                    
                    String outroGenero = rs.getString("genero");
                    
                    if (!outroGenero.equalsIgnoreCase("masculino") && !outroGenero.equalsIgnoreCase("feminino") && 
                            !outroGenero.equalsIgnoreCase("não-binario") && !outroGenero.equalsIgnoreCase("prefiro não informar") ) {
                        comboGenero.getSelectionModel().select(4);
                        txtOutroGenero.setText(rs.getString("genero"));
                        txtOutroGenero.setDisable(false);
                    } else {
                        comboGenero.setValue(rs.getString("genero"));
                    }
                    
                    comboEstado.setValue(rs.getString("uf"));
                    txtCep.setText(rs.getString("cep"));
                    txtCidade.setText(rs.getString("cidade"));
                    txtEndereco.setText(rs.getString("endereco"));
                    txtEnderecoNum.setText(rs.getString("numero_residencia"));
                    txtBairro.setText(rs.getString("bairro"));
                    txtComplemento.setText(rs.getString("complemento"));
                    txtPontoReferencia.setText(rs.getString("ponto_referencia"));
                    txtTelefoneFixo.setText(rs.getString("telefone_fixo"));
                    txtCelular.setText(rs.getString("celular"));
                    txtEmail.setText(rs.getString("email"));
                    
                    estaEditando = true;
                    
                }
                
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
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
    private void finalizarCadastro(ActionEvent event) throws IOException {
        if (!estaEditando) {
            if (txtNome.getText().isBlank()|| txtSobrenome.getText().isEmpty() ||
                    txtCpf.getText().isEmpty() || txtRg.getText().isEmpty() ||
                    pickerDataNascimento.getValue() == null ||
                    comboGenero.getValue() == null 
                    ||comboCivil.getValue() == null 
                    || txtCep.getText().isEmpty() || comboEstado.getValue() == null
                    || txtCidade.getText().isEmpty() || txtEndereco.getText().isEmpty()
                    || txtEnderecoNum.getText().isEmpty() || txtBairro.getText().isEmpty()
                    || txtEmail.getText().isEmpty()) {
                
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erro na validação do cadastro");
                alert.setHeaderText("Campos obrigatórios");
                alert.setContentText("Falta preencher campos obrigatórios \nCampos obrigatorios"
                    + " tem um * acima do campo");
                
              alert.showAndWait();
            }else{
         
                String sql = "insert into cliente(nome_cliente,sobrenome_cliente,cpf,rg,"
                    + "data_nascimento,estado_civil,genero,cep,uf,cidade,endereco,numero_residencia,"
                    + "bairro,complemento,ponto_referencia,telefone_fixo,celular,email) "
                    + "values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? , ? , ? , ? , ? , ? , ?)";

                try (PreparedStatement ps = DB.connect().prepareStatement(sql)){
                    ps.setString(1,txtNome.getText());
                    ps.setString(2,txtSobrenome.getText());
                    ps.setString(3,txtCpf.getText());
                    ps.setString(4,txtRg.getText());
                    ps.setDate(5, Date.valueOf(pickerDataNascimento.getValue()));

                    int outroEstado = comboCivil.getSelectionModel().getSelectedIndex();

                    if (outroEstado == 6) {
                        ps.setString(6,txtOutroEC.getText());
                    }else{
                        ps.setString(6, (String) comboCivil.getSelectionModel().getSelectedItem());
                    }

                    int outroGenero = comboGenero.getSelectionModel().getSelectedIndex();

                    if(outroGenero == 4){
                        ps.setString(7,txtOutroGenero.getText());

                    }else {
                        ps.setString(7, (String) comboGenero.getSelectionModel().getSelectedItem());
                    }

                    ps.setString(8,txtCep.getText());
                    ps.setString(9, (String) comboEstado.getSelectionModel().getSelectedItem());
                    ps.setString(10,txtCidade.getText());
                    ps.setString(11,txtEndereco.getText());

                    Integer numeroResidencia = Integer.parseInt(txtEnderecoNum.getText());

                    ps.setInt(12,numeroResidencia);
                    ps.setString(13,txtBairro.getText());
                    ps.setString(14,txtComplemento.getText());
                    ps.setString(15,txtPontoReferencia.getText());
                    ps.setString(16,txtTelefoneFixo.getText());
                    ps.setString(17,txtCelular.getText());
                    ps.setString(18,txtEmail.getText());

                    ps.execute();

                    limparTela();

                } catch (Exception e) {
                    e.printStackTrace();
                }

                }
                } else {
            if (txtNome.getText().isBlank()|| txtSobrenome.getText().isEmpty() ||
                    txtCpf.getText().isEmpty() || txtRg.getText().isEmpty() ||
                    pickerDataNascimento.getValue() == null ||
                    comboGenero.getValue() == null 
                    ||comboCivil.getValue() == null 
                    || txtCep.getText().isEmpty() || comboEstado.getValue() == null
                    || txtCidade.getText().isEmpty() || txtEndereco.getText().isEmpty()
                    || txtEnderecoNum.getText().isEmpty() || txtBairro.getText().isEmpty()
                    || txtEmail.getText().isEmpty()) {
                
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erro na validação do cadastro");
                alert.setHeaderText("Campos obrigatórios");
                alert.setContentText("Falta preencher campos obrigatórios \nCampos obrigatorios"
                    + " tem um * acima do campo");
                
              alert.showAndWait();
            }else{

                String sql = "update cliente set nome_cliente = ?, sobrenome_cliente = ?,"
                        + "cpf = ?, rg = ?, data_nascimento = ?, estado_civil = ?, genero = ?, "
                        + "cep = ?, uf = ?, cidade = ?, endereco = ?, numero_residencia = ?, "
                        + "bairro = ?, complemento = ?, ponto_referencia = ?, telefone_fixo = ?, "
                        + "celular = ?, email = ? where id_cliente = ?";

                try (PreparedStatement ps = DB.connect().prepareStatement(sql)){
                    ps.setString(1, txtNome.getText());
                    ps.setString(2,txtSobrenome.getText());
                    ps.setString(3,txtCpf.getText());
                    ps.setString(4,txtRg.getText());
                    ps.setDate(5, Date.valueOf(pickerDataNascimento.getValue()));

                    int outroEstado = comboCivil.getSelectionModel().getSelectedIndex();

                    if (outroEstado == 6) {
                        ps.setString(6,txtOutroEC.getText());
                    }else{
                        ps.setString(6, (String) comboCivil.getSelectionModel().getSelectedItem());
                    }

                    int outroGenero = comboGenero.getSelectionModel().getSelectedIndex();

                    if(outroGenero == 4){
                        ps.setString(7,txtOutroGenero.getText());

                    }else {
                        ps.setString(7, (String) comboGenero.getSelectionModel().getSelectedItem());
                    }

                    ps.setString(8,txtCep.getText());
                    ps.setString(9, (String) comboEstado.getSelectionModel().getSelectedItem());
                    ps.setString(10,txtCidade.getText());
                    ps.setString(11,txtEndereco.getText());

                    Integer numeroResidencia = Integer.parseInt(txtEnderecoNum.getText());

                    ps.setInt(12,numeroResidencia);
                    ps.setString(13,txtBairro.getText());
                    ps.setString(14,txtComplemento.getText());
                    ps.setString(15,txtPontoReferencia.getText());
                    ps.setString(16,txtTelefoneFixo.getText());
                    ps.setString(17,txtCelular.getText());
                    ps.setString(18,txtEmail.getText());
                    ps.setInt(19, idEdição);

                    ps.execute();

                    limparTela();
                    estaEditando = false;
                    idEdição = null;


                } catch (Exception e) {
                    e.printStackTrace();
                }
                App.abrirTelaListagemCliente();
                buttonCancelarEdicao.setVisible(false);
            }
        }
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
    
    private void limparTela(){
        txtNome.clear();
        txtSobrenome.clear();
        txtCpf.clear();
        txtRg.clear();
        pickerDataNascimento.setValue(null);
        txtOutroEC.clear();
        txtOutroGenero.clear();
        txtOutroEC.clear();
        comboGenero.getSelectionModel().clearSelection();
        comboCivil.getSelectionModel().clearSelection();
        comboEstado.getSelectionModel().clearSelection();
        txtCep.clear();
        txtCidade.clear();
        txtEndereco.clear();
        txtEnderecoNum.clear();
        txtComplemento.clear();
        txtPontoReferencia.clear();
        txtTelefoneFixo.clear();
        txtEmail.clear();
        txtCelular.clear();
        txtBairro.clear();
    }

    @FXML
    private void limpar(ActionEvent event) {
        limparTela();

    }

    @FXML
    private void cancelar(ActionEvent event) throws IOException {
        
        limparTela();
        idEdição = null;
        estaEditando = false;
        buttonCancelarEdicao.setVisible(false);
        App.abrirTelaListagemCliente();
        
    }
}