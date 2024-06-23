package oop24;

public class WimpelZwei {

    private char farbe;
    private int anzahl;

    public WimpelZwei(char farbe, int anzahl) {

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