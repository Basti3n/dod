package simd.actions;

import models.RectangleShape;
import models.Shape;
import simd.algorithm.RectangleConverter;

import java.util.List;
import java.util.stream.Collectors;

public class Mutation {

    RectangleConverter converter;

    public Mutation(RectangleConverter converter) {
        this.converter = converter;
    }

    public List<RectangleShape> output(List<Shape> input) {
        return input.stream()
                .map((shape) -> this.converter.convert(shape))
                .collect(Collectors.toList());
    }

}

