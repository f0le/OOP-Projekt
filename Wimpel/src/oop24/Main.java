package oop24;

public class Main {

    public static void main(String[] args) {

        char[] wimpelfarbe = new char[] { 'r', 'b', 'g', 'w', 's' };
        int[] wimpelanzahl = new int[] { 4, 3, 2, 4, 2 };

        char[] wimpelfarbe1 = new char[] { 'r', 'b', 'g' };
        int[] wimpelanzahl1 = new int[] { 4, 3, 2 };

        char[] wimpelfarbe2 = new char[] { 'r', 'b' };
        int[] wimpelanzahl2 = new int[] { 2, 1 };

        Wimpelkette wimpelkette = new Wimpelkette(wimpelanzahl, wimpelfarbe);
        wimpelkette.printWimpelkette();
        wimpelkette.printQuality();

        WimpelketteZwei wimpelkettezwei = new WimpelketteZwei(wimpelanzahl, wimpelfarbe);
        Ergebnis ergebnis = wimpelkettezwei.generiereOptimaleKette();

        System.out.println("Beste Kette:" + ergebnis.getKette());
        System.out.println("Placeholder Anzahl bestmöglicher Lösungen.");

        // Wimpelkette wimpelkette1 = new Wimpelkette(wimpelanzahl1, wimpelfarbe1);
        // wimpelkette1.printWimpelkette();
        // wimpelkette1.printQuality();

        // Wimpelkette wimpelkette2 = new Wimpelkette(wimpelanzahl2, wimpelfarbe2);
        // wimpelkette2.printWimpelkette();
        // wimpelkette2.printQuality();
    }
}
