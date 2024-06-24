package oop24;

/**
 * This class creates and calls the generation of the Wimpelkette
 *
 * @author Peter Krahl, Patrick Folie
 * @version 1.0
 */

public class Main {

    public static void main(String[] args) {

        char[] wimpelfarbe = new char[] { 'r', 'b', 'g', 'w', 's' };
        int[] wimpelanzahl = new int[] { 4, 3, 2, 4, 2 };

        char[] wimpelfarbe1 = new char[] { 'r', 'b', 'g' };
        int[] wimpelanzahl1 = new int[] { 4, 3, 2 };

        char[] wimpelfarbe2 = new char[] { 'r', 'b' };
        int[] wimpelanzahl2 = new int[] { 2, 1 };

        char[] wimpelfarbe3 = new char[] { 'r', 'b', 'g', 's' };
        int[] wimpelanzahl3 = new int[] { 4, 3, 2, 2 };

        Wimpelkette wimpelkette = new Wimpelkette(wimpelanzahl, wimpelfarbe);
        Ergebnis ergebnis = wimpelkette.generiereOptimaleKette();
        System.out.println("Beste Kette: " + ergebnis.getKette());
        System.out.println("Qualitaet: (" + ergebnis.getMinDistanz() + "," + ergebnis.getMinFrequenz() + ")");

        Wimpelkette wimpelkettezwei = new Wimpelkette(wimpelanzahl2, wimpelfarbe2);
        Ergebnis ergebnis2 = wimpelkettezwei.generiereOptimaleKette();
        System.out.println("Beste Kette: " + ergebnis2.getKette());
        System.out.println("Qualitaet: (" + ergebnis2.getMinDistanz() + "," + ergebnis2.getMinFrequenz() + ")")

        Wimpelkette wimpelkettedrei = new Wimpelkette(wimpelanzahl3, wimpelfarbe3);
        Ergebnis ergebnis3 = wimpelkettedrei.generiereOptimaleKette();
        System.out.println("Beste Kette: " + ergebnis3.getKette());
        System.out.println("Qualitaet: (" + ergebnis3.getMinDistanz() + "," + ergebnis3.getMinFrequenz() + ")");

    }
}