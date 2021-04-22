package parallel.actions;
import models.RectangleShape;
import models.Shape;

public interface Action {

	RectangleShape transform(Shape shape);

}
