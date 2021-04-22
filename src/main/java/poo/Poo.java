package poo;

import javafx.controllers.IndexController;
import models.BoardShape;
import models.ColorShape;
import models.Shape;
import models.Surface;

import java.util.List;
import java.util.stream.Collectors;

public class Poo {

    public IndexController controller;

    public Poo(IndexController controller) {
        this.controller = controller;
    }


    public Surface calcBoard(List<Shape> shapes){
        return shapes.stream()
                .map((Shape::getSurface))
                .reduce(new Surface(), (Surface surface, Surface currentSurface) -> {
                    return surface.getOccupedSurface(currentSurface);
                });
    }

    public void run(List<Shape> shapes){
        for (Shape shape : shapes) {
            this.controller.addShapeToGroup(shape);
        }
        Surface board = this.calcBoard(shapes);
        this.controller.addShapeToGroup(new BoardShape(board.xmin, board.ymin, board.xmax - board.xmin, board.ymax - board.ymin));
    }
}
