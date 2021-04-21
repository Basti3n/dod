package simd.actions;

import java.util.List;
import models.Shape;

public interface Pipeline {

	List<Shape> output(List<Shape> input);

}
