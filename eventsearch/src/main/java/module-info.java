module uni {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.net.http;
    requires org.jsoup;
    

    opens uni to javafx.fxml;
    exports uni;
}
