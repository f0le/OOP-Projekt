package oop24;

public class Key {

    private int privateKey;
    private int publicKey;
    private int generatorNumber;
    private int generatorFunction;

    public int getPrivateKey() {
        
        return this.privateKey;
    }

    public int getPublicKey() {

        return this.publicKey;
    }
    
    public int getGeneratorNumber() {

        return this.generatorNumber;
    }

    private void calculateGeneratorNumber(int prime1, int prime2) {

        //TODO
    }

    private void calculateGeneratorFunktion(int prime1, int prime2) {

        //TODO
    }

    private void generatePublicKey() {

        //TODO
    }

    private void generatePrivateKey() {

        //TODO
    }
}
