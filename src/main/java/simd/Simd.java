package simd;

import javafx.controllers.IndexController;
import models.RectangleShape;
import simd.actions.*;
//import simd.actions.Emission;

import java.util.ArrayList;
import java.util.List;
import models.Shape;
import models.ShapeType;
import simd.algorithm.RectangleConverter;
import simd.algorithm.RectangleComparator;

public class Simd {

    public static void run(List<Shape> shapes, IndexController indexController) {
        List<RectangleShape> convertedCircles = new Mutation(new RectangleConverter()).output(new Filtre(ShapeType.CIRCLE).output(shapes));
        List<RectangleShape> rectangles = new Filtre(ShapeType.RECTANGLE).output(shapes);

        List<RectangleShape> finalShapes = new ArrayList<>();
        finalShapes.addAll(convertedCircles);
        finalShapes.addAll(rectangles);

        Consommation consommation = new Consommation(indexController);
        consommation.consume(new Emission(new RectangleComparator()).output(finalShapes));
    }
}
