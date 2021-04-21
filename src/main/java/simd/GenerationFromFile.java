package simd;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import models.CircleShape;
import models.ColorShape;
import models.Shape;
import simd.models.ShapeType;

public class GenerationFromFile {
	
	public static List<Shape> output(String chemin) throws Exception {
		File fichier = new File(chemin);
		
		try (BufferedReader lecteur = new BufferedReader(new FileReader(fichier))) {
			return lecteur.lines().map(GenerationFromFile::toShape).collect(Collectors.toList());
		}

	}

	public static Shape toShape(String line) {
		float x;
		float y;
		float width;
		float height;
		float radius;
		ColorShape colorShape;
		ShapeType type;

		String[] splited = line.split(";");
		colorShape = ColorShape.BLEU;
		if (splited[0].equals("C")) {
			type = ShapeType.CIRCLE;
			radius = Float.parseFloat(splited[3]);
			colorShape = ColorShape.VERT;
		}
		else if (splited[0].equals("R")) {
			type = ShapeType.RECTANGLE;
			width = Float.parseFloat(splited[3]);;
			height = Float.parseFloat(splited[4]);;
			colorShape = ColorShape.VERT;
		}
		x = Float.parseFloat(splited[1]);
		y = Float.parseFloat(splited[2]);

		return new CircleShape(x, y, x, colorShape);
	}


}
