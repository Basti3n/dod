package simd.actions;

import models.BoardShape;
import models.Shape;
import models.Surface;

import java.util.ArrayList;
import java.util.List;

public class Emission implements Pipeline {

    @Override
    public List<Shape> output(List<Shape> shapes) {
        ArrayList<Shape> list = new ArrayList<>();
        Surface computedSurface = shapes.stream()
                .map((Shape::getSurface))
                .reduce(new Surface(), (Surface surface, Surface currentSurface) -> {
                    System.out.println(currentSurface);
                    return surface.getOccupedSurface(currentSurface);
                });
        list.add(new BoardShape(computedSurface.xmin, computedSurface.ymin, computedSurface.getWidth(), computedSurface.getHeight()));
        return list;
    }
}
