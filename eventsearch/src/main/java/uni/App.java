package uni;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

/**
 * JavaFX App
 */
public class App extends Application {

    @FXML
    AnchorPane root;

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("mainview"));
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        MainController controller = new MainController();
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

}
