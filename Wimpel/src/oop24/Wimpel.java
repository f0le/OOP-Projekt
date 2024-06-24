package oop24;

/**
 * This class implements the Wimpel for the Wimpelkette
 *
 * @author Peter Krahl, Patrick Folie
 * @version 1.0
 */
public class Wimpel {
    private char farbe;

    /**
     * Constructor for the KeyPair class
     * needs two primes to generate the KeyPair
     * 
     * @param farbe char, color of the Wimpel
     **/
    public Wimpel(char farbe) {
        this.farbe = farbe;
    }

    /**
     * This method returns the color of the Wimpel
     * <p>
     * Usage:
     * <p>
     * <code>
     * getFarbe()
     * </code>
     *
     * @return char, color of the Wimpel
     **/
    public char getFarbe() {
        return farbe;
    }
}
