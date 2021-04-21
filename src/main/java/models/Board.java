package models;

import poo.Surface;

public class Board extends Shape{
    public float x;
    public float y;
    public ColorShape colorShape;

    public Board(float x, float y, ColorShape colorShape) {
        this.x = x;
        this.y = y;
        this.colorShape = colorShape;
    }

    @Override
    public Surface getSurface() {
        return null;
    }
}
