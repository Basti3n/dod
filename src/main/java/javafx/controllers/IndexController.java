package javafx.controllers;

import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
import javafx.stage.Stage;
import models.*;
import parallel.Parallel;
import poo.Poo;

import simd.Simd;
import simd.actions.Consommation;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class IndexController {
    public Stage stage;

    @FXML
    public Pane mainPane;

    @FXML
    public Group mainGroup;

    @FXML
    public Group headerBox;

    @FXML
    public Label labelProcessTime;

    public String shapeToDraw = "Circle";

    public float beginX = 0;
    public float beginY = 0;
    public float endX = 0;
    public float endY = 0;

    public ColorShape colorToDraw = ColorShape.ROUGE;

    public String calcMode = "SIMD";

    public float groupMaxX = 800;
    public float groupMaxY = 640;
    public float groupMinX = 0;
    public float groupMinY = 40;

    public List<Shape> shapes;


    public void setStage(Stage primaryStage) {
        this.stage = primaryStage;
    }

    @FXML
    public void onCircleButtonClicked() {
        this.shapeToDraw = "Circle";
        this.printCurrentMode();
    }

    @FXML
    public void onRectangleButtonClicked() {
        this.shapeToDraw = "Rectangle";
        this.printCurrentMode();
    }

    @FXML
    public void onChangeColorOne() {
        this.colorToDraw = ColorShape.ROUGE;
        this.printCurrentMode();
    }

    @FXML
    public void onChangeColorTwo() {
        this.colorToDraw = ColorShape.VERT;
        this.printCurrentMode();
    }

    @FXML
    public void onChangeColorThree() {
        this.colorToDraw = ColorShape.BLEU;
        this.printCurrentMode();
    }

    @FXML
    public void onClearButtonClicked() {
        this.clearGroup();
        this.shapes = new ArrayList<>();
        this.printCurrentMode();
    }

    @FXML
    public void onChangeToPOO() {
        this.calcMode = "POO";
        this.launchCalc();
        this.printCurrentMode();
    }

    @FXML
    public void onChangeToSIMD() {
        this.calcMode = "SIMD";
        this.launchCalc();
        this.printCurrentMode();
    }

    @FXML
    public void onChangeToPARALLEL() {
        this.calcMode = "PARALLEL";
        this.launchCalc();
        this.printCurrentMode();
    }

    @FXML
    public void initialize() {
        this.drawBackground();

        this.mainGroup.setOnMousePressed(event -> {
            beginX = (float) event.getX();
            beginY = (float) event.getY();
        });
        this.mainGroup.setOnMouseReleased(event -> {
            endX = (float) event.getX();
            endX = Math.min(endX, groupMaxX);
            endY = (float) event.getY();
            endY = Math.min(endY, groupMaxY);
            newShape();
        });
        runner();

        this.printCurrentMode();

    }

    public void clearGroup() {
        this.mainGroup.getChildren().clear();
        this.drawBackground();
        this.setLabelProcessTime(0);
    }

    private void setStyle() {
        this.mainGroup.setStyle("-fx-background-color: #252525");
        this.headerBox.toFront();

    }

    private void drawBackground() {
        this.drawRectangle(0, 40, 800, 600, ColorShape.LIGHT_GRIS);
        this.setStyle();
        this.drawAxis();
    }

    private void drawLegend() {
        this.drawRectangle(0, this.groupMaxY, this.groupMaxX, 100, ColorShape.WHITE);
        drawColorShape(this.getAllColorShapes(this.shapes));
    }

    public void setLabelProcessTime(long timer) {
        this.labelProcessTime.setText(String.format("%.03f ms", (float) timer / 1000000));
    }

    private void printCurrentMode(){
        System.out.println(this.shapeToDraw + " - " + this.calcMode + " - " + this.colorToDraw.name());
    }

    private void drawAxis() {
        Line lineX = new Line(this.groupMinX, (this.groupMaxY / 2) + groupMinY, this.groupMaxX, (this.groupMaxY / 2) + groupMinY);
        lineX.setStyle("-fx-stroke: white;");
        lineX.getStrokeDashArray().addAll(10d);
        Line lineY = new Line((this.groupMaxX / 2) + groupMinX, this.groupMinY, (this.groupMaxX / 2) + groupMinX, this.groupMaxY);
        lineY.setStyle("-fx-stroke: white;");
        lineY.getStrokeDashArray().addAll(10d);
        this.mainGroup.getChildren().add(lineX);
        this.mainGroup.getChildren().add(lineY);
    }


    public void runner() {
        this.shapes = GenerationFromFile.output("src\\main\\resources\\e.txt");
        launchCalc();
    }

    public void launchCalc() {
        this.clearGroup();
        long beginTime;
        long endTime;

        Consommation consommation = new Consommation(this);
        consommation.consume(this.shapes);

        switch (this.calcMode) {
            case "POO":
                beginTime = System.nanoTime();
                Poo.run(this.shapes, this);
                endTime = System.nanoTime();
                this.setLabelProcessTime(endTime - beginTime);
                break;
            case "SIMD":
                beginTime = System.nanoTime();
                Simd.run(this.shapes, this);
                endTime = System.nanoTime();
                this.setLabelProcessTime(endTime - beginTime);
                break;
            case "PARALLEL":
                beginTime = System.nanoTime();
                Parallel.run(this.shapes, 8,this);
                endTime = System.nanoTime();
                this.setLabelProcessTime(endTime - beginTime);
                break;
            default:
                break;
        }
        this.drawLegend();
    }

    public void newShape() {
        if (this.shapeToDraw.equals("Circle")) {
            float x = beginX - this.groupMinX - (this.groupMaxX / 2);
            float y = beginY - this.groupMinY - (this.groupMaxY / 2);
            float radius = (float) (Math.sqrt(Math.pow(endX - beginX, 2) + Math.pow(endY - beginY, 2)));
            this.shapes.add(new CircleShape(x, y, radius, this.colorToDraw));
        } else if (this.shapeToDraw.equals("Rectangle")) {
            float x = Math.min(beginX, endX) - this.groupMinX - (this.groupMaxX / 2);
            float y = Math.min(beginY, endY) - this.groupMinY - (this.groupMaxY / 2);
            float width = Math.abs(endX - beginX);
            float height = Math.abs(endY - beginY);
            this.shapes.add(new RectangleShape(x, y, width, height, this.colorToDraw));
        }
        this.launchCalc();
    }

    public void drawCircle(float x, float y, float radius, ColorShape color) {
        Circle circle = new Circle(x, y, radius);
        circle.setStrokeType(StrokeType.INSIDE);
        circle.setStroke(Color.BLACK);
        circle.setFill(Color.web(color.colorCode));
        this.mainGroup.getChildren().add(circle);
    }

    public void drawRectangle(float x, float y, float width, float height, ColorShape color) {
        Rectangle rectangle = new Rectangle(x, y, width, height);
        rectangle.setStrokeType(StrokeType.INSIDE);
        rectangle.setStroke(Color.BLACK);
        rectangle.setFill(Color.web(color.colorCode));
        this.mainGroup.getChildren().add(rectangle);
    }

    public void drawBoard(float x, float y, float width, float height) {
        Rectangle rectangle = new Rectangle(x, y, width, height);
        rectangle.setStrokeType(StrokeType.INSIDE);
        rectangle.setStroke(Color.CYAN);
        rectangle.setFill(Color.web("gray", 0.1));
        this.mainGroup.getChildren().add(rectangle);
    }

    public void drawColorShape(List<ColorShape> colorShapeList) {
        Group shapesToDraw = new Group();
        int counter = 0;
        for (ColorShape colorShape : colorShapeList) {
            Circle circle = new Circle((float) (counter * 800) / colorShapeList.size() + (float) 400 / colorShapeList.size(), 690, 25);
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
            this.drawCircle(shape.x + this.groupMinX + (this.groupMaxX / 2), shape.y + this.groupMinY + (this.groupMaxY / 2), ((CircleShape) shape).radius, shape.colorShape);

        } else if (shape instanceof RectangleShape) {
            this.drawRectangle(shape.x + this.groupMinX + (this.groupMaxX / 2), shape.y + this.groupMinY + (this.groupMaxY / 2), ((RectangleShape) shape).width, ((RectangleShape) shape).height, shape.colorShape);

        } else if (shape instanceof BoardShape) {
            this.drawBoard(shape.x + this.groupMinX + (this.groupMaxX / 2), shape.y + this.groupMinY + (this.groupMaxY / 2), ((BoardShape) shape).width, ((BoardShape) shape).height);
        }
    }

    public List<ColorShape> getAllColorShapes(List<Shape> shapes) {
        return shapes.stream()
                .map((Shape shape) -> shape.colorShape)
                .distinct()
                .collect(Collectors.toList());
    }

}
