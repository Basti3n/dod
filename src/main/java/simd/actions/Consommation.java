package simd.actions;

import models.Shape;

import java.util.List;

public class Consommation {

	public void consume(List<Shape> formes) {
		formes.stream().forEach(
			forme -> {
				System.out.println("  - "+forme);
			}
		);
	}

}
