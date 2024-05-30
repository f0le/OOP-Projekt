package oop24;

/**
 * This class implements the RSA Keygeneration and helper functions.
 *
 * @author Patrick Folie
 * @version 1.0
 */

public class KeyPair {

    private long privatekey;
    private long publickey;
    private long generatorNumber;
    private long generatorFunction;

    // constructor
    public KeyPair(int prime1, int prime2) {

        this.generatorFunction = (prime1 - 1) * (prime2 - 1);
        this.generatorNumber = prime1 * prime2;
        this.generateKeyPair();
    }


    /**
     * This method returns the privatekey portion of the key without the generatornumber
     * <p>
     * Usage:
     * <p>
     * <code>
     * get_PrivateKey()
     * </code>
     *
     * @return long, value of the private key portion
     **/
    public long getPrivateKey() {

        return this.privatekey;
    }

    /**
     * This method returns the publickey portion of the key without the generatornumber
     * <p>
     * Usage:
     * <p>
     * <code>
     * getPublicKey()
     * </code>
     *
     * @return long, value of the public key portion
     **/
    public long getPublicKey() {

        return this.publickey;
    }

    /**
     * This method calculates the generatornumber
     * <p>
     * Usage:
     * <p>
     * <code>
     * getGeneratorNumber()
     * </code>
     *
     * @return long, value of the generatornumber
     **/
    public long getGeneratorNumber() {
        return this.generatorNumber;
    }

    public void generateKeyPair() {

        long fermatPrimes[] = { 3, 5, 17, 257, 65537 };
        this.publickey = 0;

        for(long fermatPrime : fermatPrimes) {
            if (
                fermatPrime < this.generatorFunction &&
                this.generatorFunction % fermatPrime > 0
            ) {
                this.publickey = fermatPrime;
                break;
            }
        }

        // to properly implement the exercise we need to implement a sieve and then
        // check each calculated prime for coprimality, as there is a possibility that
        // all fermat_primes are not coprime to a calculated phi(g)

        // PublicKey
        // Choose e from fermat_primes

        if (this.publickey == 0) {
            throw new ArithmeticException(
                "Error couldn't find proper value for e"
            );

        }

        /*  PrivateKey
            we need to find the solution for
            e*d = 1 mod phi(g)
        */

        // seed with generatorFunction
        long old_r = this.generatorFunction;
        // bezouts coefficients
        long old_s = 1;
        long old_t = 0;
        // set to e
        long r = this.publickey;
        // will be the quotients of the gcd
        long s = 0;
        long t = 1;
        // stores the current quotient, (calculates gcd, will be 1 at the end)
        long quotient;
        // helper variable needed for the calculation
        long tmp;

        //only r needed?

        // we know that the gcd is 1 so we can abort when the remainder is 1
        while (r != 1) {

            quotient = old_r / r;

            tmp = r;
            r = old_r - quotient * r;
            old_r = tmp;
            System.out.println("old_r: " + old_r + " ,r: " + r );

            tmp = s;
            s = old_s - quotient * s;
            old_s = tmp;
            System.out.println("old_s: " + old_r + " ,s: " + r );

            tmp = t;
            t = old_t - quotient * t;
            old_t = tmp;
            System.out.println("old_t: " + old_r + " ,t: " + r );
        }

        // t may be negative, so to get a correct solution we need to invert the number
        if (t < 0) {
            t *= -1;
        }

        this.privatekey = this.generatorFunction - t;
    }
}
