import javafx.application.Application;
import javafx.controllers.IndexController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
import javafx.stage.Stage;

import models.CircleShape;
import models.ColorShape;
import models.RectangleShape;
import models.Shape;
import poo.*;
import simd.GenerationFromFile;
import simd.Simd;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("index.fxml"));
        Parent parentRoot = loader.load();
        IndexController indexController = loader.getController();
        indexController.setStage(primaryStage);

        Scene scene = new Scene(parentRoot, 800, 800, Color.web("#252525"));
        primaryStage.setScene(scene);

        runner((Group) parentRoot);

        primaryStage.show();
    }

    public void runner( Group root) throws Exception {

        List<Shape> shapes = GenerationFromFile.output("src\\main\\resources\\forme.txt");

        Simd.run(shapes);

        // Add models.Shape to background
        root.getChildren().add(drawShapes(shapes));

//            System.out.println(surface);


//            System.out.println(poo.Board.getAllColorShapes(shapes));

//        root.getChildren().add(drawColorShape(Board.getAllColorShapes(shapes)));


    }



    public static Group drawShapes(List<Shape> shapes){
        Group shapesToDraw = new Group();
        for (Shape shape:shapes) {

            if(shape instanceof CircleShape){

                Circle circle = new Circle(shape.x, shape.y,((CircleShape) shape).radius);
                circle.setStrokeType(StrokeType.INSIDE);
                circle.setStroke(Color.BLACK);
                circle.setFill(Color.web(shape.colorShape.colorCode));
                shapesToDraw.getChildren().add(circle);

            }else if (shape instanceof RectangleShape){
                Rectangle rectangle =  new Rectangle(shape.x, shape.y, ((RectangleShape) shape).width, ((RectangleShape) shape).height);
                rectangle.setStrokeType(StrokeType.INSIDE);
                rectangle.setStroke(Color.BLACK);
                rectangle.setFill(Color.web(shape.colorShape.colorCode));

                shapesToDraw.getChildren().add(rectangle);

            }
        }
        return shapesToDraw;
    }

    public Rectangle DrawSurface(Surface surface){
        Rectangle rectangle =  new Rectangle(surface.xmin, surface.ymin, surface.getWidth(), surface.getHeight());
        rectangle.setStrokeType(StrokeType.INSIDE);
        rectangle.setStroke(Color.CYAN);
        rectangle.setFill(Color.web("gray", 0.1));
        return rectangle;
    }

    public Group drawColorShape(List<ColorShape> colorShapeList) {
        Group shapesToDraw = new Group();
        int counter = 0;
        for (ColorShape colorShape:colorShapeList) {
            Circle circle = new Circle( (counter * 800) / colorShapeList.size() + 25 * colorShapeList.size() , 700 ,25);
            circle.setStrokeType(StrokeType.INSIDE);
            circle.setStroke(Color.BLACK);
            circle.setFill(Color.web(colorShape.colorCode));
            shapesToDraw.getChildren().add(circle);

            counter+= 1;
        }
        return shapesToDraw;
    }


}