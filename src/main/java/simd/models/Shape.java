package simd.models;

import poo.ColorShape;

public class Shape {
    public float x;
    public float y;
    public float width;
    public float height;
    public float radius;
    public ColorShape colorShape;
    public ShapeType type;

    public Shape(float x, float y, float width, float height, ColorShape colorShape) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.colorShape = colorShape;
        this.type = ShapeType.RECTANGLE;
    }

    public Shape(float x, float y, float radius, ColorShape colorShape) {
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.colorShape = colorShape;
        this.type = ShapeType.CIRCLE;
    }

    public Shape(String line) {
        String[] splited = line.split(";");
        if (splited[0].equals("C")) {
            this.type = ShapeType.CIRCLE;
            this.radius = Float.parseFloat(splited[3]);
            this.colorShape = ColorShape.VERT;
        }
        else if (splited[0].equals("R")) {
            this.type = ShapeType.RECTANGLE;
            this.width = Float.parseFloat(splited[3]);;
            this.height = Float.parseFloat(splited[4]);;
            this.colorShape = ColorShape.VERT;
        }
        this.x = Float.parseFloat(splited[1]);
        this.y = Float.parseFloat(splited[2]);
    }
}
