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
        this.generateKeyPair();;
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

            long quotient = oldR / r;

            long temp = r;
            r = oldR - quotient * r;
            oldR = temp;
    
            temp = s;
            s = oldS - quotient * s;
            oldS = temp;
    
            temp = t;
            t = oldT - quotient * t;
            oldT = temp;
        }

        if (t < 0) {
            t *= -1;
        }
        
        this.privatekey = this.generatorFunction - t;
    }
}
