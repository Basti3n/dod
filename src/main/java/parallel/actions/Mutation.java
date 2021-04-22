package parallel.actions;

import models.CircleShape;
import models.RectangleShape;
import models.Shape;
import models.ShapeType;

public class Mutation implements Action {

	ShapeType type;

	public Mutation(ShapeType type) {
		this.type = type;
	}

	@Override
	public Shape transform(Shape shape) {
		final ShapeType mutationType = this.type;

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
	}
}
