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

        ArrayList<Ergebnis> besteErgebnisse = generiereKetten(new ArrayList<Wimpel>(), wimpelMap, new ArrayList<Ergebnis>());

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
    private ArrayList<Ergebnis> generiereKetten(ArrayList<Wimpel> aktuelleKette, HashMap<Character, Integer> verbleibendeWimpel, ArrayList<Ergebnis> besteErgebnisse) {

        if (verbleibendeWimpel.values().stream().allMatch(anzahl -> anzahl == 0)) {


            return evaluiereKette(aktuelleKette);
/* 
            if(besteErgebnisse.isEmpty() || neueErgebnisListe.get(0).istBesser(besteErgebnisse.get(0))) {

                besteErgebnisse.clear();
                besteErgebnisse.add(neueErgebnisListe.get(0));
                //return besteErgebnisse;
    
            } else if (neueErgebnisListe.get(0).istGleich(besteErgebnisse.get(0))) {
    
                besteErgebnisse.add(neueErgebnisListe.get(0));
                //return besteErgebnisse;
            }

            return besteErgebnisse; */
        }

        for (Character farbe : verbleibendeWimpel.keySet()) {

            if (verbleibendeWimpel.get(farbe) > 0) {
                
                aktuelleKette.add(new Wimpel(farbe));
                verbleibendeWimpel.put(farbe, verbleibendeWimpel.get(farbe) - 1);
                ArrayList<Ergebnis> ergebnis2 = generiereKetten(aktuelleKette, verbleibendeWimpel, besteErgebnisse);

                System.out.println(ergebnis2.get(0));

                if( besteErgebnisse.isEmpty() || ergebnis2.get(0).istBesser(besteErgebnisse.get(0))) {

                    besteErgebnisse.clear();
                    besteErgebnisse.add(ergebnis2.get(0));

                } else if ( besteErgebnisse.get(0).istGleich(ergebnis2.get(0))) {

                    besteErgebnisse.add(ergebnis2.get(0));
                }

                verbleibendeWimpel.put(farbe, verbleibendeWimpel.get(farbe) + 1);
                aktuelleKette.remove(aktuelleKette.size() - 1);
                
            }
        }

        return besteErgebnisse;
    }


/*         if (!(verbleibendeWimpel.values().stream().allMatch(anzahl -> anzahl == 0))) {

            for (Character farbe : verbleibendeWimpel.keySet()) {

                if (verbleibendeWimpel.get(farbe) > 0) {
        
                    aktuelleKette.add(new Wimpel(farbe));
                    verbleibendeWimpel.put(farbe, verbleibendeWimpel.get(farbe) - 1);
                    generiereKetten(aktuelleKette, verbleibendeWimpel, besteErgebnisse);
                    verbleibendeWimpel.put(farbe, verbleibendeWimpel.get(farbe) + 1);
                    aktuelleKette.remove(aktuelleKette.size() - 1);
                    
                }
                
            }
            
        }

        besteErgebnisse = evaluiereKette(besteErgebnisse, aktuelleKette);

            // System.out.println("Evaluated Kette: " + neuesErgebnis.getKette() + " with
            // MinDistanz: " + neuesErgebnis.getMinDistanz() + " and MinFrequenz: " +
            // neuesErgebnis.getMinFrequenz());
        return besteErgebnisse;
    } */

/*         for (Character farbe : verbleibendeWimpel.keySet()) {

            if (verbleibendeWimpel.get(farbe) > 0) {

                aktuelleKette.add(new Wimpel(farbe));
                verbleibendeWimpel.put(farbe, verbleibendeWimpel.get(farbe) - 1);
                generiereKetten(aktuelleKette, verbleibendeWimpel);
                verbleibendeWimpel.put(farbe, verbleibendeWimpel.get(farbe) + 1);
                aktuelleKette.remove(aktuelleKette.size() - 1);
            }
        } */

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
    private ArrayList<Ergebnis> evaluiereKette(ArrayList<Wimpel> wimpelliste) {

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

        Ergebnis neuesErgebnis = new Ergebnis(minDistanz, minFrequenz, kette);

        ArrayList<Ergebnis> neueErgebnisListe = new ArrayList<Ergebnis>();
        
        neueErgebnisListe.add(neuesErgebnis);

        return neueErgebnisListe;
    }
}