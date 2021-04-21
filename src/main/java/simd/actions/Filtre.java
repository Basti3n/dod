package simd.actions;
import java.util.List;
import java.util.stream.Collectors;

import simd.models.Shape;
import simd.models.ShapeType;

public class Filtre implements Pipeline {

	ShapeType type;
	
	// Filtre d'un type
	public Filtre(ShapeType type) {
		this.type = type;
	}
	
	public List<Shape> output(List<Shape> input) {
		return input.stream()
		.filter(
			forme -> {
				return forme.type.equals(type);
			}
		).collect(Collectors.toList());
	}

}
