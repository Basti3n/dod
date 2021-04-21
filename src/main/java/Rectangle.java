package main.java;

public class Rectangle extends Forme {

    public Rectangle(float x, float y, float width, float height, Couleur color) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.color = color;
    }

    @Override
    public String toString() {
        return "Rectangle" +
                "\n\tx=" + x +
                ", y=" + y +
                "\n\twidth=" + width +
                ", height=" + height +
                "\n\tcolor=" + color;
    }

    public double getArea(){
        return this.width * this.height;
    }
}
