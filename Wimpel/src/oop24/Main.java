package oop24;

/**
 * This class creates and calls the generation of the Wimpelkette
 *
 * @author Peter Krahl, Patrick Folie
 * @version 1.0
 */

public class Main {

    public static void main(String[] args) {

        char[] wimpelfarbe1 = new char[] { 'r', 'b', 'g', 'w', 's' };
        int[] wimpelanzahl1 = new int[] { 4, 3, 2, 4, 2 };

        char[] wimpelfarbe2 = new char[] { 'r', 'b', 'g', 'w', 's' };
        int[] wimpelanzahl2 = new int[] { 2, 5, 3, 1, 2 };

        char[] wimpelfarbe3 = new char[] { 'r', 'b', 'g', 'w', 's' };
        int[] wimpelanzahl3 = new int[] { 2, 3, 2, 4, 3 };

        char[] wimpelfarbe4 = new char[] { 'r', 'b', 'g', 's' };
        int[] wimpelanzahl4 = new int[] { 4, 3, 2, 2 };

        Wimpelkette wimpelkette1 = new Wimpelkette(wimpelanzahl1, wimpelfarbe1);
        Ergebnis ergebnis1 = wimpelkette1.generiereOptimaleKette();
        System.out.println("Beste Kette: " + ergebnis1.getKette());
        System.out.println("Qualitaet: (" + ergebnis1.getMinDistanz() + "," + ergebnis1.getMinFrequenz() + ")");

        Wimpelkette wimpelkette2 = new Wimpelkette(wimpelanzahl2, wimpelfarbe2);
        Ergebnis ergebnis2 = wimpelkette2.generiereOptimaleKette();
        System.out.println("Beste Kette: " + ergebnis2.getKette());
        System.out.println("Qualitaet: (" + ergebnis2.getMinDistanz() + "," + ergebnis2.getMinFrequenz() + ")");

        Wimpelkette wimpelkette3 = new Wimpelkette(wimpelanzahl3, wimpelfarbe3);
        Ergebnis ergebnis3 = wimpelkette3.generiereOptimaleKette();
        System.out.println("Beste Kette: " + ergebnis3.getKette());
        System.out.println("Qualitaet: (" + ergebnis3.getMinDistanz() + "," + ergebnis3.getMinFrequenz() + ")");

        Wimpelkette wimpelkette4 = new Wimpelkette(wimpelanzahl4, wimpelfarbe4);
        Ergebnis ergebnis4 = wimpelkette4.generiereOptimaleKette();
        System.out.println("Beste Kette: " + ergebnis4.getKette());
        System.out.println("Qualitaet: (" + ergebnis4.getMinDistanz() + "," + ergebnis4.getMinFrequenz() + ")");

    }
}