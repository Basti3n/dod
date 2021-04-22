package parallel.actions;

import models.RectangleShape;
import models.Shape;
import simd.algorithm.RectangleConverter;

public class Mutation implements Action {

    RectangleConverter converter;

    public Mutation(RectangleConverter converter) {
        this.converter = converter;
    }

    @Override
    public RectangleShape transform(Shape shape) {
        return converter.convert(shape);
    }
}
