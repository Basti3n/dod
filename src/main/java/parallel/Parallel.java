package parallel;

import javafx.controllers.IndexController;
import models.RectangleShape;
import models.Shape;
import parallel.actions.Action;
import parallel.actions.Mutation;
import simd.actions.Consommation;
import simd.algorithm.RectangleConverter;
import simd.actions.Emission;
import simd.algorithm.RectangleComparator;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Parallel {

    private static List<RectangleShape> parallelize(List<Shape> shapes, int nbCoeurs) {
        ExecutorService processeur = Executors.newFixedThreadPool(nbCoeurs);

        List<Future<Shape>> taches = new ArrayList<>();
        Action action = new Mutation(new RectangleConverter());

        for (Shape shape : shapes) {
            taches.add(
                processeur.submit(
                        new Tache(shape, action)
                )
            );
        }

        List<RectangleShape> resultats = new ArrayList<>();
        try {
            for (Future<Shape> tache : taches) {
                // Extraction de chaque résultat par tâche
                    resultats.add((RectangleShape) tache.get());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        processeur.shutdown();

        return resultats;
    }

    public static void run(List<Shape> shapes, int coreCount, IndexController indexController) {
        List<RectangleShape> finalShapes =  parallelize(shapes, coreCount);
        Consommation consommation = new Consommation(indexController);
        consommation.consume(new Emission(new RectangleComparator()).output(finalShapes));
    }

}
