package br.senac.pi;

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
    @FXML
    private TableColumn<LinhaTabelaCliente, Integer> colunaIdCliente;
    @FXML
    private TextField txtCpf;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        colunaIdCliente.setCellValueFactory(new PropertyValueFactory("id"));
        colunaNomeCliente.setCellValueFactory(new PropertyValueFactory("nome"));
        colunaSobreNomeCliente.setCellValueFactory(new PropertyValueFactory("sobreNome"));
        colunaCpfCliente.setCellValueFactory(new PropertyValueFactory("cpf"));
        colunaRgCliente.setCellValueFactory(new PropertyValueFactory("rg"));
        colunaEmailCliente.setCellValueFactory(new PropertyValueFactory("email"));
        
        listar();
    }    

    @FXML
    private void consultarCliente(ActionEvent event) {
        if (!txtNome.getText().isEmpty()) {
            tabelaCliente.getItems().clear();
            
            String sql = "select * from cliente where nome_cliente = ? ";
            
            try (PreparedStatement ps = DB.connect().prepareStatement(sql)){
            ps.setString(1, txtNome.getText());
                
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                int id = rs.getInt("id_cliente");
                String nome = rs.getString("nome_cliente");
                String sobreNome = rs.getString("sobrenome_cliente");
                String cpf = rs.getString("cpf");
                String rg = rs.getString("rg");
                String email = rs.getString("email");
                
                LinhaTabelaCliente linha = new LinhaTabelaCliente(id, nome, sobreNome, cpf, rg, email);
                
                tabelaCliente.getItems().add(linha);
   
            }
            
                
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (!txtCpf.getText().isEmpty()) {
            String sql = "select * from cliente where cpf = ? ";
            
            try (PreparedStatement ps = DB.connect().prepareStatement(sql)){
            ps.setString(1, txtCpf.getText());
                
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                int id = rs.getInt("id_cliente");
                String nome = rs.getString("nome_cliente");
                String sobreNome = rs.getString("sobrenome_cliente");
                String cpf = rs.getString("cpf");
                String rg = rs.getString("rg");
                String email = rs.getString("email");
                
                LinhaTabelaCliente linha = new LinhaTabelaCliente(id, nome, sobreNome, cpf, rg, email);
                
                tabelaCliente.getItems().add(linha);
  
            }       
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (!txtEmail.getText().isEmpty()) {
            String sql = "select * from cliente where email = ? ";
            
            try (PreparedStatement ps = DB.connect().prepareStatement(sql)){
            ps.setString(1, txtEmail.getText());
                
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                int id = rs.getInt("id_cliente");
                String nome = rs.getString("nome_cliente");
                String sobreNome = rs.getString("sobrenome_cliente");
                String cpf = rs.getString("cpf");
                String rg = rs.getString("rg");
                String email = rs.getString("email");
                
                LinhaTabelaCliente linha = new LinhaTabelaCliente(id, nome, sobreNome, cpf, rg, email);
                
                tabelaCliente.getItems().add(linha);
   
            }
  
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        
    }

    @FXML
    private void excluirCliente(ActionEvent event) {
        LinhaTabelaCliente linha = tabelaCliente.getSelectionModel().getSelectedItem();
        
        if (linha != null) {
            int id = linha.getId();
            
            String sql = "delete from cliente where id_cliente = ?";
            
            try (PreparedStatement ps = DB.connect().prepareStatement(sql)){
                ps.setInt(1, id);
                
                ps.execute();
                
            } catch (Exception e) {
                e.printStackTrace();
            }
        } 
    }

    @FXML
    private void modificarCliente(ActionEvent event) throws IOException {
        LinhaTabelaCliente linha = tabelaCliente.getSelectionModel().getSelectedItem();
        if (linha != null) {
            int id = linha.getId();
            App.abrirTelaEdicaoCliente(id);
        }
    }

    private void listar(){
        tabelaCliente.getItems().clear();
        
        String sql = "select * from cliente";
        
        try (PreparedStatement ps = DB.connect().prepareStatement(sql)){
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                int id = rs.getInt("id_cliente");
                String nome = rs.getString("nome_cliente");
                String sobreNome = rs.getString("sobrenome_cliente");
                String cpf = rs.getString("cpf");
                String rg = rs.getString("rg");
                String email = rs.getString("email");
                
                LinhaTabelaCliente linha = new LinhaTabelaCliente(id, nome, sobreNome, cpf, rg, email);
                
                tabelaCliente.getItems().add(linha);
   
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }   
    }
}
