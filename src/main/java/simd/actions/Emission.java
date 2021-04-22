package simd.actions;

import models.BoardShape;
import models.RectangleShape;
import models.Shape;
import simd.algorithm.Comparator;

import java.util.ArrayList;
import java.util.List;

public class Emission {
    Comparator comparator;

    public Emission(Comparator<RectangleShape> comparator) {
        this.comparator = comparator;
    }

    public List<Shape> output(List<RectangleShape> shapes) {
        ArrayList<Shape> list = new ArrayList<>();
        RectangleShape rectangle = shapes.stream()
                .reduce(null,
                        (RectangleShape board, RectangleShape rectangleShape) ->
                                (RectangleShape) this.comparator.exec(rectangleShape, board)
                );
        list.add(new BoardShape(rectangle.x, rectangle.y, rectangle.width, rectangle.height));
        return list;
    }
}
