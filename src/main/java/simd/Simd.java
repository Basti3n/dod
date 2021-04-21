package simd;

import javafx.controllers.IndexController;
import models.CircleShape;
import simd.actions.Consommation;
//import simd.actions.Emission;
import simd.actions.Emission;
import simd.actions.Filtre;
import simd.actions.Mutation;

import java.util.ArrayList;
import java.util.List;
import models.Shape;
import simd.models.ShapeType;

public class Simd {

    public static void run(List<Shape> shapes, IndexController indexController) {
        List<Shape> circles = new Mutation(ShapeType.CIRCLE).output(new Filtre(ShapeType.CIRCLE).output(shapes));
        List<Shape> rectangles = new Filtre(ShapeType.RECTANGLE).output(shapes);

        List<Shape> finalShapes = new ArrayList<>();
        finalShapes.addAll(circles);
        finalShapes.addAll(rectangles);

        Consommation consommation = new Consommation(indexController);
        consommation.consume(new Emission().output(finalShapes));

    }
}
