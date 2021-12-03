package br.senac.pi;

import java.net.URL;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class TelaVendaController implements Initializable {

    @FXML
    private TableView<LinhaTabelaVenda> tabelaVenda;
    @FXML
    private TableColumn<LinhaTabelaVenda, Integer> colunaId;
    @FXML
    private TableColumn<LinhaTabelaVenda, String> colunaProduto;
    @FXML
    private TableColumn<LinhaTabelaVenda, Integer> colunaQtd;
    @FXML
    private TableColumn<LinhaTabelaVenda, Float> colunaPreco;
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
    @FXML
    private Label labelCliente;
    
    private Integer idCliente = null;
    
    private float precoTotal = 0;
    @FXML
    private DatePicker pickerDataVenda;
    private Integer parcela = null;
    

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
        comboParcelas.getItems().add("5X");
        comboParcelas.getItems().add("6X");
        comboParcelas.getItems().add("7X");
        comboParcelas.getItems().add("8X");
        comboParcelas.getItems().add("9X");
        comboParcelas.getItems().add("10X");
        comboParcelas.getItems().add("11X");
        comboParcelas.getItems().add("12X");
       
        colunaId.setCellValueFactory(new PropertyValueFactory("id"));
        colunaProduto.setCellValueFactory(new PropertyValueFactory("produto"));
        colunaQtd.setCellValueFactory(new PropertyValueFactory("quant"));
        colunaPreco.setCellValueFactory(new PropertyValueFactory("preco"));
        
        pickerDataVenda.setValue(LocalDate.now());
  
    }    

    @FXML
    private void excluir(ActionEvent event) {
        if (App.perguntar("Exclusão", "Deseja mesmo excluir?", "A escolha não pode ser desfeita!")) {
            
        if (tabelaVenda.getSelectionModel().getSelectedItem() == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Nenhum produto selecionado");
            alert.showAndWait();
     
        } else {
            LinhaTabelaVenda ltv = tabelaVenda.getSelectionModel().getSelectedItem();
            
            float precoProduto = ltv.getPreco();
            
            precoTotal -= precoProduto;
            
            tabelaVenda.getItems().remove(tabelaVenda.getSelectionModel().getSelectedIndex());
            
            if (precoTotal == 0) {
                txtPrecoTotal.clear();
            } else {
                txtPrecoTotal.setText(Float.toString(precoTotal));
            }   
        }
        }
    }

    @FXML
    private void adicionar(ActionEvent event) {
        if (txtCodProduto.getText().isEmpty() && txtQnt.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Faltam campos obrigatórios\nFavor verificar ");
            alert.showAndWait();
            
        } else {
            float precoProduto;
            int qntProduto = 0;
            
            String sql = "select top 1 * from produto where cod_produto = ?";
        
        try (PreparedStatement ps = DB.connect().prepareStatement(sql)){
            
            ps.setInt(1, Integer.parseInt(txtCodProduto.getText()));
            
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                qntProduto = Integer.parseInt(txtQnt.getText());
                precoProduto = rs.getFloat("preco") * qntProduto;
                int estoque = rs.getInt("quantidade");
                
                if (qntProduto > estoque) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Não há produtos suficentes para efetuar esse compra\nO estoque desse produto é de " 
                            +  rs.getInt("quantidade")
                    + "\nE o cliente deseja " + qntProduto);
                    alert.showAndWait();              
                }else{              
                        LinhaTabelaVenda ltv = new LinhaTabelaVenda(rs.getInt("id_produto"), 
                        rs.getString("nome_produto"), 
                        Integer.parseInt(txtQnt.getText()), 
                        precoProduto);

                        tabelaVenda.getItems().add(ltv);

                        precoTotal += precoProduto;

                        txtPrecoTotal.setText(Float.toString(precoTotal));

                        limpar();                   
                }
            }    
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
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
        String sqlVenda = "insert into venda"
                + "(id_cliente, forma_pagamento, "
                + "parcelas, data_venda) values(?, ?, ?, ?)";
        
        try (PreparedStatement ps = DB.connect().prepareStatement(sqlVenda, PreparedStatement.RETURN_GENERATED_KEYS)){
            
            parcela = comboParcelas.getSelectionModel().getSelectedIndex();
            
            if (parcela == null || parcela <= -1) {
                parcela = 1;
            }else{
                parcela += 1;
            }
            ps.setInt(1, idCliente);        
            ps.setString(2, (String) comboFormaPagamento.getSelectionModel().getSelectedItem());
            ps.setInt(3, parcela);
            ps.setDate(4, Date.valueOf(pickerDataVenda.getValue()));
            
            ps.execute();
            
            ResultSet rsKeys = ps.getGeneratedKeys();
            
            if (rsKeys.next()) {
                int idVenda = rsKeys.getInt(1);
                registarVenda(idVenda);
                decrementarEstoque();
                cancelar(event);
                
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("Venda realizada!");
                alert.showAndWait();
                
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void registarVenda(int idVenda){
        for (int i = 0; i < tabelaVenda.getItems().size(); i++) {
            String sql = "insert into venda_produto(id_venda, id_produto, quantidade, preco) "
                    + "values (?, ?, ?, ?)";
            
            try (PreparedStatement ps = DB.connect().prepareStatement(sql)){
                ps.setInt(1, idVenda);
                ps.setInt(2, tabelaVenda.getItems().get(i).getId());
                ps.setInt(3, tabelaVenda.getItems().get(i).getQuant());
                ps.setFloat(4, tabelaVenda.getItems().get(i).getPreco());
                
                ps.execute();
                
                
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
    private void decrementarEstoque(){
        for (int i = 0; i < tabelaVenda.getItems().size(); i++) {
            String sql = "update produto set quantidade = quantidade - ? where id_produto = ?";
            
            try (PreparedStatement ps = DB.connect().prepareStatement(sql)){
                ps.setInt(1, tabelaVenda.getItems().get(i).getQuant());
                ps.setInt(2, tabelaVenda.getItems().get(i).getId());
                
                ps.execute();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    private void validarCliente(ActionEvent event) {
        String sql = "select top 1 * from cliente where cpf = ?";
        
        try (PreparedStatement ps = DB.connect().prepareStatement(sql)){
            ps.setString(1, txtCpfCliente.getText());
            
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                String nome = rs.getString("nome_cliente");
                labelCliente.setText("Cliente: " + nome);
                
                idCliente = rs.getInt("id_cliente");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void cancelar(ActionEvent event) {
        txtCodProduto.clear();
        txtQnt.clear();
        txtCpfCliente.clear();
        txtPrecoTotal.clear();
        precoTotal = 0;       
        labelCliente.setText("Cliente:");      
        idCliente = null;       
        tabelaVenda.getItems().clear();     
        comboFormaPagamento.setValue(null);
        comboParcelas.setValue(null);
   
    }
    
    private void limpar(){
        txtCodProduto.clear();
        txtQnt.clear();
    }
   
}
