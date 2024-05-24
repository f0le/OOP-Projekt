package oop24;

public class KeyPair {

    // keys include the generatornumber always, so we return an array with two
    // values
    private long privatekey;
    private long publickey;
    private long generatorNumber;
    private long generatorFunction;

    // constructor
    public KeyPair(int prime1, int prime2) {

        this.generatorFunction = (prime1 - 1) * (prime2 - 1);
        this.generatorNumber = prime1 * prime2;
    }

    // give a privateKey array back with number e and generatornumber g
    // same with publickey
    public long getPrivateKey() {

        return this.privatekey;
    }

    public long getPublicKey() {

        return this.publickey;
    }

    public long getGeneratorNumber() {
        return this.generatorNumber;
    }

    public void generateKeyPair() {

        // to properly implement the exercise we need to implement a sieve to check if
        // the gcd is a prime

        // PublickKey
        // Choose e from fermat_primes
        long fermat_primes[] = { 3, 5, 17, 257, 65537 };
        this.publickey = 0;
        for (int i = 0; i <= 4; i++) {
            if (fermat_primes[i] < this.generatorFunction && this.generatorFunction % fermat_primes[i] > 0) {
                this.publickey = fermat_primes[i];
                break;
            }
        }
        if (this.publickey == 0) {
            throw new ArithmeticException("Error couldn't find proper value for e");

        }
        // System.out.println(this.publickey);

        // PrivateKey
        // we need to find the solution for
        // e*d = 1 mod phi(g)
        long a = this.generatorFunction;
        long b = this.publickey;
        long oldR = a, r = b;
        long oldS = 1, s = 0;
        long oldT = 0, t = 1;

        // we know that the gcd is 1 so we can abort when the remainder is 1
        while (r != 1) {

            /*
             * System.out.println(this.publickey);
             * System.out.println(a);
             * System.out.println(r);
             * System.out.println(oldR);
             */
            var quotient = oldR / r;

            var rTemp = r;
            r = oldR - quotient * r;
            oldR = rTemp;

            var sTemp = s;
            s = oldS - quotient * s;
            oldS = sTemp;

            var tTemp = t;
            t = oldT - quotient * t;
            oldT = tTemp;
        }
        this.privatekey = oldS * a + oldT * b;
        System.out.println(oldS);
        System.out.println(oldT);
        System.out.println(oldR);
        System.out.println(s);
        System.out.println(t);
        System.out.println(this.privatekey);
    }

    /*
     * for reference:
     * 
     * euclids algorithm(recursive)
     * 
     * public int gcd(int a, int b) {
     * if (b == 0)
     * return a;
     * return gcd(b, a % b);
     * }
     * 
     * extended euclids algorithm
     * 
     * public static void extendedEuclidean(long a, long b) {
     * long oldR = a, r = b;
     * long oldS = 1, s = 0;
     * long oldT = 0, t = 1;
     * 
     * while (r != 0) {
     * var quotient = oldR / r;
     * 
     * var rTemp = r;
     * r = oldR - quotient * r;
     * oldR = rTemp;
     * 
     * var sTemp = s;
     * s = oldS - quotient * s;
     * oldS = sTemp;
     * 
     * var tTemp = t;
     * t = oldT - quotient * t;
     * oldT = tTemp;
     * }
     * 
     * System.out.printf("Coefficients: s:%s, t:%s%n", oldS, oldT);
     * System.out.printf("GCD:%s%n", oldR);
     * }
     * 
     */

}
