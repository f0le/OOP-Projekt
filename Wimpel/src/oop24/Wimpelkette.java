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
    // wimpellist which we create
    private ArrayList<Wimpel> wimpelkette;
    private ArrayList<Character> colorlist;
    private ArrayList<Wimpel> results;
    // unsorted wimpels which we have to sort
    private ArrayList<Wimpel> unsortedwimpel;
    private int max = 0;
    private int maxindex = 0;
    private boolean occurrence = true;
    private int iteration;
    private int position;
    private boolean empty;
    private int count;
    private char currentColor;
    private char color;
    private char lastColor;
    private int[] qualityResult;
    private int countSolution;

    public Wimpelkette(int[] count, char[] color) {

        this.wimpelkette = new ArrayList<Wimpel>();
        this.unsortedwimpel = new ArrayList<Wimpel>();
        for (int i = 0; i < color.length; i++) {
            for (int j = 0; j < count.length; j++) {
                unsortedwimpel.add(new Wimpel(color[i]));
            }
        }

        this.generiereStandardKette(count, color);
        // this.generiereOptimaleKette(wimpelkette, this.countlist,
        // this.colorlist);
    }

    // generate optimal solution recursively, iteration needs to be 0 on first
    // invocation
    private int generiereOptimaleKette(ArrayList<Wimpel> wimpelkette, ArrayList<Wimpel> result,
            ArrayList<Wimpel> unsortedwimpel, char lastColor, int[] qualityResult, int countSolution) {

        // prueft ob die liste mit den unsortierten wimpeln leer ist
        for (int i = 0; i < unsortedwimpel.size(); i++) {
            if (unsortedwimpel.get(i).getCount() > 0) {
                empty = false;
            } else {
                empty = true;
            }
        }
        // abbruchbedingung, alle wimpel wurden sortiert
        if (wimpelEmpty(unsortedwimpel)) {
            // hier methode fuer kette vergleichen und falls qualitaet besser dann setze
            // aktuelle kette als beste kette
            // falls die qualitaet der gefundenen wimpelkette hoeher ist, dann setze die
            // anzahl der loesungen auf 1
            // auch hier das attribut erhoehen, falls die qualitaet der gefundenen
            // wimpelkette gleich ist der aktuellel
            for (int i = 0; i < 2; i++) {
            }
        }

        // wenn wimpelkette leer initialisiere mit farbe von max vorkommenden elementen,
        // das sind
        if (wimpelkette.isEmpty()) {
            wimpelkette.add(new Wimpel(unsortedwimpel.get(getMaxCountElement()).getColor(), 1));
        }
        for (int i = 0; i < unsortedwimpel.size(); i++) {
            if (unsortedwimpel.get(i).getCount() > 0
                    && unsortedwimpel.get(i).getColor() != unsortedwimpel.get(i - 1).getColor()) {
                wimpelkette.add(new Wimpel(unsortedwimpel.get(i).getColor(), 1));
                unsortedwimpel.set(unsortedwimpel.get(i).getColor(), unsortedwimpel.get(i).getCount() - 1);
            }
        }
        for (int i = 0; i < wimpelkette.size(); i++) {
            System.out.print(wimpelkette.get(i));
        }

        generiereOptimaleKette(wimpelkette, result, unsortedwimpel, lastColor, qualityResult, countSolution);
    }

    // generate unoptimized standardchain
    public void generiereStandardKette(int[] count, char[] color) {
        for (int i = 0; i < color.length; i++) {
            for (int j = 0; j < count[i]; j++) {
                wimpelkette.add(new Wimpel(color[i], 1));
            }
        }
    }

    public boolean wimpelEmpty(ArrayList<Wimpel> unsortedwimpel) {
        this.unsortedwimpel = unsortedwimpel;
        for (int i = 0; i < this.unsortedwimpel.size(); i++) {
            if (this.unsortedwimpel.get(i).getCount() > 0) {
                empty = false;
            } else {
                empty = true;
            }
        }
        return empty;
    }

    // TODO finish
    // calculates max distance of a set color from an existing wimpellist
    public int getMaxDistance(char color) {
        for (Wimpel wimpel : wimpelkette) {
        }
        return maxindex;
    }

    public int getMaxCountElement() {
        max = 0;
        maxindex = 0;
        for (int i = 0; i < wimpelkette.size(); i++) {
            if (wimpelkette.get(i).getCount() > max) {
                max = wimpelkette.get(i).getCount();
                maxindex = i;
            }
        }
        return maxindex;
    }

    // calculates the quality of the current wimpelkette
    // parameter is the wimpelkette to check
    // refactor for new wimpelketten
    public int[] getQuality() {
        // store all colors of current wimpelkette and use that to generate the quality,
        // so we are independent of additional input
        colorlist = new ArrayList<Character>();

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
        quality[1] = frequency;
        return quality;
    }

    // prints the quality of the current wimpelkette
    public void printQuality() {
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