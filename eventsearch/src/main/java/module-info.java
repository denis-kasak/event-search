module uni {
    requires javafx.controls;
    requires javafx.fxml;

    opens uni to javafx.fxml;
    exports uni;
}
