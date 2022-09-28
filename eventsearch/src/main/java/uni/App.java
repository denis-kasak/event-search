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

        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("MainView.fxml"));
        scene = new Scene(fxmlLoader.load(), 1200, 700);
        MainController c = fxmlLoader.getController();
        c.setHostServices(getHostServices());
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.getIcons().add(new Image(App.class.getResourceAsStream("logo.png")));
        stage.setTitle("Event Search");
        stage.show();

    }

    public static void main(String[] args) {
        launch();

    }

}
