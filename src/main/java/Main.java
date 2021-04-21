package main.java;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


public class Main extends Application {
        public static void main(String[] args) {
            launch(args);
        }

        @Override
        public void start(Stage primaryStage) {
            Group root = new Group();
            Scene scene = new Scene(root, 800, 600, Color.BLACK);
            primaryStage.setScene(scene);

            runned();
            primaryStage.show();
        }

        public void runned(){
            Cercle cRouge = new Cercle(1, 2, 1,Couleur.ROUGE);
            Rectangle rVert = new Rectangle(1, 2, 1, 2,Couleur.VERT);
            System.out.println(cRouge);
            System.out.println(rVert);
        }


}
