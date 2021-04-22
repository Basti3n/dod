package simd.algorithm;

import models.CircleShape;
import models.RectangleShape;
import models.Shape;

public class RectangleConverter {
    public RectangleShape convert(Shape shape) {
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
}
