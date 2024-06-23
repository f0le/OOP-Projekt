package oop24;

import java.util.ArrayList;
import java.util.HashMap;

public class Wimpelkette {

    private ArrayList<Wimpel> wimpelliste;

    public Wimpelkette(int[] anzahl, char[] farben) {
        wimpelliste = new ArrayList<>();
        for (int i = 0; i < anzahl.length; i++) {
            for (int j = 0; j < anzahl[i]; j++) {
                wimpelliste.add(new Wimpel(farben[i]));
            }
        }
    }


    public Ergebnis generiereOptimaleKette() {
        ArrayList<Ergebnis> ergebnisse = new ArrayList<>();
        generierePermutationen(wimpelliste, 0, ergebnisse);

        if (ergebnisse.isEmpty()) {
            return null;
        } else {
            System.out.println(ergebnisse.size());
            return ergebnisse.get(0);
        }
    }

    private void generierePermutationen(ArrayList<Wimpel> wimpelliste, int index, ArrayList<Ergebnis> ergebnisse) {
        if (index == wimpelliste.size()) {
            Ergebnis neuesErgebnis = evaluiereKette(wimpelliste);
            if (ergebnisse.isEmpty() || neuesErgebnis.istBesser(ergebnisse.get(0))) {
                ergebnisse.clear();
                ergebnisse.add(neuesErgebnis);
            } else if (neuesErgebnis.istGleich(ergebnisse.get(0))) {
                ergebnisse.add(neuesErgebnis);
            }
            
            return;
        }

        for (int i = index; i < wimpelliste.size(); i++) {
            tausche(wimpelliste, index, i);
            generierePermutationen(wimpelliste, index + 1, ergebnisse);
            tausche(wimpelliste, index, i);
        }
    }

    private void tausche(ArrayList<Wimpel> wimpelliste, int index1, int index2) {
        Wimpel temp = wimpelliste.get(index1);
        wimpelliste.set(index1, wimpelliste.get(index2));
        wimpelliste.set(index2, temp);
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