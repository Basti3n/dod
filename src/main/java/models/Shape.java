package models;

import poo.Surface;

public abstract class Shape {
    public float x;
    public float y;
    public ColorShape colorShape;
    public abstract Surface getSurface();
}
