package simd;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import simd.models.Shape;

public class GenerationFromFile {
	
	public static List<Shape> output(String chemin) throws Exception {
		File fichier = new File(chemin);
		
		try (BufferedReader lecteur = new BufferedReader(new FileReader(fichier))) {
			return lecteur.lines().map(Shape::new).collect(Collectors.toList());
		}

	}


}
