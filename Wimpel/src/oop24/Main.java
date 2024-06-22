package oop24;

public class Main {

    public static void main(String[] args) {

        // zu dem Beispiel noch zwei weitere optimale wimpelketten mit anderer belegung
        // erzeugen

        char[] wimpelfarbe = new char[] { 'r', 'b', 'g', 'w', 's' };
        int[] wimpelanzahl = new int[] { 4, 3, 2, 4, 2 };

        Wimpelkette wimpelkette = new Wimpelkette(wimpelanzahl, wimpelfarbe);
        wimpelkette.generiereStandardKette(wimpelanzahl, wimpelfarbe);
        wimpelkette.printWimpelkette();
        wimpelkette.getQuality(wimpelfarbe);
    }
}
