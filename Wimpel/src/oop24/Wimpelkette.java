package oop24;

import java.util.ArrayList;
import java.util.HashMap;

public class Wimpelkette {

    private HashMap<Character, Integer> wimpelMap;
    private ArrayList<Ergebnis> besteErgebnisse;
    private int anzahlBesteLoesungen;

    public Wimpelkette(int[] anzahl, char[] farben) {
        wimpelMap = new HashMap<>();
        for (int i = 0; i < anzahl.length; i++) {
            wimpelMap.put(farben[i], anzahl[i]);
        }
        besteErgebnisse = new ArrayList<>();
        anzahlBesteLoesungen = 0;
    }

    public Ergebnis generiereOptimaleKette() {
        generiereKetten(new ArrayList<>(), wimpelMap);

        if (besteErgebnisse.isEmpty()) {
            return null;
        } else {
            System.out.println("Anzahl der besten Lösungen: " + anzahlBesteLoesungen);
            return besteErgebnisse.get(0);
        }
    }

    private void generiereKetten(ArrayList<Wimpel> aktuelleKette, HashMap<Character, Integer> verbleibendeWimpel) {
        if (verbleibendeWimpel.values().stream().allMatch(anzahl -> anzahl == 0)) {
            Ergebnis neuesErgebnis = evaluiereKette(aktuelleKette);
            if (besteErgebnisse.isEmpty() || neuesErgebnis.istBesser(besteErgebnisse.get(0))) {
                besteErgebnisse.clear();
                besteErgebnisse.add(neuesErgebnis);
                anzahlBesteLoesungen = 1;
            } else if (neuesErgebnis.istGleich(besteErgebnisse.get(0))) {
                besteErgebnisse.add(neuesErgebnis);
                anzahlBesteLoesungen++;
            }

            System.out.println("Evaluated Kette: " + neuesErgebnis.getKette() + " with MinDistanz: " + neuesErgebnis.getMinDistanz() + " and MinFrequenz: " + neuesErgebnis.getMinFrequenz());
            return;
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