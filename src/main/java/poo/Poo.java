package poo;

import javafx.controllers.IndexController;
import models.*;
import simd.actions.Consommation;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Poo {

    public static void run(List<Shape> shapes, IndexController indexController) {
        List<Shape> finalShapes = new ArrayList<>();

        finalShapes.add(generateBoard(shapes));
        Consommation consommation = new Consommation(indexController);
        consommation.consume(finalShapes);

    }

    public static BoardShape generateBoard(List<Shape> shapes) {
        RectangleShape board = shapes.stream()
                .map(Poo::convert)
                .reduce(null, (RectangleShape acc, RectangleShape currentShape) -> {
                    System.out.println(currentShape);
                    return compareBiggest(acc, currentShape);
                });
        System.out.println("board.toString()");
        System.out.println(board.toString());
        return new BoardShape(board.x, board.y, board.width, board.height);
    }

    public static RectangleShape compareBiggest(RectangleShape board, RectangleShape shape) {
        if (board == null) {
            return new RectangleShape(shape.x, shape.y, shape.width, shape.height, shape.colorShape);
        }
        float x = Math.min(board.x, shape.x);
        float y = Math.min(board.y, shape.y);
        float width = board.x + board.width > shape.x + shape.width ? (board.x + board.width) - x : (shape.x + shape.width) - x;
        float height = board.y + board.height > shape.y + shape.height ? (board.y + board.height) - y : (shape.y + shape.height) - y;
        return new RectangleShape(x, y, width, height, board.colorShape);
    }

    public static RectangleShape convert(Shape shape) {
        if (shape instanceof CircleShape) {
            return new RectangleShape(
                    shape.x - ((CircleShape) shape).radius,
                    shape.y - ((CircleShape) shape).radius,
                    ((CircleShape) shape).radius * 2,
                    ((CircleShape) shape).radius * 2, shape.colorShape
            );
        }

        return (RectangleShape) shape;
    }

    public static List<ColorShape> getAllColorShapes(List<Shape> shapes) {
        return shapes.stream()
                .map((Shape shape) -> shape.colorShape)
                .distinct()
                .collect(Collectors.toList());
    }

}
