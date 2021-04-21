package simd;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.List;
import java.util.stream.Collectors;

public class GenerationFromFile {
	
	public static List<String> output(String chemin) throws Exception {
		File fichier = new File(chemin);
		
		try (BufferedReader lecteur = new BufferedReader(new FileReader(fichier))) {
			return lecteur.lines().collect(Collectors.toList());
		}

	}

}
