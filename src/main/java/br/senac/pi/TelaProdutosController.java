package br.senac.pi;

import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
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
    private TextField txtCodProduto;
    @FXML
    private TableColumn<LinhaTabelaProdutos, String> colunaMarca;
    @FXML
    private TableColumn<LinhaTabelaProdutos, String> colunaTipo;
    @FXML
    private TableColumn<LinhaTabelaProdutos, Integer> colunaQuantidade;
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
    @FXML
    private TableColumn<LinhaTabelaProdutos,String> colunaCodigo;
    @FXML
    private Button buttonAddProduto;
    @FXML
    private TableColumn<LinhaTabelaProdutos, Integer> colunaId;
    
    private boolean estaEditando = false;
    private Integer idEdicao = null;
    @FXML
    private Button buttonCancelarEdicao;
  
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        colunaId.setCellValueFactory(new PropertyValueFactory("id"));
        colunaNome.setCellValueFactory(new PropertyValueFactory("nome"));
        colunaMarca.setCellValueFactory(new PropertyValueFactory("marca"));
        colunaTipo.setCellValueFactory(new PropertyValueFactory("tipo"));
        colunaQuantidade.setCellValueFactory(new PropertyValueFactory("quantidade"));
        colunaPreco.setCellValueFactory(new PropertyValueFactory("preco"));
        colunaCodigo.setCellValueFactory(new PropertyValueFactory("codigo"));
        
        listarProdutos();
        
    }    

    @FXML
    private void addProduto(ActionEvent event) {
     
        float precoProduto = Float.parseFloat(txtPrecoProduto.getText());
        
        int quantProduto = Integer.parseInt(txtQuantidadeProduto.getText());
        
        if (!estaEditando) {
            String sql = "insert into produto (cod_produto, nome_produto, marca, "
                    + "tipo, quantidade, preco)"
                    + "values(?, ?, ?, ?, ? , ?)";

            try (PreparedStatement ps = DB.connect().prepareStatement(sql)){

                ps.setString(1, txtCodProduto.getText());
                ps.setString(2, txtNomeProduto.getText());
                ps.setString(3, txtMarcaProduto.getText());
                ps.setString(4, txtTipoProduto.getText());
                ps.setInt(5, quantProduto);
                ps.setFloat(6, precoProduto);

                ps.execute();

                limpar();

                listarProdutos();

            }catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            String sql = "update produto set cod_produto = ?, nome_produto = ?, marca = ?, "
                    + "tipo = ?, quantidade = ?, preco = ? where id_produto = ?";
            
            try (PreparedStatement ps = DB.connect().prepareStatement(sql)){
                ps.setString(1, txtCodProduto.getText());
                ps.setString(2, txtNomeProduto.getText());
                ps.setString(3, txtMarcaProduto.getText());
                ps.setString(4, txtTipoProduto.getText());
                ps.setInt(5, quantProduto);
                ps.setDouble(6, precoProduto);
                ps.setInt(7, idEdicao);
                
                ps.execute();
                
                limpar(); 
                estaEditando = false;
                idEdicao = null;
                listarProdutos();
               
                
            } catch (Exception e) {
                e.printStackTrace();
            }
            
        }

        buttonAddProduto.setText("Adicionar");
    }

    @FXML
    private void excluirProduto(ActionEvent event) {
        if (App.perguntar("Exclusão", "Deseja mesmo excluir?", "A escolha não pode ser desfeita!")) {
            
            LinhaTabelaProdutos linha = tabelaProdutos.getSelectionModel().getSelectedItem();        

            if (linha != null) {
                int id = linha.getId();

                String sql = "delete from produto where id_produto = ?";

                try (PreparedStatement ps = DB.connect().prepareStatement(sql)){
                    ps.setInt(1, id);

                    ps.execute();

                    listarProdutos();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }else{
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("Nenhum produto selecionado");
                alert.showAndWait();
            }
        }
    }

    @FXML
    private void consultProduto(ActionEvent event) {
        if (!txtNomeProduto.getText().isEmpty()) {
            tabelaProdutos.getItems().clear();
            
            String sql = "select * from produto where nome_produto = ?";
            
            try (PreparedStatement ps = DB.connect().prepareStatement(sql)){
                
                ps.setString(1, txtNomeProduto.getText());
                ResultSet rs = ps.executeQuery();
                
                while (rs.next()) {                
                int id = rs.getInt("id_produto");
                String cod = rs.getString("cod_produto");
                String nome = rs.getString("nome_produto");
                String marca = rs.getString("marca");
                String tipo = rs.getString("tipo");
                int quant = rs.getInt("quantidade");
                float preco = rs.getFloat("preco");
                
                LinhaTabelaProdutos linha = new LinhaTabelaProdutos(id, nome, marca, tipo, quant, preco, cod);
                
                tabelaProdutos.getItems().add(linha);     
            }        
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (!txtCodProduto.getText().isEmpty()) {
            tabelaProdutos.getItems().clear();
            
            String sql = "select * from produto where cod_produto = ?";
            
            try (PreparedStatement ps = DB.connect().prepareStatement(sql)){
                
                ps.setString(1, txtCodProduto.getText());
                ResultSet rs = ps.executeQuery();
                
                while (rs.next()) {                
                int id = rs.getInt("id_produto");
                String cod = rs.getString("cod_produto");
                String nome = rs.getString("nome_produto");
                String marca = rs.getString("marca");
                String tipo = rs.getString("tipo");
                int quant = rs.getInt("quantidade");
                float preco = rs.getFloat("preco");
                
                LinhaTabelaProdutos linha = new LinhaTabelaProdutos(id, nome, marca, tipo, quant, preco, cod);
                
                tabelaProdutos.getItems().add(linha);     
            }        
            } catch (Exception e) {
                e.printStackTrace();
            }   
        }
        if (txtCodProduto.getText().isEmpty() && txtNomeProduto.getText().isEmpty()) {
            listarProdutos();
        }
    }

    @FXML
    private void editarProduto(ActionEvent event) {
        LinhaTabelaProdutos linha = tabelaProdutos.getSelectionModel().getSelectedItem();
        
        if (linha != null) {
            int id = linha.getId();
            idEdicao = id; 
            
            String quantidade = Integer.toString(tabelaProdutos.getSelectionModel().getSelectedItem().getQuantidade());

            String preco = Double.toString(tabelaProdutos.getSelectionModel().getSelectedItem().getPreco());

            txtNomeProduto.setText(tabelaProdutos.getSelectionModel().getSelectedItem().getNome());
            txtMarcaProduto.setText(tabelaProdutos.getSelectionModel().getSelectedItem().getMarca());
            txtPrecoProduto.setText(preco);
            txtQuantidadeProduto.setText(quantidade);
            txtTipoProduto.setText(tabelaProdutos.getSelectionModel().getSelectedItem().getTipo());
            txtCodProduto.setText(tabelaProdutos.getSelectionModel().getSelectedItem().getCodigo());

            estaEditando = true;

            buttonAddProduto.setText("Atualizar");
            
            buttonCancelarEdicao.setVisible(true);
        }
        
    }
    
    private void listarProdutos(){
        tabelaProdutos.getItems().clear();
        String sql = "select * from produto";
        
        try (PreparedStatement ps = DB.connect().prepareStatement(sql)){
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {                
                int id = rs.getInt("id_produto");
                String cod = rs.getString("cod_produto");
                String nome = rs.getString("nome_produto");
                String marca = rs.getString("marca");
                String tipo = rs.getString("tipo");
                int quant = rs.getInt("quantidade");
                float preco = rs.getFloat("preco");
                
                LinhaTabelaProdutos linha = new LinhaTabelaProdutos(id, nome, marca, tipo, quant, preco, cod);
                
                tabelaProdutos.getItems().add(linha);
                
            }
            
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    
    private void limpar (){
        txtMarcaProduto.clear();
        txtNomeProduto.clear();
        txtPrecoProduto.clear();
        txtQuantidadeProduto.clear();
        txtTipoProduto.clear();
        txtCodProduto.clear();
     
    }

    @FXML
    private void cancelarEdicao(ActionEvent event) {
        estaEditando = false;
        buttonCancelarEdicao.setVisible(false);
        limpar();
        buttonAddProduto.setText("Adicionar");
        listarProdutos();
    }
}