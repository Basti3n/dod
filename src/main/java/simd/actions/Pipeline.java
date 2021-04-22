package simd.actions;

import models.Shape;

import java.util.List;

public interface Pipeline {

    List<Shape> output(List<Shape> input);

}
