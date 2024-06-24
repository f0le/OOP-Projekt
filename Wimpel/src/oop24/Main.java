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

        char[] wimpelfarbe5 = new char[] { 'r', 'g'};
        int[] wimpelanzahl5 = new int[] { 2, 1 };

   /*      Wimpelkette wimpelkette1 = new Wimpelkette(wimpelanzahl1, wimpelfarbe1);
        wimpelkette1.generiereOptimaleKette();

        Wimpelkette wimpelkette2 = new Wimpelkette(wimpelanzahl2, wimpelfarbe2);
        wimpelkette2.generiereOptimaleKette();


        Wimpelkette wimpelkette3 = new Wimpelkette(wimpelanzahl3, wimpelfarbe3);
        wimpelkette3.generiereOptimaleKette();


        Wimpelkette wimpelkette4 = new Wimpelkette(wimpelanzahl4, wimpelfarbe4);
        wimpelkette4.generiereOptimaleKette(); */

        Wimpelkette wimpelkette5 = new Wimpelkette(wimpelanzahl5, wimpelfarbe5);
        wimpelkette5.generiereOptimaleKette();

    }
}