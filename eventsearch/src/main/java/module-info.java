module uni {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.net.http;

    opens uni to javafx.fxml;
    exports uni;
}
