package models;

import poo.Surface;

public class RectangleShape extends Shape {

    public float width;
    public float height;

    public RectangleShape(float x, float y, float width, float height, ColorShape colorShape) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.colorShape = colorShape;
    }

    @Override
    public Surface getSurface() {
        return new Surface(x, y, x + width, y + height);
    }

    @Override
    public String toString() {
        return "Rectangle" +
                " x=" + x +
                ", y=" + y +
                " width=" + width +
                ", height=" + height +
                " colorShape=" + colorShape;
    }

}
