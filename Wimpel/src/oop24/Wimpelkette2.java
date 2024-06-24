import java.util.ArrayList;

import java.util.HashMap;

import java.util.LinkedList;

import java.util.List;



//finale version

public class Wimpelkette {

    private Qualitaet qualitaet;

    private List<WimpelFarbe> wimpelListe = new ArrayList<>();

    private HashMap<WimpelFarbe, Integer> wimpelFarbeListe = new
HashMap<>();



    public class Qualitaet {

        public double minimalAbstand;

        public int haeufigkeit;

        public int anzahlGleichGuteOptionen;



        public boolean besserAls(Qualitaet other) {

            if (this.minimalAbstand > other.minimalAbstand){

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



        public boolean gleichGut(Qualitaet other) {

            return this.minimalAbstand == other.minimalAbstand &&
this.haeufigkeit == other.haeufigkeit;

        }

    }



    public enum WimpelFarbe {

        ROT, BLAU, WEISS, SCHWARZ, GELB;

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



    public static Wimpelkette bestmoeglicheKette(HashMap<WimpelFarbe,
Integer> restWimpel){  

        return bestmoeglicheKette(new Wimpelkette(), restWimpel);

    }



    public static Wimpelkette bestmoeglicheKette(Wimpelkette kette,
HashMap<WimpelFarbe, Integer> restWimpel) {

        if (restWimpel.isEmpty()) {

            kette.berechneQualitaet();

            return kette;

        }

        List<Wimpelkette> optionenAnKetten = new LinkedList<>();

        for (WimpelFarbe farbe : restWimpel.keySet()){

            Wimpelkette neueKette = addFarbeToKette(kette, farbe);

            HashMap<WimpelFarbe, Integer> neueRestWimpel =
removeFarbeFromRest(restWimpel, farbe);

            optionenAnKetten.add(bestmoeglicheKette(neueKette,
neueRestWimpel));

        }

        sortiereNachQualitaet(optionenAnKetten);

        Wimpelkette besteKette = optionenAnKetten.get(0);

        Qualitaet besteQualitaet = besteKette.qualitaet;

        int anzahlGleichGuteOptionen = 0;

        for (Wimpelkette option : optionenAnKetten){

            if(option.qualitaet.gleichGut(besteQualitaet)) {

                anzahlGleichGuteOptionen +=
option.qualitaet.anzahlGleichGuteOptionen;

            }

        }

        besteKette.qualitaet.anzahlGleichGuteOptionen =
anzahlGleichGuteOptionen;

        return besteKette;

    }



    private static void sortiereNachQualitaet(List<Wimpelkette>
optionenAnKetten) {

        optionenAnKetten.sort((wimpelkette, t1) -> {

            if (wimpelkette.qualitaet.besserAls(t1.qualitaet)) {

                return -1;

            } else {

                return 1;

            }

        });

    }



    private static Wimpelkette addFarbeToKette(Wimpelkette kette,
WimpelFarbe farbe) {

        Wimpelkette neueKette = new Wimpelkette(kette);

        neueKette.wimpelListe.add(farbe);

        return neueKette;

    }



    private static HashMap<WimpelFarbe, Integer>
removeFarbeFromRest(HashMap<WimpelFarbe, Integer> restWimpel, WimpelFarbe
farbe) {

        HashMap<WimpelFarbe, Integer> neueRestWimpel = new
HashMap(restWimpel);

        if(neueRestWimpel.get(farbe) > 1) {

            neueRestWimpel.put(farbe, neueRestWimpel.get(farbe) - 1);

        } else {

            neueRestWimpel.remove(farbe);

        }

        return neueRestWimpel;

    }



    private void berechneQualitaet() {

        qualitaet = new Qualitaet();

        qualitaet.anzahlGleichGuteOptionen = 1;

        qualitaet.minimalAbstand = Double.POSITIVE_INFINITY;

        for (WimpelFarbe farbe : WimpelFarbe.values()){

            qualitaet.minimalAbstand = Double.min(qualitaet.minimalAbstand,
getMinAbstand(farbe));

        }

        qualitaet.haeufigkeit = 0;

        for (WimpelFarbe farbe : WimpelFarbe.values()){

            qualitaet.haeufigkeit += zaehleMinAbstand(farbe,
qualitaet.minimalAbstand);

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

                if(minAbstand == abstand){

                    anzahl++;

                };

                lastPosition = position;

            }

            position++;

        }

        return anzahl;

    }



    public void aufhaengen(){

        System.out.print("---");

        for(WimpelFarbe farbe : wimpelListe){

            System.out.print(farbe.name().charAt(0));

        }

        System.out.println("---");

    }





    public static void main(String[] args) {

        //test1();

        //test2();

        //test3();

        run3();

        //run2();

        //run1();

    }



    private static void run3() {

        HashMap<WimpelFarbe, Integer> verfuegbareWimpel = new HashMap<>();

        verfuegbareWimpel.put(WimpelFarbe.ROT, 3);

        verfuegbareWimpel.put(WimpelFarbe.BLAU, 3);

        verfuegbareWimpel.put(WimpelFarbe.WEISS, 3);

        Wimpelkette besteKette = bestmoeglicheKette(verfuegbareWimpel);

        besteKette.aufhaengen();

        System.out.println("Qualität("+ besteKette.qualitaet.minimalAbstand
+ ", " + besteKette.qualitaet.haeufigkeit + ")");

        System.out.println("Ketten mit gleich guter Qualität:"+
besteKette.qualitaet.anzahlGleichGuteOptionen);

    }



    private static void run2() {

        HashMap<WimpelFarbe, Integer> verfuegbareWimpel = new HashMap<>();

       verfuegbareWimpel.put(WimpelFarbe.ROT, 1);

        verfuegbareWimpel.put(WimpelFarbe.BLAU, 2);

        verfuegbareWimpel.put(WimpelFarbe.WEISS, 3);

        verfuegbareWimpel.put(WimpelFarbe.SCHWARZ, 4);

        Wimpelkette besteKette = bestmoeglicheKette(verfuegbareWimpel);

        besteKette.aufhaengen();

        System.out.println("Qualität("+ besteKette.qualitaet.minimalAbstand
+ ", " + besteKette.qualitaet.haeufigkeit + ")");

        System.out.println("Ketten mit gleich guter Qualität:"+
besteKette.qualitaet.anzahlGleichGuteOptionen);

    }



    private static void run1() {

        HashMap<WimpelFarbe, Integer> verfuegbareWimpel = new HashMap<>();

        verfuegbareWimpel.put(WimpelFarbe.ROT, 4);

        verfuegbareWimpel.put(WimpelFarbe.BLAU, 3);

        verfuegbareWimpel.put(WimpelFarbe.WEISS, 4);

        verfuegbareWimpel.put(WimpelFarbe.SCHWARZ, 2);

        verfuegbareWimpel.put(WimpelFarbe.GELB, 2);

        Wimpelkette besteKette = bestmoeglicheKette(verfuegbareWimpel);

        besteKette.aufhaengen();

        System.out.println("Qualität("+ besteKette.qualitaet.minimalAbstand
+ ", " + besteKette.qualitaet.haeufigkeit + ")");

        System.out.println("Ketten mit gleich guter Qualität:"+
besteKette.qualitaet.anzahlGleichGuteOptionen);

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

        System.out.println("Qualität("+ testKette.qualitaet.minimalAbstand
+ ", " + testKette.qualitaet.haeufigkeit + ")");

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

        System.out.println("Qualität("+ testKette.qualitaet.minimalAbstand
+ ", " + testKette.qualitaet.haeufigkeit + ")");

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

        System.out.println("Qualität("+ testKette.qualitaet.minimalAbstand
+ ", " + testKette.qualitaet.haeufigkeit + ")");

    }

}

