package oop24;

import java.util.ArrayList;
import java.util.Collections;

public class Wimpelkette {

    // helper variables for calculating the quality of a wimpelkette
    private int distance;
    private int minDistance;
    private int frequency;
    // stores the quality of a wimpelkette, first element is the distance, second
    // the frequency of that distance
    private int[] quality = new int[2];
    private ArrayList<Wimpel> wimpelkette;
    private ArrayList<Character> colorlist;
    private ArrayList<Integer> countlist;
    private int max = 0;
    private int maxindex;
    private boolean occurrence = true;
    private int iteration;
    private int position;

    public Wimpelkette(int[] count, char[] color) {

        this.wimpelkette = new ArrayList<Wimpel>();
        // initialize the arraylist
        this.countlist = new ArrayList<Integer>();
        for (int i = 0; i < color.length; i++) {
            countlist.add(count[i]);
        }
        // initialize the arraylist
        this.colorlist = new ArrayList<Character>();
        for (int i = 0; i < color.length; i++) {
            colorlist.add(color[i]);
        }

        // this.generiereStandardKette(count, color);
        this.generiereOptimaleKette(wimpelkette, 0, 0, this.countlist, this.colorlist);
    }

    // generate optimal solution recursively, iteration needs to be 0 on first
    // invocation
    private void generiereOptimaleKette(ArrayList<Wimpel> wimpelkette, int iteration, int position,
            ArrayList<Integer> countlist,
            ArrayList<Character> colorlist) {

        // abbruchbedingung wenn alle farben sortiert wurden
        if (colorlist.isEmpty()) {
            return;
        }

        // bestimme die am haufigsten vorkommende farbe und ihren index
        max = Collections.max(countlist);
        maxindex = countlist.indexOf(max);
        System.out.println(max);
        System.out.println(maxindex);

        // wenn wimpelkette leer initialisiere mit farbe von max vorkommenden elementen,
        // das sind
        // dann gleichzeitig die buckets in die reinsortiert wird
        // setze den iterator dann richtig, sodass immer nach den jetzt erstellten
        // elementen einsortiert wird
        if (wimpelkette.isEmpty()) {
            // add the color as often as it is in the countlist
            for (int i = 0; i <= max; i++) {
                wimpelkette.add(new Wimpel(colorlist.get(maxindex)));
            }
            // remove the color and the used count of the colors from the lists
            colorlist.remove(maxindex);
            countlist.remove(maxindex);
        }

        // speichere die stelle des iterators, falls eine farbe weniger oft als max-1
        // vorkommt um dann in naechstem loop fort die elemente einzusortieren
        // generiereOptimaleKette(this.wimpelkette, iteration, position, this.countlist,
        // this.colorlist);
    }

    // generate unoptimized standardchain
    public void generiereStandardKette(int[] count, char[] color) {
        for (int i = 0; i < color.length; i++) {
            for (int j = 0; j < count[i]; j++) {
                wimpelkette.add(new Wimpel(color[i]));
            }
        }
    }

    // gibt die Anzahl an Loesungen aus
    public void printAnzahlLoesungen() {
    }

    // generiert permutationen und vergleicht mit der
    public void generierePermutation() {
    }

    // calculates the quality of the current wimpelkette
    // parameter is the wimpelkette to check
    public void getQuality(ArrayList<Wimpel> wimpelkette) {
        // store all colors of current wimpelkette and use that to generate the quality,
        // so we are independent of additional input
        colorlist = new ArrayList<Character>();
        this.wimpelkette = wimpelkette;

        // stores all occurring colors of the input
        for (int i = 0; i < wimpelkette.size(); i++) {
            for (int j = 0; j < colorlist.size(); j++) {
                // color not already stored, store it
                if (wimpelkette.get(i).getColor() == colorlist.get(j)) {
                    occurrence = true;
                    break;
                } else {
                    occurrence = false;
                }
            }
            if (occurrence == false) {
                colorlist.add(wimpelkette.get(i).getColor());
            }
        }

        // determines the quality of the input
        for (int i = 0; i < colorlist.size(); i++) {
            frequency = 0;
            for (int j = 0; j < wimpelkette.size(); j++) {
                if (wimpelkette.get(j).getColor() == colorlist.get(i)) {
                    for (int k = j + 1; k < wimpelkette.size(); k++) {
                        // vergleich j!=k nicht notwendig
                        if (wimpelkette.get(k).getColor() == colorlist.get(i)) {
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
        getQuality(this.wimpelkette);
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