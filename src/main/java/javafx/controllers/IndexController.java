package javafx.controllers;

import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.layout.Pane;


import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
import javafx.stage.Stage;
import poo.*;

import java.util.ArrayList;
import java.util.List;

public class IndexController {
    public Stage stage;

    @FXML
    public Pane mainPane;

    @FXML
    public Group mainGroup;

    public void setStage(Stage primaryStage) {
        this.stage = primaryStage;
    }

    @FXML
    public void onCircleButtonClicked() {
        System.out.println("CLICKED CIRCLE BUTTON");
    }

    @FXML
    public void initialize() {
        runner();
    }


    public void runner() {
        CircleShape cVert = new CircleShape(150, 150, 100, ColorShape.VERT);
//        System.out.println(cVert);
        CircleShape cBleu = new CircleShape(270, 330, 100, ColorShape.BLEU);
//        System.out.println(cBleu);
        RectangleShape rVert = new RectangleShape(250, 200, 100, 200, ColorShape.VIOLET);
//        System.out.println(rVert);
        RectangleShape rGris = new RectangleShape(50, 50, 100, 200, ColorShape.GRIS);
//        System.out.println(rGris);

        ArrayList<Shape> shapes = new ArrayList<>();
        shapes.add(cVert);
        shapes.add(rVert);
        shapes.add(cBleu);
        shapes.add(rGris);

        // Add poo.Shape to background
        addShapeToGroup(shapes);
//        this.mainGroup.getChildren().add();

        Surface surface = Board.calcBoard(shapes);

        drawSurface(surface);
        drawColorShape(Board.getAllColorShapes(shapes));

    }

    public void drawSurface(Surface surface) {
        Rectangle rectangle = new Rectangle(surface.xmin, surface.ymin, surface.getWidth(), surface.getHeight());
        rectangle.setStrokeType(StrokeType.INSIDE);
        rectangle.setStroke(Color.CYAN);
        rectangle.setFill(Color.web("gray", 0.1));
        this.mainGroup.getChildren().add(rectangle);
    }

    public void drawColorShape(List<ColorShape> colorShapeList) {
        Group shapesToDraw = new Group();
        int counter = 0;
        for (ColorShape colorShape : colorShapeList) {
            Circle circle = new Circle((float) (counter * 800) / colorShapeList.size() + (float) 400 / colorShapeList.size(), 700, 25);
            circle.setStrokeType(StrokeType.INSIDE);
            circle.setStroke(Color.BLACK);
            circle.setFill(Color.web(colorShape.colorCode));
            shapesToDraw.getChildren().add(circle);

            counter += 1;
        }
        this.mainGroup.getChildren().add(shapesToDraw);
    }

    public void addShapeToGroup(ArrayList<Shape> shapeArrayList) {
        for (Shape shape : shapeArrayList) {
            if (shape instanceof CircleShape) {
                Circle circle = new Circle(shape.x, shape.y, ((CircleShape) shape).radius);
                circle.setStrokeType(StrokeType.INSIDE);
                circle.setStroke(Color.BLACK);
                circle.setFill(Color.web(shape.colorShape.colorCode));
                this.mainGroup.getChildren().add(circle);

            } else if (shape instanceof RectangleShape) {
                Rectangle rectangle = new Rectangle(shape.x, shape.y, ((RectangleShape) shape).width, ((RectangleShape) shape).height);
                rectangle.setStrokeType(StrokeType.INSIDE);
                rectangle.setStroke(Color.BLACK);
                rectangle.setFill(Color.web(shape.colorShape.colorCode));
                this.mainGroup.getChildren().add(rectangle);

            } else {
                Rectangle rectangle = new Rectangle(shape.x, shape.y, ((RectangleShape) shape).width, ((RectangleShape) shape).height);
                rectangle.setStrokeType(StrokeType.INSIDE);
                rectangle.setStroke(Color.CYAN);
                rectangle.setFill(Color.web("gray", 0.1));
                this.mainGroup.getChildren().add(rectangle);

            }
        }
    }
}
