package oop24;

import java.lang.reflect.Array;

public class Wimpelkette {

    // declare array for the wimpels
    char[] wimpelkette;
    // char[] colorlist;
    // helper variable for calculating the quality of a wimpelkette
    private int distance;
    private int minDistance;
    private int minTotalDistance;
    private int frequency;
    // stores the quality of a wimpelkette
    private int[] quality = new int[2];
    // stores overall number of wimpels
    int wimpelanzahl = 0;

    // den Konstruktor brauchen wir nicht denke ich
    public Wimpelkette(int[] count, char[] color) {

        // get number of wimpels and create an array with the corresponding size
        for (int i = 0; i < count.length; i++) {
            this.wimpelanzahl += count[i];
        }
        // initialize array for the wimpels
        this.wimpelkette = new char[wimpelanzahl];

        // this.colorlist = color;
    }

    /*
     * private Ergebnis generiereOptimaleKette(int[] wimpelkette, int position,
     * int[] count, char[] color) {
     * 
     * // in der Theorie wollen wir hier ja immer unser (bisher) mines Ergebnis
     * // übergeben und eine
     * // Kondition haben, die abbricht, sobald wir kein bessere finden
     * 
     * // einfach immer eine farbe mehr in die wimpelkette reinsortieren? und zwei
     * // arrays mit farbe und anzahl der wimpel uebergeben und dann einfach immer
     * n-1
     * // der arrays verarbeiten. Und die Rekursion ist zu Ende wenn die beiden
     * arrays
     * // abgearbeitet wurden?
     * // Von den Farben mit den meisten Vorkommen anfangen und dann die anderen
     * farben
     * // so reinsortieren, dass die qualitaet besser wird? Oder einfach vorher
     * // ueberlegt wie es am optimalsten reinvertielt wird?
     * // das einfach ueber die position des iterators bei dem inkrementieren von
     * den
     * // arrays machen?
     * 
     * if (position > color.length) {
     * return minErgebnis;
     * }
     * position++;
     * generiereOptimaleKette(kette, position, minErgebnis, color, count);
     * }
     */

    // generate unoptimized standardchain
    public void generiereStandardKette(int[] count, char[] color) {
        // counter for creation of simple wimpelkette
        int k = 0;
        for (int i = 0; i < color.length; i++) {
            for (int j = 0; j < count[i]; j++, k++) {
                wimpelkette[k] = color[i];
            }
        }
    }

    // neu schreiben fuer gewoehnliches array
    // method which calculates the quality of the wimpelkette
    public void getQuality(char[] color) {

        // quality consists of two things
        // first the smallest distance of the occurrence of two wimpels
        // second the commonness of that distance
        // first calculate the smallest distance of wimpels
        for (int i = 0; i < color.length; i++) {
            frequency = 0;
            for (int j = 0; j < wimpelkette.length; j++) {
                if (wimpelkette[j] == color[i]) {
                    for (int k = j + 1; k < wimpelkette.length; k++) {
                        // vergleich j!=k nicht notwendig
                        if (wimpelkette[k] == color[i] && wimpelkette[j] != wimpelkette[k]) {
                            // sollte nicht negativ werden so
                            distance = k - j;
                            if (distance < 0) {
                                distance *= -1;
                            }
                            j = k;
                            frequency++;
                            if (distance < minDistance) {
                                minDistance = distance;
                            }
                            // leave the k loop as current k is new j
                            // we want the distance of the current k to the next element in the array
                            break;
                        }
                    }
                } else {
                    j++;
                }
            }
            // if current quality is worse then store that quality
            if (minTotalDistance == 0) {
                minTotalDistance = minDistance;
            }
            if (minDistance < minTotalDistance) {
                minTotalDistance = minDistance;
            }
        }

        // set overall quality
        this.quality[0] = minTotalDistance;
        this.quality[1] = frequency;

        System.out.print("Qualitaet:(");
        System.out.print(this.quality[0] + ",");
        System.out.print(this.quality[1] + ")\n");
    }

    // prints the current wimpelkette
    public void printWimpelkette() {
        // print wimpelkette
        System.out.print("Kette:");
        for (int i = 0; i < this.wimpelanzahl; i++) {
            System.out.print(wimpelkette[i] + ",");
        }
        System.out.print(wimpelkette[wimpelkette.length - 1] + "\n");
    }

}