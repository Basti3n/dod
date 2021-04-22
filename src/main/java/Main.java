import javafx.application.Application;
import javafx.controllers.IndexController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;


public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/index.fxml"));
        Pane rootLayout = loader.load();
        Scene scene = new Scene(rootLayout, 800, 740);
        IndexController indexController = loader.getController();
        indexController.setStage(primaryStage);
        primaryStage.setTitle("DOD");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

}