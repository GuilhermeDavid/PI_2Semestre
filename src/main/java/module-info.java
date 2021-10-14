module br.senac.pi {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;

    opens br.senac.pi to javafx.fxml;
    exports br.senac.pi;
    requires dom4j;
}
