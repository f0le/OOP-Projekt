package oop24;

public class Ergebnis {
    
    private final int minDistanz;
    private final int minFrequenz;
    private final String besteKette;

    public Ergebnis(int minDistanz, int minFrequenz, String optimaleKette) {

        this.minDistanz = minDistanz;
        this.minFrequenz = minFrequenz;
        this.besteKette = optimaleKette;
    }

    public int getMinDistanz() {
        
        return this.minDistanz;
    }

    public int getMinFrequenz() {

        return this.minFrequenz;
    }

    public String getBesteKette() {

        return this.besteKette;
    }

    public boolean istBesser(Ergebnis vergleichsErgebnis) {

        return  this.minDistanz > vergleichsErgebnis.minDistanz ||
                (this.minDistanz == vergleichsErgebnis.minDistanz &&
                this.minFrequenz < vergleichsErgebnis.minFrequenz);
    }
}
