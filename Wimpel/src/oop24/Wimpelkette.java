package oop24;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Wimpelkette {

    // arraylist for the wimpelkette
    private ArrayList<Char> wimpellist = new ArrayList<Char>();
    // helper list for calculating the quality of a wimpelkette
    private ArrayList<Int> qualitylist = new ArrayList<Int>();
    // helper variable for calculating the quality of a wimpelkette
    private int distance;
    // stores the quality value
    private int[] quality = new int[2];
    // stores position of array increment
    int position = 0;

    // den Konstruktor brauchen wir nicht denke ich
    public Wimpelkette(int[] count, char[] color) {

        // WRONG don't do it like that!
        // create the wimpelkette
        // iterate over the different numbers
        for (int i = 0; i <= count.length; i++) {
            // add chars as often as the number says
            for (int j = 0; j <= count[i]; j++) {
                wimpellist.add(color[i]);
            }
        }
        this.wimpellist = wimpellist;
    }

    private Ergebnis generiereOptimaleKette(ArrayList<Char> kette, int position, Ergebnis bestErgebnis, int[] count,
            char[] color) {

        // in der Theorie wollen wir hier ja immer unser (bisher) bestes Ergebnis
        // übergeben und eine
        // Kondition haben, die abbricht, sobald wir kein bessere finden

        // einfach immer eine farbe mehr in die wimpelkette reinsortieren? und zwei
        // arrays mit farbe und anzahl der wimpel uebergeben und dann einfach immer n-1
        // der arrays verarbeiten. Und die Rekursion ist zu Ende wenn die beiden arrays
        // abgearbeitet wurden?
        // Von den Farben mit den meisten Vorkommen anfangen und dann die anderen farben
        // so reinsortieren, dass die qualitaet besser wird? Oder einfach vorher
        // ueberlegt wie es am optimalsten reinvertielt wird?
        // das einfach ueber die position des iterators bei dem inkrementieren von den
        // arrays machen?

        if (position > color.length) {
            return bestErgebnis;
        }
        position++;
        generiereOptimaleKette(kette, position, bestErgebnis, color, count);
    }

    // method which calculates the quality of a color of the wimpelkette
    public int getQuality(char color) {
        // store all indexes of ocurrences of a color in an arraylist
        for (int i = 0; i <= wimpellist.length; i++) {
            if (wimpellist[i] == color) {
                qualitylist.add(i);
            }
        }

        int distance = qualitylist[0] - qualitylist[1];
        if (distance < 0) {
            distance *= -1;
        }

        for (int i = 2; i <= quality.length; i++) {
            // if distance is smaller than previous recorded distance save it
            if (distance - quality[i] < 0) {
                distance = distance - quality[i];
                if (distance < 0) {
                    distance *= -1;
                }
            }
        }
        return this.quality;
    }

    // aboslut noch nicht fertig, hatte hier nen Knoten im Kopf und mache die Tage
    // weite :DD
}