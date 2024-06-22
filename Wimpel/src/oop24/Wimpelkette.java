package oop24;

import java.util.ArrayList;

public class Wimpelkette {

    // declare array for the wimpels
    private char[] colorlist;
    private int[] countlist;
    // helper variable for calculating the quality of a wimpelkette
    private int distance;
    private int minDistance;
    private int frequency;
    // stores the quality of a wimpelkette, first element is the distance, second
    // the frequency of that distance
    private int[] quality = new int[2];
    private ArrayList<Wimpel> wimpelkette = new ArrayList<Wimpel>();

    public Wimpelkette(int[] count, char[] color) {

        // arrayelements have the corresponding number of colored wimpels
        this.countlist = count;
        this.colorlist = color;
        this.wimpelkette = new ArrayList<Wimpel>();
    }

    *

    private void generiereOptimaleKette(ArrayList<Wimpel> wimpelkette, int position, int[] count, char[] color) {
      
      // in der Theorie wollen wir hier ja immer unser (bisher) mines Ergebnis
      // übergeben und eine
      // Kondition haben, die abbricht, sobald wir kein bessere finden
      
      // einfach immer eine farbe mehr in die wimpelkette reinsortieren? und zwei
      // arrays mit farbe und anzahl der wimpel uebergeben und dann einfach immer
      //n-1
      // der arrays verarbeiten. Und die Rekursion ist zu Ende wenn die beiden
      //arrays
      // abgearbeitet wurden?
      // Von den Farben mit den meisten Vorkommen anfangen und dann die anderen
      //farben
      // so reinsortieren, dass die qualitaet besser wird? Oder einfach vorher
      // ueberlegt wie es am optimalsten reinvertielt wird?
      // das einfach ueber die position des iterators bei dem inkrementieren von
      //den
      // arrays machen?
      
      if (position > color.length) {
      return minErgebnis;
      }
      position++;
      generiereOptimaleKette(kette, position, minErgebnis, color, count);
      }

    // generate unoptimized standardchain
    public void generiereStandardKette(int[] count, char[] color) {
        for (int i = 0; i < color.length; i++) {
            for (int j = 0; j < count[i]; j++) {
                wimpelkette.add(new Wimpel(color[i]));
            }
        }
    }

    // calculates the quality of the current wimpelkette
    public void getQuality() {
        for (int i = 0; i < this.colorlist.length; i++) {
            frequency = 0;
            for (int j = 0; j < this.wimpelkette.size(); j++) {
                if (this.wimpelkette.get(j).getColor() == colorlist[i]) {
                    for (int k = j + 1; k < this.wimpelkette.size(); k++) {
                        // vergleich j!=k nicht notwendig
                        if (this.wimpelkette.get(k).getColor() == this.colorlist[i]
                                && this.wimpelkette.get(j) != this.wimpelkette.get(k)) {
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
        this.getQuality();
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