package models;

public class Surface {
    public float xmin;
    public float ymin;
    public float xmax;
    public float ymax;

    public Surface() {
        this.xmin = 800;
        this.ymin = 800;
        this.xmax = -800;
        this.ymax = -800;
    }

    public Surface(float xmin, float ymin, float xmax, float ymax) {
        this.xmin = xmin;
        this.ymin = ymin;
        this.xmax = xmax;
        this.ymax = ymax;
    }

    public Surface getOccupedSurface(Surface surface) {
        Surface computedSurface = new Surface(xmin, ymin, xmax, ymax);
        if (surface.xmin < computedSurface.xmin)
            computedSurface.xmin = surface.xmin;
        if (surface.ymin < computedSurface.ymin)
            computedSurface.ymin = surface.ymin;
        if (surface.xmax > computedSurface.xmax)
            computedSurface.xmax = surface.xmax;
        if (surface.ymax > computedSurface.ymax)
            computedSurface.ymax = surface.ymax;
        return computedSurface;
    }

    @Override
    public String toString() {
        return "models.Surface{" +
                "xmin=" + xmin +
                ", ymin=" + ymin +
                ", xmax=" + xmax +
                ", ymax=" + ymax +
                '}';
    }

    public float getWidth() {
        return this.xmax - this.xmin;
    }

    public float getHeight() {
        return this.ymax - this.ymin;
    }
}
