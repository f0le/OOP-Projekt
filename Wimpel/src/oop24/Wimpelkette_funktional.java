import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

//finale version
public class Wimpelkette2 {
    private Qualitaet qualitaet;
    private List<WimpelFarbe> wimpelListe = new ArrayList<>();
    private HashMap<WimpelFarbe, Integer> wimpelFarbeListe = new HashMap<>();

    public class Qualitaet {
        public double minimalAbstand;
        public int haeufigkeit;

        public boolean besserAls(Qualitaet other) {
            if (this.minimalAbstand > other.minimalAbstand) {
                return true;
            } else if (this.minimalAbstand < other.minimalAbstand) {
                return false;
            } else {
                if (this.haeufigkeit < other.haeufigkeit) {
                    return true;
                } else {
                    return false;
                }
            }
        }
    }

    public enum WimpelFarbe {
        ROT, BLAU, WEISS, SCHWARZ, GELB;
    }

    public Wimpelkette2() {
        this.wimpelListe = new ArrayList<>();
    }

    public Wimpelkette2(List<WimpelFarbe> wimpelListe) {
        this.wimpelListe = new ArrayList<>(wimpelListe);
    }

    public Wimpelkette2(Wimpelkette2 wimpelkette) {
        this(wimpelkette.wimpelListe);
    }

    public static Wimpelkette2 bestmoeglicheKette(HashMap<WimpelFarbe, Integer> restWimpel) {
        return bestmoeglicheKette(new Wimpelkette2(), restWimpel);
    }

    public static Wimpelkette2 bestmoeglicheKette(Wimpelkette2 kette, HashMap<WimpelFarbe, Integer> restWimpel) {
        if (restWimpel.isEmpty()) {
            kette.berechneQualitaet();
            return kette;
        }
        List<Wimpelkette2> optionenAnKetten = new LinkedList<>();
        for (WimpelFarbe farbe : restWimpel.keySet()) {
            Wimpelkette2 neueKette = addFarbeToKette(kette, farbe);
            HashMap<WimpelFarbe, Integer> neueRestWimpel = removeFarbeFromRest(restWimpel, farbe);
            optionenAnKetten.add(bestmoeglicheKette(neueKette, neueRestWimpel));
        }
        optionenAnKetten.sort((wimpelkette, t1) -> {
            if (wimpelkette.qualitaet.besserAls(t1.qualitaet)) {
                return -1;
            } else {
                return 1;
            }
        });
        return optionenAnKetten.get(0);
    }

    private static Wimpelkette2 addFarbeToKette(Wimpelkette2 kette, WimpelFarbe farbe) {
        Wimpelkette2 neueKette = new Wimpelkette2(kette);
        neueKette.wimpelListe.add(farbe);
        return neueKette;
    }

    private static HashMap<WimpelFarbe, Integer> removeFarbeFromRest(HashMap<WimpelFarbe, Integer> restWimpel,
            WimpelFarbe farbe) {
        HashMap<WimpelFarbe, Integer> neueRestWimpel = new HashMap(restWimpel);
        if (neueRestWimpel.get(farbe) > 1) {
            neueRestWimpel.put(farbe, neueRestWimpel.get(farbe) - 1);
        } else {
            neueRestWimpel.remove(farbe);
        }
        return neueRestWimpel;
    }

    private void berechneQualitaet() {
        qualitaet = new Qualitaet();
        qualitaet.minimalAbstand = Double.POSITIVE_INFINITY;
        for (WimpelFarbe farbe : WimpelFarbe.values()) {
            qualitaet.minimalAbstand = Double.min(qualitaet.minimalAbstand, getMinAbstand(farbe));
        }
        qualitaet.haeufigkeit = 0;
        for (WimpelFarbe farbe : WimpelFarbe.values()) {
            qualitaet.haeufigkeit += zaehleMinAbstand(farbe, qualitaet.minimalAbstand);
        }
    }

    private double getMinAbstand(WimpelFarbe farbe) {
        double minAbstand = Double.POSITIVE_INFINITY;
        double lastPosition = Double.NEGATIVE_INFINITY;
        double position = 0.0;
        for (WimpelFarbe wimpel : wimpelListe) {
            if (farbe == wimpel) {
                double abstand = position - lastPosition - 1;
                minAbstand = Double.min(minAbstand, abstand);
                lastPosition = position;
            }
            position++;
        }
        return minAbstand;
    }

    private int zaehleMinAbstand(WimpelFarbe farbe, double minAbstand) {
        double lastPosition = Double.NEGATIVE_INFINITY;
        double position = 0.0;
        int anzahl = 0;
        for (WimpelFarbe wimpel : wimpelListe) {
            if (farbe == wimpel) {
                double abstand = position - lastPosition - 1;
                if (minAbstand == abstand) {
                    anzahl++;
                }
                ;
                lastPosition = position;
            }
            position++;
        }
        return anzahl;
    }

    public void aufhaengen() {
        System.out.print("---");
        for (WimpelFarbe farbe : wimpelListe) {
            System.out.print(farbe.name().charAt(0));
        }
        System.out.println("---");
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