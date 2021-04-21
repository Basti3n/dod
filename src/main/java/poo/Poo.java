package poo;

import models.ColorShape;
import models.Shape;
import models.Surface;

import java.util.List;
import java.util.stream.Collectors;

public class Poo {


    public static Surface calcBoard(List<Shape> shapes){
        return shapes.stream()
                .map((Shape::getSurface))
                .reduce(new Surface(), (Surface surface, Surface currentSurface) -> {
                    System.out.println(currentSurface);
                    return surface.getOccupedSurface(currentSurface);
                });
    }

    public static List<ColorShape> getAllColorShapes(List<Shape> shapes) {
        return shapes.stream()
                .map((Shape shape) -> shape.colorShape)
                .distinct()
                .collect(Collectors.toList());
    }
}
