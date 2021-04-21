package main.java;

public class Cercle extends Forme {

    public Cercle(float cx, float cy, float radius, Couleur color) {
        this.x = cx - radius;
        this.y = cy - radius;
        this.width = radius * 2;
        this.height = radius * 2;
        this.color = color;
    }

    @Override
    public String toString() {
        return "Cercle" +
                "\n\tx=" + x +
                ", y=" + y +
                "\n\tdiameter=" + width +
                "\n\tcolor=" + color;
    }

    public double getArea(){
        return 3.14 * (width / 2) * (width / 2);
    }
}
