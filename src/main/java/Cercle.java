public class Cercle extends Forme {

    float radius;

    public Cercle(float cx, float cy, float radius, Couleur color) {
        this.x = cx;
        this.y = cy;
        this.radius = radius;
        this.color = color;
    }

    public Surface getSurface() {
        return new Surface(x, y - radius * 2, x + radius * 2, y);
    }

    @Override
    public String toString() {
        return "Cercle" +
                "\n\tx=" + x +
                ", y=" + y +
                "\n\tradius=" + radius +
                "\n\tcolor=" + color;
    }


}
