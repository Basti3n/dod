
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
import javafx.stage.Stage;

import java.util.ArrayList;


public class Main extends Application {
        public static void main(String[] args) {
            launch(args);
        }

        @Override
        public void start(Stage primaryStage) {
            Group root = new Group();
            Scene scene = new Scene(root, 800, 600, Color.web("#252525"));
            primaryStage.setScene(scene);

            runner(root);

            primaryStage.show();
        }

        public void runner( Group root){
            CircleShape cVert = new CircleShape(150, 150, 100, ColorShape.VERT);
            System.out.println(cVert);
            CircleShape cBleu = new CircleShape(270, 330, 100, ColorShape.BLEU);
            System.out.println(cBleu);
            RectangleShape rVert = new RectangleShape(250, 200, 100, 200, ColorShape.VIOLET);
            System.out.println(rVert);
            ArrayList<Shape> shapes = new ArrayList<>();
            shapes.add(cVert);
            shapes.add(rVert);
            shapes.add(cBleu);

            // Add Shape to background
            root.getChildren().add(drawShapes(shapes));

            Surface surface = Board.calcBoard(shapes);
            System.out.println(surface);


            System.out.println(Board.getAllColorShapes(shapes));

            root.getChildren().add(DrawSurface(surface));

        }

        public Group drawShapes(ArrayList<Shape> shapes){
            Group shapesToDraw = new Group();
            for (Shape shape:shapes) {

                if(shape instanceof CircleShape){

                    Circle circle = new Circle(shape.x, shape.y,((CircleShape) shape).radius);
                    circle.setStrokeType(StrokeType.INSIDE);
                    circle.setStroke(Color.BLACK);
                    circle.setFill(Color.web(shape.colorShape.colorCode));
                    shapesToDraw.getChildren().add(circle);

                }else if (shape instanceof RectangleShape){
                    Rectangle rectangle =  new Rectangle(shape.x, shape.y, ((RectangleShape) shape).width, ((RectangleShape) shape).height);
                    rectangle.setStrokeType(StrokeType.INSIDE);
                    rectangle.setStroke(Color.BLACK);
                    rectangle.setFill(Color.web(shape.colorShape.colorCode));

                    shapesToDraw.getChildren().add(rectangle);

                }
            }
            return shapesToDraw;
        }

        public Rectangle DrawSurface(Surface surface){
            Rectangle rectangle =  new Rectangle(surface.xmin, surface.ymin, surface.getWidth(), surface.getHeight());
            rectangle.setStrokeType(StrokeType.INSIDE);
            rectangle.setStroke(Color.CYAN);
            rectangle.setFill(Color.web("gray", 0.1));
            return rectangle;
        }


}
