package oop24;

import java.util.ArrayList;

/**
 * This class implements the RSA Keygeneration and helper functions.
 *
 * @author Patrick Folie, Peter Krahl
 * @version 1.0
 */

public class KeyPair {

    private long privatekey;
    private long publickey = 0;
    private long generatorNumber;
    private long generatorFunction;
    private ArrayList<Long> primes = new ArrayList<Long>();
    private long[] seedPrimes;
    private boolean testedNumber = false;

    /**
     * Constructor for the KeyPair class
     * needs two primes to generate the KeyPair
     * 
     * @param int prime1
     * @param int prime2
     *
     **/
    public KeyPair(int prime1, int prime2) {

        this.generatorFunction = (prime1 - 1) * (prime2 - 1);
        this.generatorNumber = prime1 * prime2;
        this.seedSieve();
        this.generateKeyPair();
    }

    /**
     * This method returns the privatekey portion of the key without the
     * generatornumber
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
     * This method returns the publickey portion of the key without the
     * generatornumber
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

    /**
     * This method seeds the sieve with the first 200 primes for faster key
     * generation
     * <p>
     * Usage:
     * <p>
     * <code>
     * seedSieve()
     * @return void, no return value
     * </code>
     *
     **/
    private void seedSieve() {

        // seed the sieve with the first 200 prime numbers
        long seedPrimes[] = { 2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83,
                89, 97, 101, 103, 107, 109, 113, 127, 131, 137, 139, 149, 151, 157, 163, 167, 173, 179, 181, 191, 193,
                197, 199, 211, 223, 227, 229, 233, 239, 241, 251, 257, 263, 269, 271, 277, 281, 283, 293, 307, 311, 313,
                317, 331,
                337, 347, 349, 353, 359, 367, 373, 379, 383, 389, 397, 401, 409, 419, 421, 431, 433, 439, 443, 449, 457,
                461, 463, 467, 479, 487, 491, 499, 503, 509, 521, 523, 541, 547, 557, 563, 569, 571, 577, 587, 593, 599,
                601, 607, 613, 617, 619, 631, 641, 643, 647, 653, 659, 661, 673, 677, 683, 691, 701, 709, 719, 727, 733,
                739, 743, 751, 757, 761, 769, 773, 787, 797, 809, 811, 821, 823, 827, 829, 839, 853, 857, 859, 863, 877,
                881, 883, 887, 907, 911, 919, 929, 937, 941, 947, 953, 967, 971, 977, 983, 991, 997, 1009, 1013, 1019,
                1021, 1031, 1033, 1039, 1049, 1051, 1061, 1063, 1069, 1087, 1091, 1093, 1097, 1103, 1109, 1117, 1123,
                1129, 1151, 1153, 1163, 1171, 1181, 1187, 1193, 1201, 1213, 1217, 1223 };

        for (long seedPrime : seedPrimes) {
            primes.add(seedPrime);
        }
    }

    /**
     * This method calculates the public and private key portion of the KeyPair
     * <p>
     * Usage:
     * <p>
     * <code>
     * generateKeyPair()
     * @return void, no return value
     * </code>
     *
     **/
    private void generateKeyPair() {

        // check if a prime of the first 200 primes works
        for (long prime : primes) {
            if (prime < this.generatorFunction && this.generatorFunction % prime > 0) {
                this.publickey = prime;
                break;
            }
        }
        // checks if a prime of the sieve is already a good fit for e,
        // otherwise check all following numbers if they pass the requirements
        // aborts before the tested value overflows
        if (this.publickey == 0) {
            // aborts before the tested value overflows
            for (long possiblePrime = seedPrimes[199]; possiblePrime < Long.MAX_VALUE; possiblePrime++) {
                for (long prime : primes) {
                    // check if the value is a prime
                    // this is wrong?
                    if (possiblePrime % prime > 0) {
                        testedNumber = true;
                    } else {
                        testedNumber = false;
                        break;
                    }
                }
                if (testedNumber == true) {
                    // if the tested number is prime, add it to the
                    // sieve to improve the subsequent calculation
                    primes.add(possiblePrime);
                }
                // check tested number for coprimality with the generator function
                if (possiblePrime < this.generatorFunction && this.generatorFunction % possiblePrime > 0) {
                    this.publickey = possiblePrime;
                    break;
                }
            }
        }
        // it may happen that we don't have a value before the above loop overflows
        if (this.publickey == 0)

        {
            throw new ArithmeticException(
                    "Error couldn't find proper value for e");
        }

        // PrivateKey
        // we need to find the solution for
        // e*d = 1 mod phi(g)
        // we use the extended euclidean algorithm
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

        // we know that the gcd is 1 so we can abort when the remainder is 1
        while (r != 1) {

            quotient = old_r / r;

            tmp = r;
            r = old_r - quotient * r;
            old_r = tmp;

            tmp = s;
            s = old_s - quotient * s;
            old_s = tmp;

            tmp = t;
            t = old_t - quotient * t;
            old_t = tmp;
        }

        // t may be negative, so to get a correct solution we need to invert the number
        if (t < 0) {
            t *= -1;
        }

        this.privatekey = this.generatorFunction - t;
    }

}
