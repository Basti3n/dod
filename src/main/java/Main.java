import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;


public class Main extends Application {
        public static void main(String[] args) {
            launch(args);
        }

        @Override
        public void start(Stage primaryStage) {
            Group root = new Group();
            Scene scene = new Scene(root, 800, 800, Color.web("#252525"));
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
            RectangleShape rGris = new RectangleShape(50, 50, 100, 200, ColorShape.GRIS);
            System.out.println(rGris);
            ArrayList<Shape> shapes = new ArrayList<>();
            shapes.add(cVert);
            shapes.add(rVert);
            shapes.add(cBleu);
//            shapes.add(rGris);

            // Add Shape to background
            root.getChildren().add(drawShapes(shapes));

            Surface surface = Board.calcBoard(shapes);
            root.getChildren().add(DrawSurface(surface));

//            System.out.println(surface);


//            System.out.println(Board.getAllColorShapes(shapes));

            root.getChildren().add(drawColorShape(Board.getAllColorShapes(shapes)));


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

        public Group drawColorShape(List<ColorShape> colorShapeList) {
            Group shapesToDraw = new Group();
            int counter = 0;
            for (ColorShape colorShape:colorShapeList) {
                Circle circle = new Circle( (counter * 800) / colorShapeList.size() + 25 * colorShapeList.size() , 700 ,25);
                circle.setStrokeType(StrokeType.INSIDE);
                circle.setStroke(Color.BLACK);
                circle.setFill(Color.web(colorShape.colorCode));
                shapesToDraw.getChildren().add(circle);

                counter+= 1;
            }
            return shapesToDraw;
        }


}
