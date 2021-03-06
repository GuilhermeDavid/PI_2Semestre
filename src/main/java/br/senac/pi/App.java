package br.senac.pi;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.BorderPane;

public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        int w = 1100;
        int h = 900;
        
        scene = new Scene(loadFXML("telaPrincipal"), w, h);
        stage.setScene(scene);
        scene.getStylesheets().add(getClass().getResource("style.css").toString());
        stage.setMinWidth(w);
        stage.setMinHeight(h + 20);
        stage.show();
    }

    static void setRoot(String fxml, BorderPane container) throws IOException {
        container.setCenter(loadFXML(fxml));
    }
    
    static void abrirTelaEdicaoCliente(int id) throws IOException{
        TelaCadastroClienteController.idEdição = id;
        BorderPane container = (BorderPane) scene.lookup("#container");
        container.setCenter(loadFXML("telaCadastroCliente"));
    }
    static void abrirTelaListagemCliente() throws IOException{
        BorderPane container = (BorderPane) scene.lookup("#container");
        container.setCenter(loadFXML("telaConsultaCliente"));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }
    
    public static boolean perguntar(String titulo, String cabecalho, String msg){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        
        alert.setTitle(titulo);
        alert.setHeaderText(cabecalho);
        alert.setContentText(msg);
    
        ButtonType buttonSim = new ButtonType("Sim");
        ButtonType buttonNao = new ButtonType("Não");
        
        alert.getButtonTypes().setAll(buttonSim,buttonNao);
        
        Optional<ButtonType> result = alert.showAndWait();
        
        if (result.get() == buttonSim) {
            return true;
        } else {
            return false;
        }
    }
    
    public static void main(String[] args) {
        launch();
    }
}