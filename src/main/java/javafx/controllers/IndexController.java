package javafx.controllers;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;



import javafx.stage.Stage;

public class IndexController{
    public Stage stage;

    @FXML
    public Pane mainPane;

    public void setStage(Stage primaryStage){
        this.stage = primaryStage;
    }

    @FXML
    public void onCircleButtonClicked() {
        System.out.println("CLICKED CIRCLE BUTTON");
    }
}
