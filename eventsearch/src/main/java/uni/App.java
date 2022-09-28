package uni;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import javafx.scene.image.Image;

/**
 * JavaFX App
 */
public class App extends Application {

    public static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        MainController c = new MainController();
        
        
        scene = new Scene(loadFXML("MainView"), 1200, 700);
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.getIcons().add(new Image(App.class.getResourceAsStream("logo.png")));
        stage.setTitle("Event Search");
        stage.show();
        
    }

    public static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();

    }

}
