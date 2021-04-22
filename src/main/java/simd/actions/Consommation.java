package simd.actions;

import javafx.controllers.IndexController;
import models.Shape;

import java.util.List;

public class Consommation {
	public IndexController controller;

	public Consommation(IndexController controller) {
		this.controller = controller;
	}

	public void consume(List<Shape> shapes) {
		shapes.stream().forEach(
			shape -> {
				this.controller.addShapeToGroup(shape);
			}
		);
	}

}
