package oop24;

public class KeyPair {

    private long privatekey;
    private long publickey;
    private long generatorNumber;
    private long generatorFunction;

    // constructor
    public KeyPair(int prime1, int prime2) {

        this.generatorFunction = (prime1 - 1) * (prime2 - 1);
        this.generatorNumber = prime1 * prime2;
    }

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

        // to properly implement the exercise we need to implement a sieve and then
        // check each calculated prime for coprimality, as there is a possibility that
        // all fermat_primes are not coprime to a calculated phi(g)

        // PublicKey
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
        if (t < 0) {
            t *= -1;
        }
        this.privatekey = this.generatorFunction - t;
    }
}
