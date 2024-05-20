package oop24;

import java.util.Random;

public class Key {

    private int privateKey;
    private int publicKey;
    private int generatorNumber;
    private int generatorFunction;
    private int primeNumbers[];

    // give a privatekey array back with number e and generatornumber g
    // same with publickey

    public int getPrivateKey() {

        return this.privateKey;
    }

    public int getPublicKey() {

        return this.publicKey;
    }

    public int getGeneratorNumber() {

        return this.generatorNumber;
    }

    public int getGeneratorFunction() {

        return this.generatorFunction;
    }

    private void calculateGeneratorNumber(int primeNumbers[]) {

        this.generatorNumber = primeNumbers[0] * primeNumbers[1];
    }

    private void calculateGeneratorFunction(int primeNumbers[]) {

        this.generatorFunction = (primeNumbers[0] - 1) * (primeNumbers[1] - 1);
    }

    private void generatePublicKey() {

        // TODO
    }

    private void generatePrivateKey() {

        // TODO
    }

    private int[] getPrimeNumber() {

        // generate prime nubmers with sieve, return array with two entries

        int[] PrimeNumbers;

        return this.PrimeNumbers;
    }
}
