package oop24;

public class Signature {
    
    private KeyPair keyPair;
    private Filemanager filemanager;
    private long checksum;
    private String message;

    public Signature(KeyPair keyPair, Filemanager filemanger) {

        //TODO
        this.filemanager = filemanager;
        this.keyPair = keyPair;
        this.message = filemanger.read();
    }

    public String getMessage() {

        return this.message;
    }

    public long getChecksum() {
        
        return this.checksum;
    }
    
    // vielleicht simpler als SHA-256, aber sollte auch gehen, oder?
    public void generateChecksum() {

        byte[] bytes = message.getBytes();
        long checksum = 0;
        for (byte b : bytes) {
            checksum ^= b;
        }
        this.checksum = checksum;
    }

    public long signMessage() {

        long privateKey = keyPair.getPrivateKey();
        long generatorNumber = keyPair.getGeneratorNumber();

        return powMod(this.checksum, privateKey, generatorNumber);
    }

    public boolean verifySignature(long signature) {

        long publicKey = keyPair.getPublicKey();
        long generatorNumber = keyPair.getGeneratorNumber();

        long decryptedChecksum = powMod(signature, publicKey, generatorNumber);

        return decryptedChecksum == this.checksum;
    }

    private long powMod(long base, long exponent, long modulus) {

        if(modulus == 1) return 0;

        long result = 1;

        while (exponent > 0) {
            if (exponent % 2 == 1) {
                result = (result * base) % modulus;
            }
            exponent = exponent >> 1;
            base = (base * base) % modulus;
        }
        return result;
    }
}