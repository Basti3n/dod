package models;

public enum ColorShape {
    ROUGE("#b80a07"),
    VERT("#3bd13b"),
    VIOLET("PURPLE"),
    BLEU("#3b4ad1"),
    GRIS("#959595"),
    WHITE("#FFFFFF"),
    LIGHT_GRIS("#252525");

    public final String colorCode;

    ColorShape(String s) {
        this.colorCode = s;
    }

    @Override
    public String toString() {
        return colorCode;
    }
}
