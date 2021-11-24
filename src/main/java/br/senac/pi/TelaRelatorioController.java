package br.senac.pi;

import java.net.URL;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import static java.time.temporal.ChronoUnit.DAYS;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class TelaRelatorioController implements Initializable {

    @FXML
    private TableView<LinhaTabelaRelatorio> tabelaRelatorio;
    @FXML
    private TableColumn<LinhaTabelaRelatorio, String> colunaCliente;
    @FXML
    private TableColumn<LinhaTabelaRelatorio, String> colunaProduto;
    @FXML
    private TableColumn<LinhaTabelaRelatorio, Integer> colunaQuantidade;
    @FXML
    private TableColumn<LinhaTabelaRelatorio, Float> colunaValor;
    @FXML
    private TableColumn<LinhaTabelaRelatorio, LocalDate> colunaData;
    @FXML
    private DatePicker dataInicio;
    @FXML
    private DatePicker dataFinal;
    @FXML
    private TextField txtTotal;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        colunaCliente.setCellValueFactory(new PropertyValueFactory("cliente"));
        colunaProduto.setCellValueFactory(new PropertyValueFactory("produto"));
        colunaQuantidade.setCellValueFactory(new PropertyValueFactory("quantidade"));
        colunaValor.setCellValueFactory(new PropertyValueFactory("valor"));
        colunaData.setCellValueFactory(new PropertyValueFactory("data"));
          
    }    

    @FXML
    private void validar(ActionEvent event) {
        long dias = DAYS.between(dataInicio.getValue(), dataFinal.getValue().plusDays(1));
        
        if (dias <= 0 || dias > 31) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("A data inicial deve ser menor que a data final e o preiodo não pode "
                    + "exceder os 30 dias ");
            alert.showAndWait();
            
            return;
        } 
        
        tabelaRelatorio.getItems().clear();
        
        String sql = "select c.nome_cliente , c.sobrenome_cliente, p.nome_produto, vp.quantidade, "
                        + "vp.preco, v.data_venda, vp.id_venda\n" +
                        "from venda_produto as vp inner join venda as v\n" +
                        "on v.id_venda = vp.id_venda\n" +
                        "inner join cliente c\n" +
                        "on v.id_cliente = c.id_cliente\n" +
                        "inner join produto p \n" +
                        "on vp.id_produto = p.id_produto\n" +
                        "where v.data_venda > ? and v.data_venda < ?\n";
        
        try (PreparedStatement ps = DB.connect().prepareStatement(sql)){
            ps.setDate(1, Date.valueOf(dataInicio.getValue()));
            ps.setDate(2, Date.valueOf(dataFinal.getValue()));
            
            ResultSet rs = ps.executeQuery();
            float valorFinal = 0;
            
            int idVenda = 0;
            
            while (rs.next()) {
                String nome = "";
                if (idVenda != rs.getInt("id_venda")) {
                    nome = rs.getString("nome_cliente");
                    idVenda = rs.getInt("id_venda");
                }
                LinhaTabelaRelatorio ltr = new LinhaTabelaRelatorio(rs.getString("nome_cliente") + " " + rs.getString("sobrenome_cliente"), 
                       rs.getString("nome_produto"), rs.getInt("quantidade"), rs.getFloat("preco"), 
                       rs.getDate("data_venda").toLocalDate());
                
                tabelaRelatorio.getItems().add(ltr);
                
                valorFinal += rs.getFloat("preco");
               
            }          
            txtTotal.setText("R$" + Float.toString(valorFinal));
            tabelaRelatorio.refresh();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }

    @FXML
    private void limpar(ActionEvent event) {
        
        if (!tabelaRelatorio.getItems().isEmpty()) {
            tabelaRelatorio.getItems().clear();
            dataFinal.setValue(null);
            dataInicio.setValue(null);
        
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Ainda não existe um relátorio gerado");
            alert.showAndWait();
        }
    }
}
