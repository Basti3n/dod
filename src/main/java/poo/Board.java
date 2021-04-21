package poo;

import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class Board {

    public static Surface calcBoard(ArrayList<Shape> shapes){
        return shapes.stream()
                .map((shape -> shape.getSurface()))
                .reduce(new Surface(), (Surface surface, Surface currentSurface) -> {
            System.out.println(currentSurface);
            return surface.getOccupedSurface(currentSurface);
        });
    }

    public static List<ColorShape> getAllColorShapes(ArrayList<Shape> shapes) {
        return shapes.stream()
                .map((Shape shape) -> shape.colorShape)
                .distinct()
                .collect(Collectors.toList());
    }
}
