package simd.actions;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import simd.models.Shape;
import simd.models.ShapeType;

public class Mutation implements Pipeline {

    ShapeType type;

    public Mutation(ShapeType type) {
        this.type = type;
    }

    @Override
    public List<Shape> output(List<Shape> input) {
        // Mutation un type en un autre, ex : cercle ou rectangle
        final ShapeType mutationType = this.type;

        // Si mutation en cercle
        // -> cercle@1 => cercle@1#mut
        // -> rectangle@2 => cercle@2#mut
        return input.stream()
                .map((shape) -> {
                    if (mutationType.equals(ShapeType.CIRCLE) && shape.type.equals(ShapeType.RECTANGLE)) {
                        float radius = Math.min(shape.height, shape.width) / (float) 2;
                        return new Shape(shape.x + radius, shape.y - radius, radius, shape.colorShape);
                    } else if (mutationType.equals(ShapeType.RECTANGLE) && shape.type.equals(ShapeType.CIRCLE)) {
                        return new Shape(shape.x - shape.radius, shape.y + shape.radius, shape.radius * 2, shape.radius * 2, shape.colorShape);
                    }
                    return shape;
                })
                .collect(Collectors.toList());
    }

}

