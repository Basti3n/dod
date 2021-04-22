package javafx.controllers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
import javafx.stage.Stage;
import models.*;
import simd.GenerationFromFile;
import simd.Simd;
import simd.actions.Consommation;

import java.util.List;

public class IndexController {
    public Stage stage;

    @FXML
    public Pane mainPane;

    @FXML
    public Group mainGroup;

    public String shapeToDraw = "Circle";

    private float beginX = 0;
    private float beginY = 0;
    private float endX = 0;
    private float endY = 0;

    private ColorShape colorToDraw = ColorShape.GRIS;

    private float groupMaxX = 800;
    private float groupMaxY = 640;
    private float groupMinX = 0;
    private float groupMinY = 40;

    public void setStage(Stage primaryStage) {
        this.stage = primaryStage;
    }

    @FXML
    public void onCircleButtonClicked() {
        System.out.println("CLICKED CIRCLE BUTTON");
        this.shapeToDraw = "Circle";
    }

    @FXML
    public void onRectangleButtonClicked() {
        System.out.println("CLICKED RECTANGLE BUTTON");
        this.shapeToDraw = "Rectangle";
    }

    @FXML
    public void onChangeColorOne() {
        this.colorToDraw = ColorShape.ROUGE;
    }

    @FXML
    public void onChangeColorTwo() {
        this.colorToDraw = ColorShape.VERT;
    }

    @FXML
    public void onChangeColorThree() {
        this.colorToDraw = ColorShape.BLEU;
    }

    @FXML
    public void initialize() {
        drawRectangle(0,40,800,600, ColorShape.LIGHT_GRIS);

        this.setStyle();
        this.mainGroup.setOnMousePressed(event -> {
            System.out.println("Click");
            beginX = (float) event.getX();
            beginY = (float) event.getY();
        });
        this.mainGroup.setOnMouseReleased(event -> {
            System.out.println("Release");
            endX = (float) event.getX();
            endX = Math.min(endX, groupMaxX);
            endY = (float) event.getY();
            endY = Math.min(endY, groupMaxY);
            newShape();
        });
        runner();
    }

    private void setStyle(){
        this.mainGroup.setStyle("-fx-background-color: #252525");
    }


    public void runner() {
//        CircleShape cVert = new CircleShape(150, 150, 100, ColorShape.VERT);
//        System.out.println(cVert);
//        CircleShape cBleu = new CircleShape(270, 330, 100, ColorShape.BLEU);
//        System.out.println(cBleu);
//        RectangleShape rVert = new RectangleShape(250, 200, 100, 200, ColorShape.VIOLET);
//        System.out.println(rVert);
//        RectangleShape rGris = new RectangleShape(50, 50, 100, 200, ColorShape.GRIS);
//        System.out.println(rGris);
//
//        ArrayList<Shape> shapes = new ArrayList<>();
//        shapes.add(cVert);
//        shapes.add(rVert);
//        shapes.add(cBleu);
//        shapes.add(rGris);

        // Add poo.Shape to background
//        addShapeToGroup(shapes);
//        this.mainGroup.getChildren().add();
        List<Shape> shapes = GenerationFromFile.output("src\\main\\resources\\e.txt");
        Consommation consommation = new Consommation(this);
        consommation.consume(shapes);
        Simd.run(shapes, this);

        // TODO : EHEH
//        Surface surface = Board.calcBoard(shapes);
//
//        drawSurface(surface);
//        drawColorShape(Board.getAllColorShapes(shapes));

    }

    public void newShape(){
        if(this.shapeToDraw == "Circle"){
            System.out.println("Circle");
            System.out.println(beginX);
            System.out.println(beginY);
            System.out.println(endX);
            System.out.println(endY);
            drawCircle(beginX, beginY, (float) (Math.sqrt(Math.pow(endX-beginX,2) + Math.pow(endY-beginY,2))), this.colorToDraw);
        }else if (this.shapeToDraw == "Rectangle"){
            System.out.println("Rectangle");
            System.out.println(beginX);
            System.out.println(beginY);
            System.out.println(endX);
            System.out.println(endY);
            float x = Math.min(beginX, endX);
            float y = Math.min(beginY, endY);
            float width = Math.abs(endX - beginX);
            float height = Math.abs(endY - beginY);
            drawRectangle(x, y, width, height, this.colorToDraw);
        }
    }

    public void drawCircle(float x, float y, float radius, ColorShape color){
        Circle circle = new Circle(x, y, radius);
        circle.setStrokeType(StrokeType.INSIDE);
        circle.setStroke(Color.BLACK);
        circle.setFill(Color.web(color.colorCode));
        this.mainGroup.getChildren().add(circle);
    }

    public void drawRectangle(float x, float y, float width, float height, ColorShape color){
        Rectangle rectangle = new Rectangle(x, y, width, height);
        rectangle.setStrokeType(StrokeType.INSIDE);
        rectangle.setStroke(Color.BLACK);
        rectangle.setFill(Color.web(color.colorCode));
        this.mainGroup.getChildren().add(rectangle);
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

    public void addShapeToGroup(Shape shape) {
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

        } else if (shape instanceof BoardShape) {
            Rectangle rectangle = new Rectangle(shape.x, shape.y, ((BoardShape) shape).width, ((BoardShape) shape).height);
            rectangle.setStrokeType(StrokeType.INSIDE);
            rectangle.setStroke(Color.CYAN);
            rectangle.setFill(Color.web("gray", 0.1));
            this.mainGroup.getChildren().add(rectangle);

        }
    }


}
