package simd.actions;
import models.CircleShape;
import models.RectangleShape;
import models.Shape;
import simd.models.ShapeType;

import java.util.List;
import java.util.stream.Collectors;


public class Filtre implements Pipeline {

	ShapeType type;
	
	// Filtre d'un type
	public Filtre(ShapeType type) {
		this.type = type;
	}
	
	public List<Shape> output(List<Shape> input) {
		return input.stream()
		.filter(
			shape -> shape instanceof CircleShape && type.equals(ShapeType.CIRCLE)
					|| shape instanceof RectangleShape && type.equals(ShapeType.RECTANGLE)
		).collect(Collectors.toList());
	}

}
