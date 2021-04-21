package poo;

public class Surface {
    public double xmin = 800;
    public double ymin = 800;
    public double xmax = 0;
    public double ymax;

    public Surface() {
        this.xmin = 800;
        this.ymin = 800;
        this.xmax = 0;
        this.ymax = 0;
    }

    public Surface(double xmin, double ymin, double xmax, double ymax) {
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
        return "poo.Surface{" +
                "xmin=" + xmin +
                ", ymin=" + ymin +
                ", xmax=" + xmax +
                ", ymax=" + ymax +
                '}';
    }

    public double getWidth() {
        return this.xmax - this.xmin;
    }

    public double getHeight() {
        return this.ymax - this.ymin;
    }
}
