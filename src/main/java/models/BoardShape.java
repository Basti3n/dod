package models;

public class BoardShape extends Shape {
    public float width;
    public float height;

    public BoardShape(float x, float y, float width, float height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }
}
