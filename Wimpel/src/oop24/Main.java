package oop24;

public class Main {
    /*
     * public static void main(String[] args) {
     * 
     * char[] wimpelfarbe = new char[] { 'r', 'b', 'g', 'w', 's' };
     * int[] wimpelanzahl = new int[] { 4, 3, 2, 4, 2 };
     * 
     * char[] wimpelfarbe1 = new char[] { 'r', 'b', 'g' };
     * int[] wimpelanzahl1 = new int[] { 4, 3, 2 };
     * 
     * char[] wimpelfarbe2 = new char[] { 'r', 'b' };
     * int[] wimpelanzahl2 = new int[] { 2, 1 };
     * 
     * char[] wimpelfarbe3 = new char[] { 'r', 'b', 'g', 's' };
     * int[] wimpelanzahl3 = new int[] { 4, 3, 2, 2 };
     * 
     * Wimpelkette wimpelkettezwei = new Wimpelkette(wimpelanzahl3, wimpelfarbe3);
     * Ergebnis ergebnis = wimpelkettezwei.generiereOptimaleKette();
     * 
     * System.out.println("Beste Kette: " + ergebnis.getKette());
     * System.out.println("Qualitaet: (" + ergebnis.getMinDistanz() + "," +
     * ergebnis.getMinFrequenz() + ")");
     * 
     * // Wimpelkette wimpelkette1 = new Wimpelkette(wimpelanzahl1, wimpelfarbe1);
     * // wimpelkette1.printWimpelkette();
     * // wimpelkette1.printQuality();
     * 
     * // Wimpelkette wimpelkette2 = new Wimpelkette(wimpelanzahl2, wimpelfarbe2);
     * // wimpelkette2.printWimpelkette();
     * // wimpelkette2.printQuality();
     * }
     */

    public enum WimpelFarbe {
        ROT, BLAU, WEISS, SCHWARZ, GELB;
    }

    public static void main(String[] args) {
        // test1();
        // test2();
        // test3();
        run3();
        run2();
        run1();
    }

    private static void run3() {
        HashMap<WimpelFarbe, Integer> verfuegbareWimpel = new HashMap<>();
        verfuegbareWimpel.put(WimpelFarbe.ROT, 3);
        verfuegbareWimpel.put(WimpelFarbe.BLAU, 3);
        verfuegbareWimpel.put(WimpelFarbe.WEISS, 3);
        Wimpelkette2 besteKette = bestmoeglicheKette(verfuegbareWimpel);
        besteKette.aufhaengen();
        System.out.println(
                "Qualität(" + besteKette.qualitaet.minimalAbstand + ", " + besteKette.qualitaet.haeufigkeit + ")");
    }

    private static void run2() {
        HashMap<WimpelFarbe, Integer> verfuegbareWimpel = new HashMap<>();
        verfuegbareWimpel.put(WimpelFarbe.ROT, 1);
        verfuegbareWimpel.put(WimpelFarbe.BLAU, 2);
        verfuegbareWimpel.put(WimpelFarbe.WEISS, 3);
        verfuegbareWimpel.put(WimpelFarbe.SCHWARZ, 4);
        Wimpelkette2 besteKette = bestmoeglicheKette(verfuegbareWimpel);
        besteKette.aufhaengen();
        System.out.println(
                "Qualität(" + besteKette.qualitaet.minimalAbstand + ", " + besteKette.qualitaet.haeufigkeit + ")");
    }

    private static void run1() {
        HashMap<WimpelFarbe, Integer> verfuegbareWimpel = new HashMap<>();
        verfuegbareWimpel.put(WimpelFarbe.ROT, 4);
        verfuegbareWimpel.put(WimpelFarbe.BLAU, 3);
        verfuegbareWimpel.put(WimpelFarbe.WEISS, 4);
        verfuegbareWimpel.put(WimpelFarbe.SCHWARZ, 2);
        verfuegbareWimpel.put(WimpelFarbe.GELB, 2);
        Wimpelkette2 besteKette = bestmoeglicheKette(verfuegbareWimpel);
        besteKette.aufhaengen();
        System.out.println(
                "Qualität(" + besteKette.qualitaet.minimalAbstand + ", " + besteKette.qualitaet.haeufigkeit + ")");
    }

    private static void test1() {
        List<WimpelFarbe> testWimpelListe = new ArrayList<>();
        testWimpelListe.add(WimpelFarbe.ROT);
        testWimpelListe.add(WimpelFarbe.ROT);
        testWimpelListe.add(WimpelFarbe.ROT);
        testWimpelListe.add(WimpelFarbe.ROT);
        Wimpelkette2 testKette = new Wimpelkette2(testWimpelListe);
        testKette.berechneQualitaet();
        System.out.print("Qualität(0.0,3)==");
        System.out.println(
                "Qualität(" + testKette.qualitaet.minimalAbstand + ", " + testKette.qualitaet.haeufigkeit + ")");
    }

    private static void test2() {
        List<WimpelFarbe> testWimpelListe = new ArrayList<>();
        testWimpelListe.add(WimpelFarbe.ROT);
        testWimpelListe.add(WimpelFarbe.ROT);
        testWimpelListe.add(WimpelFarbe.ROT);
        testWimpelListe.add(WimpelFarbe.ROT);
        testWimpelListe.add(WimpelFarbe.BLAU);
        testWimpelListe.add(WimpelFarbe.BLAU);
        testWimpelListe.add(WimpelFarbe.BLAU);
        Wimpelkette2 testKette = new Wimpelkette2(testWimpelListe);
        testKette.berechneQualitaet();
        System.out.print("Qualität(0.0,5)==");
        System.out.println(
                "Qualität(" + testKette.qualitaet.minimalAbstand + ", " + testKette.qualitaet.haeufigkeit + ")");
    }

    private static void test3() {
        List<WimpelFarbe> testWimpelListe = new ArrayList<>();
        testWimpelListe.add(WimpelFarbe.ROT);
        testWimpelListe.add(WimpelFarbe.BLAU);
        testWimpelListe.add(WimpelFarbe.ROT);
        testWimpelListe.add(WimpelFarbe.BLAU);
        testWimpelListe.add(WimpelFarbe.ROT);
        testWimpelListe.add(WimpelFarbe.BLAU);
        testWimpelListe.add(WimpelFarbe.ROT);
        testWimpelListe.add(WimpelFarbe.BLAU);
        testWimpelListe.add(WimpelFarbe.WEISS);
        testWimpelListe.add(WimpelFarbe.BLAU);
        testWimpelListe.add(WimpelFarbe.WEISS);
        testWimpelListe.add(WimpelFarbe.BLAU);
        Wimpelkette2 testKette = new Wimpelkette2(testWimpelListe);
        testKette.berechneQualitaet();
        System.out.print("Qualität(0.0,9)==");
        System.out.println(
                "Qualität(" + testKette.qualitaet.minimalAbstand + ", " + testKette.qualitaet.haeufigkeit + ")");
    }
}
