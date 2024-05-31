package oop24;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.zip.CRC32;

/**
 * Die Signature Klasse erstellt eine checksum, signiert und verifiziert
 * Signaturen.
 * 
 * @author Peter Krahl
 * @author Patrick Folie
 * @version 1.0
 */

public class Signature {
    
    private KeyPair keyPair;
    private Filemanager filemanager;
    private BigInteger checksum;
    private String message;

    /**
     * Kontrstuktor für die Signature Klasse.
     * Nimmt einen Filemanager und ein Schlüsselpaar.
     * @param keyPair Schlüsselpaar
     * @param filemanager filemanager
     */
    public Signature(KeyPair keyPair, Filemanager filemanger) {

        this.filemanager = filemanager;
        this.keyPair = keyPair;
        this.message = filemanger.read();
        generateChecksum();
    }

    /**
     * Gibt ursprüngliche Nachricht zurück.
     * @return Nachricht
     */
    public String getMessage() {

        return this.message;
    }

    /**
     * Gibt Prüfsumme zurück.
     * @return Prüfsumme
     */
    public BigInteger getChecksum() {
        
        return this.checksum;
    }
    
    /**
     * Generiert aus der Nachricht einen SHA-256 Hash und setzt das checksum
     * Feld der Klasse mit den ersten 8 Bytes des Hashes.
     */
    public void generateChecksum() {
        CRC32 crc = new CRC32();
        crc.update(this.message.getBytes(StandardCharsets.UTF_8));
        long checksumValue = crc.getValue();
        this.checksum = BigInteger.valueOf(checksumValue);

        // Debug-Ausgabe für Checksumme
        System.out.println("Checksum: " + this.checksum);
    }


    /**
     * Signiert eine Nachricht mittel privatem Schlüssel und Generatorzahl.
     * @return signierte Nachricht als long
     */
    public BigInteger signMessage() {

        BigInteger privateKey = BigInteger.valueOf(keyPair.getPrivateKey());
        BigInteger generatorNumber = BigInteger.valueOf(keyPair.getGeneratorNumber());

        //return this.checksum.modPow(privateKey, generatorNumber);
        return powMod(this.checksum, privateKey, generatorNumber);
    }

    /**
     * Verifiziert eine Nachricht mittels öffentlichem Schlüssel und der
     * Generatorzahl.
     * @param signature Signatur
     * @return Boolean ob mit Prüfsumme übereinstimmend
     */
    public boolean verifySignature(BigInteger signature) {

        BigInteger publicKey = BigInteger.valueOf(keyPair.getPublicKey());
        BigInteger generatorNumber = BigInteger.valueOf(keyPair.getGeneratorNumber());

        //BigInteger decryptedChecksum = signature.modPow(publicKey, generatorNumber);
        BigInteger decryptedChecksum = powMod(signature, publicKey, generatorNumber);

        //Debug
        System.out.println("Decrypted Checksum: " + decryptedChecksum);
        System.out.println("Original Checksum: " + checksum);

        return decryptedChecksum.equals(this.checksum);
    }

    /**
     * Exponentiert den Modulus einer Zahl. (nicht mehr benötigt)
     * @param base Basis
     * @param exponend Exponent
     * @param modulo Modulus
     * @return Ergebnis der Modulus Exponentiation
     */

    private BigInteger powMod(BigInteger base, BigInteger exponent, BigInteger modulo) {
    
        if (modulo.equals(BigInteger.ONE)) return BigInteger.ZERO;
    
        BigInteger result = BigInteger.ONE;
        base = base.mod(modulo);
    
        while (exponent.compareTo(BigInteger.ZERO) > 0) {
            if (exponent.mod(BigInteger.TWO).equals(BigInteger.ONE)) {
                result = (result.multiply(base)).mod(modulo);
            }
            exponent = exponent.shiftRight(1);
            base = (base.multiply(base)).mod(modulo);
        }
        return result;
    }
}