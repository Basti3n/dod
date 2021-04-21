package models;

public class Surface {
    public float xmin = 800;
    public float ymin = 800;
    public float xmax = 0;
    public float ymax;

    public Surface() {
        this.xmin = 800;
        this.ymin = 800;
        this.xmax = 0;
        this.ymax = 0;
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
