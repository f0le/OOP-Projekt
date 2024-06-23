package oop24;

import java.util.ArrayList;

public class WimpelketteZwei {

    // arraylist for the wimpelkette
    private ArrayList<WimpelZwei> wimpelliste;

    // den Konstruktor brauchen wir nicht denke ich
    public WimpelketteZwei(int[] anzahl, char[] farben) {

        wimpelliste = new ArrayList<>();
        for (int i = 0; i <= anzahl.length; i++) {
            wimpelliste.add(new WimpelZwei(farben[i], anzahl[i]));   
        }
    }

    // Starter für die Rekursion
    public Ergebnis generiereOptimaleKette() {

        int wimpelGesamt = 0;
        for(WimpelZwei wimpel : wimpelliste) {
            wimpelGesamt += wimpel.getAnzahl();
        }
        
        char[] kette = new char[wimpelGesamt];
        return optimaleKetteRekursiv(kette, 0, new Ergebnis(Integer.MAX_VALUE, Integer.MAX_VALUE, ""));//rekursive Funktion
    }

    // Rekursion
    public Ergebnis optimaleKetteRekursiv(char[] kette, int pos, Ergebnis bestesErgebnis) {

        if(pos == kette.length) {

            String aktuelleKette = new String(kette);
            return evaluriereKette(kette, aktuelleKette, bestesErgebnis);
        }

        for(WimpelZwei wimpel : wimpelliste) {

            if(wimpel.getAnzahl() > 0) {
                
                kette[pos] = wimpel.getFarbe();
                wimpel.setAnzahl(wimpel.getAnzahl() -1);

                Ergebnis ergebnis = optimaleKetteRekursiv(kette, pos + 1, bestesErgebnis);

                if(ergebnis.istBesser(bestesErgebnis)) {

                    bestesErgebnis = ergebnis;
                }

                wimpel.setAnzahl(wimpel.getAnzahl() + 1); //backtracking
            }
        }

        return bestesErgebnis;
    }

    // Überprüfung der Qualität
    private Ergebnis evaluriereKette(char[] kette, String aktuelleKette, Ergebnis aktuellBestesErgebnis) {

        int minDistanz = Integer.MAX_VALUE;
        int minFrequenz = 0;

        for (int i = 0; i < kette.length; i++) {

            for (int j = i + 1; j < kette.length; j++) {

                if(kette[i] == kette[j]) {

                    int distanz = j - i;

                    if(distanz < minDistanz) {

                        minDistanz = distanz;
                        minFrequenz = 1;
                    } else if (distanz == minDistanz) {

                        minFrequenz++;
                    }
                }
            }
        }

        Ergebnis aktuellesErgebis = new Ergebnis(minDistanz, minFrequenz, aktuelleKette);

        if (aktuellesErgebis.istBesser(aktuellBestesErgebnis)) {

            return aktuellesErgebis;

        } else if (aktuellesErgebis.istGleich(aktuellBestesErgebnis)) {

            return new Ergebnis(aktuellesErgebis.getMinDistanz(), aktuellesErgebis.getMinFrequenz(), aktuellesErgebis.getKette());
        }

        return aktuellBestesErgebnis;
    }
}