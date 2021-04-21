package main.java;

public class Main {
    public static void main(String[] args) {
        // Prints "Hello, World" to the terminal window.
        Cercle cRouge = new Cercle(1, 2, 1,Couleur.ROUGE);
        Rectangle rVert = new Rectangle(1, 2, 1, 2,Couleur.VERT);
        System.out.println(cRouge);
        System.out.println(cRouge.getArea());
        System.out.println(rVert);
        System.out.println(rVert.getArea());
        System.out.println("Hello, :(.");
    }
}
