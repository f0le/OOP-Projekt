package oop24;

public class Key {

    // keys include the generatornumber always, so we return an array with two
    // values
    private int privateKeyPair[];
    private int privateKey;
    private int publicKeyPair[];
    private int publicKey;
    private int generatorNumber;

    // this class needs to return the key on the screen?

    // use constructor to generate the keypair automatically when a new object
    // key is generated and return the key?
    public Key(int prime1, int prime2) {
    }

    // give a privatekey array back with number e and generatornumber g
    // same with publickey
    public int[] getPrivateKeyPair() {

        this.privatekeyPair[0] = this.privateKey;
        this.privatekeyPair[1] = this.getGeneratorNumber();
        return this.privateKeyPair;
    }

    public int[] getPublicKeyPair() {

        this.publickeyPair[0] = this.publicKey;
        this.publickeyPair[1] = this.getGeneratorNumber();
        return this.publicKeyPair;
    }

    public int getGeneratorNumber() {

        return this.generatorNumber;
    }

    private void calculateGeneratorNumber(int prime1, int prime2) {

        this.generatorNumber = prime1 * prime2;
    }

    private void calculateGeneratorFunction(int prime1, int prime2) {

        this.generatorFunction = (prime1 - 1) * (prime2 - 1);
    }

    private void generatePublicKey() {
        // 3 will always work
        // this.publickey = 3;
    }

    private void generatePrivateKey() {

        // use extended euclidian elgorithm
        // this.privatekey =
    }

}
