package oop24;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * This class creates and calls the generation of the Wimpelkette
 *
 * @author Peter Krahl, Patrick Folie
 * @version 1.0
 */
public class Wimpelkette {

    private HashMap<Character, Integer> wimpelMap;
    //private ArrayList<Ergebnis> besteErgebnisse;
    //private int anzahlBesteLoesungen;

    /**
     * Constructor for the Wimpelketten class
     * 
     * @param anzahl int array
     * @param farben char array
     **/
    public Wimpelkette(int[] anzahl, char[] farben) {

        wimpelMap = new HashMap<>();

        for (int i = 0; i < anzahl.length; i++) {
            wimpelMap.put(farben[i], anzahl[i]);
        }

        //besteErgebnisse = new ArrayList<>();
        //anzahlBesteLoesungen = 0;
    }

    /**
     * This method generates an optimal solution(calls other methods) and prints the
     * number of best solutions
     * <p>
     * Usage:
     * <p>
     * <code>
     * generiereOptimaleKette()
     * </code>
     *
     * @return Ergebnis, object which stores the current best Wimpelkette
     **/
    public void generiereOptimaleKette() {

        ArrayList<Ergebnis> besteErgebnisse = generiereKetten(new ArrayList<Wimpel>(), wimpelMap);

        System.out.println("Beste Wimpelkette: " + besteErgebnisse.get(0));
        System.out.println("Anzahl beste Ergebnisse: " + besteErgebnisse.size()); 
/*         if (besteErgebnisse.isEmpty()) {

            return null;
        } else {

            System.out.println("Anzahl der besten Lösungen: " + anzahlBesteLoesungen);
            return besteErgebnisse.get(0);
        } */
    }

    /**
     * This method is the recursive function and generates the permutations of the
     * Wimpelketten
     * <p>
     * Usage:
     * <p>
     * <code>
     * generiereKetten()
     * </code>
     *
     * @param aktuelleKette      ArrayList<Wimpel>, current Wimpelkette
     * @param verbleibendeWimpel Wimpelketten HashMap<Character, Integer>, remaining
     *                           Wimpelketten
     * @return void
     **/
    private ArrayList<Ergebnis> generiereKetten(ArrayList<Wimpel> aktuelleKette, HashMap<Character, Integer> verbleibendeWimpel) {
        
        ArrayList<Ergebnis> besteErgebnisse = new ArrayList<>();

        if (verbleibendeWimpel.values().stream().allMatch(anzahl -> anzahl == 0)) {

            Ergebnis neuesErgebnis = evaluiereKette(aktuelleKette);

            if (besteErgebnisse.isEmpty() || neuesErgebnis.istBesser(besteErgebnisse.get(0))) {

                besteErgebnisse.clear();
                besteErgebnisse.add(neuesErgebnis);

            } else if (neuesErgebnis.istGleich(besteErgebnisse.get(0))) {

                besteErgebnisse.add(neuesErgebnis);
            }

            // System.out.println("Evaluated Kette: " + neuesErgebnis.getKette() + " with
            // MinDistanz: " + neuesErgebnis.getMinDistanz() + " and MinFrequenz: " +
            // neuesErgebnis.getMinFrequenz());
            return besteErgebnisse;
        }

        for (Character farbe : verbleibendeWimpel.keySet()) {

            if (verbleibendeWimpel.get(farbe) > 0) {

                aktuelleKette.add(new Wimpel(farbe));
                verbleibendeWimpel.put(farbe, verbleibendeWimpel.get(farbe) - 1);
                generiereKetten(aktuelleKette, verbleibendeWimpel);
                verbleibendeWimpel.put(farbe, verbleibendeWimpel.get(farbe) + 1);
                aktuelleKette.remove(aktuelleKette.size() - 1);
            }
        }
    }

    /**
     * This method evaluates the quality of a Wimpelkette and returns an Ergebnis
     * object
     * <p>
     * Usage:
     * <p>
     * <code>
     * evaluiereKette()
     * </code>
     * 
     * @param wimpelliste ArrayList<Wimpel>, Wimpelkette to be evaluated
     * @return Ergebnis, object which
     **/
    private Ergebnis evaluiereKette(ArrayList<Wimpel> wimpelliste) {

        int minDistanz = Integer.MAX_VALUE;
        int minFrequenz = 0;

        HashMap<Character, Integer> letztePosition = new HashMap<>();

        for (int i = 0; i < wimpelliste.size(); i++) {

            char farbe = wimpelliste.get(i).getFarbe();

            if (letztePosition.containsKey(farbe)) {

                int distanz = i - letztePosition.get(farbe);

                if (distanz < minDistanz) {

                    minDistanz = distanz;
                    minFrequenz = 1;

                } else if (distanz == minDistanz) {

                    minFrequenz++;
                }
            }

            letztePosition.put(farbe, i);
        }

        StringBuilder ketteBuilder = new StringBuilder();

        for (Wimpel wimpel : wimpelliste) {
            ketteBuilder.append(wimpel.getFarbe());
        }
        
        String kette = ketteBuilder.toString();

        return new Ergebnis(minDistanz, minFrequenz, kette);
    }
}