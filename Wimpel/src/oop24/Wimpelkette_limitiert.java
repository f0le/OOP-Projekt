import java.util.ArrayList;
import java.util.List;

public class Wimpelkette {
    private Qualitaet qualitaet;
    private List<WimpelFarbe> wimpelListe = new ArrayList<>();

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
        ROT, BLAU, WEISS
    }

    public Wimpelkette() {
        this.wimpelListe = new ArrayList<>();
    }

    public Wimpelkette(List<WimpelFarbe> wimpelListe) {
        this.wimpelListe = new ArrayList<>(wimpelListe);
    }

    public Wimpelkette(Wimpelkette wimpelkette) {
        this(wimpelkette.wimpelListe);
    }

    public static Wimpelkette bestmoeglicheKette(int rest_rot, int rest_blau, int rest_weiss) {
        return bestmoeglicheKette(new Wimpelkette(), rest_rot, rest_blau, rest_weiss);
    }

    public static Wimpelkette bestmoeglicheKette(Wimpelkette kette, int rest_rot, int rest_blau, int rest_weiss) {
        if (rest_rot == 0 && rest_blau == 0 && rest_weiss == 0) {
            kette.berechneQualitaet();
            return kette;
        }
        Wimpelkette kette_plus_rot = null;
        Wimpelkette kette_plus_blau = null;
        Wimpelkette kette_plus_weiss = null;
        for (WimpelFarbe farbe : WimpelFarbe.values()) {
            if (farbe == WimpelFarbe.ROT && rest_rot > 0) {
                Wimpelkette neueKette = new Wimpelkette(kette);
                neueKette.wimpelListe.add(farbe);
                kette_plus_rot = bestmoeglicheKette(neueKette, rest_rot - 1, rest_blau, rest_weiss);
            }
            if (farbe == WimpelFarbe.BLAU && rest_blau > 0) {
                Wimpelkette neueKette = new Wimpelkette(kette);
                neueKette.wimpelListe.add(farbe);
                kette_plus_blau = bestmoeglicheKette(neueKette, rest_rot, rest_blau - 1, rest_weiss);
            }
            if (farbe == WimpelFarbe.WEISS && rest_weiss > 0) {
                Wimpelkette neueKette = new Wimpelkette(kette);
                neueKette.wimpelListe.add(farbe);
                kette_plus_weiss = bestmoeglicheKette(neueKette, rest_rot, rest_blau, rest_weiss - 1);
            }
        }
        if (kette_plus_rot != null && kette_plus_blau != null && kette_plus_weiss != null) {
            if (kette_plus_rot.qualitaet.besserAls(kette_plus_blau.qualitaet) &&
                    kette_plus_rot.qualitaet.besserAls(kette_plus_weiss.qualitaet)) {
                return kette_plus_rot;
            } else if (kette_plus_blau.qualitaet.besserAls(kette_plus_weiss.qualitaet)) {
                return kette_plus_blau;
            } else {
                return kette_plus_weiss;
            }
        } else if (kette_plus_blau != null && kette_plus_weiss != null) {
            if (kette_plus_blau.qualitaet.besserAls(kette_plus_weiss.qualitaet)) {
                return kette_plus_blau;
            } else {
                return kette_plus_weiss;
            }
        } else if (kette_plus_rot != null && kette_plus_weiss != null) {
            if (kette_plus_rot.qualitaet.besserAls(kette_plus_weiss.qualitaet)) {
                return kette_plus_rot;
            } else {
                return kette_plus_weiss;
            }
        } else if (kette_plus_blau != null && kette_plus_rot != null) {
            if (kette_plus_blau.qualitaet.besserAls(kette_plus_rot.qualitaet)) {
                return kette_plus_blau;
            } else {
                return kette_plus_rot;
            }
        } else if (kette_plus_rot != null) {
            return kette_plus_rot;
        } else if (kette_plus_blau != null) {
            return kette_plus_blau;
        } else if (kette_plus_weiss != null) {
            return kette_plus_weiss;
        }
        return null;
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
        Wimpelkette besteKette = bestmoeglicheKette(3, 5, 3);
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
        Wimpelkette testKette = new Wimpelkette(testWimpelListe);
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
        Wimpelkette testKette = new Wimpelkette(testWimpelListe);
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
        Wimpelkette testKette = new Wimpelkette(testWimpelListe);
        testKette.berechneQualitaet();
        System.out.print("Qualität(0.0,9)==");
        System.out.println(
                "Qualität(" + testKette.qualitaet.minimalAbstand + ", " + testKette.qualitaet.haeufigkeit + ")");
    }
}
