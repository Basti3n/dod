package simd;

import javafx.controllers.IndexController;
import models.RectangleShape;
import models.Shape;
import models.ShapeType;
import simd.actions.Consommation;
import simd.actions.Emission;
import simd.actions.Filtre;
import simd.actions.Mutation;
import simd.algorithm.RectangleComparator;
import simd.algorithm.RectangleConverter;

import java.util.ArrayList;
import java.util.List;

public class Simd {

    public static void run(List<Shape> shapes, IndexController indexController) {

        if (!shapes.isEmpty()) {
            List<RectangleShape> convertedCircles = new Mutation(new RectangleConverter()).output(new Filtre(ShapeType.CIRCLE).output(shapes));
            List<RectangleShape> rectangles = new Filtre(ShapeType.RECTANGLE).output(shapes);

            List<RectangleShape> finalShapes = new ArrayList<>();
            finalShapes.addAll(convertedCircles);
            finalShapes.addAll(rectangles);

            Consommation consommation = new Consommation(indexController);
            consommation.consume(new Emission(new RectangleComparator()).output(finalShapes));
        }
    }
}
