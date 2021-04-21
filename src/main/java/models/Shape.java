package models;

public abstract class Shape {
    public float x;
    public float y;
    public ColorShape colorShape;
    public abstract Surface getSurface();
}
