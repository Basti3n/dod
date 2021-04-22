package simd;

import java.io.*;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import models.CircleShape;
import models.ColorShape;
import models.RectangleShape;
import models.Shape;
import simd.models.ShapeType;

public class GenerationFromFile {

	public static List<Shape> output(String chemin) {
		File fichier = new File(chemin);

		try (BufferedReader lecteur = new BufferedReader(new FileReader(fichier))) {
			return lecteur.lines().map(GenerationFromFile::toShape).collect(Collectors.toList());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
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
		x = Float.parseFloat(splited[1]);
		y = Float.parseFloat(splited[2]);
		if (splited[0].equals("C")) {
			type = ShapeType.CIRCLE;
			radius = Float.parseFloat(splited[3]);
			colorShape = ColorShape.valueOf(splited[4]);
			return new CircleShape(x, y, radius, colorShape);
		}
		else if (splited[0].equals("R")) {
			type = ShapeType.RECTANGLE;
			width = Float.parseFloat(splited[3]);;
			height = Float.parseFloat(splited[4]);;
			colorShape = ColorShape.valueOf(splited[5]);
			return new RectangleShape(x, y, width, height, colorShape);
		} else
			return null;
	}


}
