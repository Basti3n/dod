package poo;

public enum ColorShape {
    ROUGE("RED"),
    VERT("GREEN"),
    VIOLET("PURPLE"),
    BLEU("BLUE"),
    GRIS("#959595");

    public final String colorCode;

    ColorShape(String s) {
        this.colorCode = s;
    }

    @Override
    public String toString() {
        return colorCode;
    }
}
