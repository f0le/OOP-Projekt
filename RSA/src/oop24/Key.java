package oop24;

public class Key {

    // keys include the generatornumber always, so we return an array with two
    // values
    private long privateKeyPair[];
    private long privateKey;
    private long publicKeyPair[];
    private long publicKey;
    private long generatorNumber;
    private long generatorFunction;

    // this class needs to return the key on the screen?

    // use constructor to generate the keypair automatically when a new object
    // key is generated and return the key?
    /*
     * public Key(int prime1, int prime2) {
     * this.privateKey =
     * }
     */

    // give a privateKey array back with number e and generatornumber g
    // same with publickey
    public long[] getprivateKeyPair() {

        this.privateKeyPair[0] = this.privateKey;
        this.privateKeyPair[1] = this.getGeneratorNumber();
        return this.privateKeyPair;
    }

    public long[] getPublicKeyPair() {

        this.publicKeyPair[0] = this.publicKey;
        this.publicKeyPair[1] = this.getGeneratorNumber();
        return this.publicKeyPair;
    }

    public long getGeneratorNumber() {

        return this.generatorNumber;
    }

    private void calculateGeneratorNumber(int prime1, int prime2) {

        this.generatorNumber = prime1 * prime2;
    }

    private void calculateGeneratorFunction(int prime1, int prime2) {

        this.generatorFunction = (prime1 - 1) * (prime2 - 1);
    }

    private void generatePublicKey() {

        long fermat_primes[] = { 3, 5, 17, 257, 65537 };
        this.publicKey = 0;
        for (int i = 0; i <= 4; i++) {
            if (fermat_primes[i] < this.generatorNumber && this.generatorNumber % i != 0) {
                this.publicKey = fermat_primes[i];
                break;
            }
            if (fermat_primes[i] == 5 && this.publicKey == 0) {
                throw new ArithmeticException("Error couldn't find proper value for e");
            }
        }
    }

    private void generateprivateKey() {

        // we need to find the solution for
        // e*d = 1 mod phi(g)
        long a = this.generatorFunction;
        long b = this.publicKey;
        long oldR = a, r = b;
        long oldS = 1, s = 0;
        long oldT = 0, t = 1;

        // we know that the gcd is 1 so we can abort when the remainder is 1
        while (r != 1) {
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
        this.privateKey = oldS * a + oldT * b;

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
