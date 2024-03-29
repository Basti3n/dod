package models;

public class CircleShape extends Shape {

    public float radius;

    public CircleShape(float cx, float cy, float radius, ColorShape colorShape) {
        this.x = cx;
        this.y = cy;
        this.radius = radius;
        this.colorShape = colorShape;
    }

    @Override
    public String toString() {
        return "Cercle " +
                "x=" + x +
                ", y=" + y +
                " radius=" + radius +
                " colorShape=" + colorShape;
    }


}
