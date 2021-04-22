package simd.actions;

import models.CircleShape;
import models.RectangleShape;
import models.Shape;
import models.ShapeType;

import java.util.List;
import java.util.stream.Collectors;

public class Mutation implements Pipeline {

    ShapeType type;

    public Mutation(ShapeType type) {
        this.type = type;
    }

    @Override
    public List<Shape> output(List<Shape> input) {
        final ShapeType mutationType = this.type;

        return input.stream()
                .map((shape) -> {
                    if (mutationType.equals(ShapeType.CIRCLE)
                            && shape instanceof RectangleShape) {
                        float radius = Math.min(((RectangleShape) shape).height, ((RectangleShape) shape).width) / (float) 2;
                        return new CircleShape(
                                shape.x + radius,
                                shape.y - radius,
                                radius,
                                shape.colorShape
                        );
                    } else if (mutationType.equals(ShapeType.RECTANGLE)
                            && shape instanceof CircleShape) {
                        return new RectangleShape(
                                shape.x - ((CircleShape) shape).radius,
                                shape.y - ((CircleShape) shape).radius,
                                ((CircleShape) shape).radius * 2,
                                ((CircleShape) shape).radius * 2, shape.colorShape
                        );
                    }
                    return shape;
                })
                .collect(Collectors.toList());
    }

}

