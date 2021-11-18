package br.senac.pi;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import javafx.scene.layout.BorderPane;

public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("telaPrincipal"), 1160, 840);
        stage.setScene(scene);
        scene.getStylesheets().add(getClass().getResource("style.css").toString());
      
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

    public static void main(String[] args) {
        launch();
    }
}