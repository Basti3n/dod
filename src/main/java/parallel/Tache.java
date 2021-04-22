package parallel;

import models.Shape;
import parallel.actions.Action;

import java.util.concurrent.Callable;

public class Tache implements Callable<Shape> {

	private Shape shape;
	private Action mapping;
	
	public Tache(Shape shape, Action transform) {
		this.shape = shape;
		this.mapping = transform;
	}
	
	@Override
	public Shape call() throws Exception {
		if (mapping!=null) {
			return mapping.transform(shape);
		} else {
			return shape;
		}
	}

}
