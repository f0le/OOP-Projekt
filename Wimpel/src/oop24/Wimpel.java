package oop24;

public class Wimpel {

    private char farbe;
    private int anzahl;

    public Wimpel(char farbe, int anzahl) {

        this.farbe = farbe;
        this.anzahl = anzahl;
    }

    public char getFarbe() {

        return this.farbe;
    }

    public int getAnzahl() {

        return this.anzahl;
    }

    public void setAnzahl(int anzahl) {

        this.anzahl = anzahl;
    }

}
