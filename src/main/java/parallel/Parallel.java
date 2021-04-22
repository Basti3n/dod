package parallel;

import javafx.controllers.IndexController;
import models.RectangleShape;
import models.Shape;
import models.ShapeType;
import parallel.actions.Action;
import parallel.actions.Mutation;
import simd.actions.Consommation;
import simd.actions.Emission;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Parallel {

    private static List<Shape> parallelize(List<Shape> shapes, int nbCoeurs) {
        ExecutorService processeur = Executors.newFixedThreadPool(nbCoeurs);

        List<Future<Shape>> taches = new ArrayList<>();
        Action action = new Mutation(ShapeType.RECTANGLE);

        System.out.println("[ " + nbCoeurs + " coeur(s) ]");

        for (Shape shape : shapes) {
            taches.add(
                processeur.submit(
                        new Tache(shape, action)
                )
            );
        }

        System.out.println();
        System.out.println("[ MUTATION ] (Parellel)");

        List<Shape> resultats = new ArrayList<>();
        try {
            for (Future<Shape> tache : taches) {
                // Extraction de chaque résultat par tâche
                    resultats.add(tache.get());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        processeur.shutdown();

        System.out.println();
        System.out.println("[ RESULTATS ] (Parellel)");
        for (Shape resultat : resultats) {
            System.out.println("  - " + resultat);
        }

        System.out.println();

        return resultats;
    }

    public static void run(List<Shape> shapes, int coreCount, IndexController indexController) {
        List<Shape> finalShapes =  parallelize(shapes, coreCount);
        Consommation consommation = new Consommation(indexController);
        consommation.consume(new Emission().output(finalShapes));
    }

}
