package oop24;

/**
 * This class creates and calls the generation of the Wimpelkette
 *
 * @author Peter Krahl, Patrick Folie
 * @version 1.0
 */
public class Ergebnis {

    private final int minDistanz;
    private final int minFrequenz;
    private final String kette;

    /**
     * Constructor for the Ergebnis class
     * 
     * @param minDistanz    int
     * @param minFrequenz   int
     * @param optimaleKette String
     **/
    public Ergebnis(int minDistanz, int minFrequenz, String optimaleKette) {

        this.minDistanz = minDistanz;
        this.minFrequenz = minFrequenz;
        this.kette = optimaleKette;
    }

    /**
     * This method overrides the default toString method ov every object to properly
     * print the Object's data
     * <p>
     * Usage:
     * <p>
     * <code>
     * toString()
     * the method gets automatically called when you print an Ergebniss object
     * </code>
     *
     * @return String, String representation of the Wimpelkette
     **/
    @Override
    public String toString() {
        return "Kette: (" + this.kette + ") mit Qualitaet (" + this.minDistanz + "," + this.minFrequenz + ")";
    }

    /**
     * This method returns the minimal Distance of the quality criteria
     * <p>
     * Usage:
     * <p>
     * <code>
     * getMinDistanz()
     * </code>
     *
     * @return int, minimal distance of the result Wimpelkette
     **/
    public int getMinDistanz() {

        return this.minDistanz;
    }

    /**
     * This method returns the privatekey portion of the key without the
     * <p>
     * Usage:
     * <p>
     * <code>
     * getMinFrequenz()
     * </code>
     *
     * @return int, frequency of the minimal distance in the Wimpelkette
     **/
    public int getMinFrequenz() {

        return this.minFrequenz;
    }

    /**
     * This method returns the Wimpelkette as a String
     * <p>
     * Usage:
     * <p>
     * <code>
     * getKette()
     * </code>
     *
     * @return String, returns the Wimpelkette as a String of the colors as letters
     **/
    public String getKette() {

        return this.kette;
    }

    /**
     * This method compares two Wimpelketten and calculates which one has the better
     * quality
     * <p>
     * Usage:
     * <p>
     * <code>
     * istBesser()
     * </code>
     * 
     * @param vergleichsErgebnis Ergebnis
     * @return boolean, if the compared Wimpelkette is better
     **/
    public boolean istBesser(Ergebnis vergleichsErgebnis) {

        return this.minDistanz > vergleichsErgebnis.minDistanz ||
                (this.minDistanz == vergleichsErgebnis.minDistanz &&
                        this.minFrequenz < vergleichsErgebnis.minFrequenz);
    }

    /**
     * This method checks if two Wimpelketten are equal
     * <p>
     * Usage:
     * <p>
     * <code>
     * istGleich()
     * </code>
     * 
     * @param vergleichsErgebnis Ergebnis
     * @return boolean, if the compared Wimpelketten are of equal quality
     **/
    public boolean istGleich(Ergebnis vergleichsErgebnis) {

        return this.minDistanz == vergleichsErgebnis.minDistanz &&
                this.minFrequenz == vergleichsErgebnis.minFrequenz;
    }
}
