package oop24;

import java.util.ArrayList;

public class Wimpelkette {

    private int[] countlist;
    // helper variables for calculating the quality of a wimpelkette
    private int distance;
    private int minDistance;
    private int frequency;
    // stores the quality of a wimpelkette, first element is the distance, second
    // the frequency of that distance
    private int[] quality = new int[2];
    private ArrayList<Wimpel> wimpelkette = new ArrayList<Wimpel>();
    private ArrayList<String> colorlist;
    private int max = 0;
    private char maxCountColor;
    private boolean occurrence = true;

    public Wimpelkette(int[] count, char[] color) {

        // arrayelements have the corresponding number of colored wimpels
        this.countlist = count;
        // this.colorlist = color;
        this.wimpelkette = new ArrayList<Wimpel>();
    }

    // private void generiereOptimaleKette(ArrayList<Wimpel> wimpelkette, int
    // position, int[] count, char[] color) {

    // einfach immer eine farbe mehr in die wimpelkette reinsortieren? und zwei
    // arrays mit farbe und anzahl der wimpel uebergeben und dann einfach immer
    // n-1
    // der arrays verarbeiten. Und die Rekursion ist zu Ende wenn die beiden
    // arrays
    // abgearbeitet wurden?
    // Von den Farben mit den meisten Vorkommen anfangen und dann die anderen
    // farben
    // so reinsortieren, dass die qualitaet besser wird? Oder einfach vorher
    // ueberlegt wie es am optimalsten reinvertielt wird?
    // das einfach ueber die position des iterators bei dem inkrementieren von
    // den
    // arrays machen?

    /*
     * // abbruchsbedingung
     * // if(){return;}
     * for (int i = 0; i < count.length; i++) {
     * if (count[i] > max) {
     * this.max = count[i];
     * this.maxCountColor = color[i];
     * }
     * position++;
     * }
     * // wenn wimpelkette leer initialisiere mit farbe von max elementen, das sind
     * // dann gleichzeitig die buckets in die reinsortiert wird
     * if (wimpelkette.isEmpty()) {
     * for (int i = 0; i <= max; i++) {
     * wimpelkette.add();
     * }
     * }
     * generiereOptimaleKette(wimpelkette, position, count, color);
     * }
     */

    // generate unoptimized standardchain
    public void generiereStandardKette(int[] count, char[] color) {
        for (int i = 0; i < color.length; i++) {
            for (int j = 0; j < count[i]; j++) {
                this.wimpelkette.add(new Wimpel(color[i]));
            }
        }
    }

    // calculates the quality of the current wimpelkette
    // parameter is the wimpelkette to check
    public void getQuality(ArrayList<Wimpel> wimpelkette) {
        // store all colors of current wimpelkette and use that to generate the quality,
        // so we are independent of additional input
        colorlist = new ArrayList<String>();
        this.wimpelkette = wimpelkette;

        for (int i = 0; i < this.wimpelkette.size(); i++) {
            for (int j = 0; j < colorlist.size(); j++) {
                // color not already stored, store it
                if (wimpelkette.get(i).getColor() == colorlist.get(j).charAt(0)) {
                    occurrence = true;
                    break;
                } else {
                    occurrence = false;
                }
            }
            if (occurrence == false) {

            }
        }

        for (int i = 0; i < colorlist.size(); i++) {
            frequency = 0;
            for (int j = 0; j < wimpelkette.size(); j++) {
                if (wimpelkette.get(j).getColor() == colorlist.get(i).charAt(0)) {
                    for (int k = j + 1; k < wimpelkette.size(); k++) {
                        // vergleich j!=k nicht notwendig
                        if (wimpelkette.get(k).getColor() == colorlist.get(i).charAt(0)) {
                            // sollte nicht negativ werden so
                            distance = k - j;
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
            // if current quality is smaller then store that quality
            if (quality[0] == 0) {
                quality[0] = minDistance;
            }
            if (minDistance < quality[0]) {
                quality[0] = minDistance;
            }
        }
    }

    // prints the quality of the current wimpelkette
    public void printQuality() {
        getQuality(wimpelkette);
        System.out.print("Qualitaet: (" + quality[0] + "," + quality[1] + ")\n");
    }

    // prints the current wimpelkette
    public void printWimpelkette() {
        System.out.print("Kette: ");
        for (int i = 0; i < wimpelkette.size() - 1; i++) {
            System.out.print(wimpelkette.get(i).getColor() + ",");
        }
        System.out.print(wimpelkette.get(wimpelkette.size() - 1).getColor() + "\n");
    }

}