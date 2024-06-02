package oop24;

import java.math.BigInteger;

public class Main {

    /**
     * Main Klasse für die Testen der Signatur.
     * 
     * @author Peter Krahl
     * @author Patrick Folie
     * @version 1.0
     */
    public static void main(String[] args) {

        KeyPair keyNachricht1 = new KeyPair(47573, 59303);

        System.out.println(
            "Publickey: {" +
            keyNachricht1.getPublicKey() +
            "," +
            keyNachricht1.getGeneratorNumber() +
            "}"
        );

        System.out.println(
            "Privatekey : {" +
            keyNachricht1.getPrivateKey() +
            "," +
            keyNachricht1.getGeneratorNumber() +
            "}"
        );

        Signature nachricht1 = new Signature(
            keyNachricht1,
            new Filemanager("RSA\\extern\\Zusatzmaterial 3\\Nachricht1.txt")
        );


        BigInteger encryptedNachricht1 = nachricht1.signMessage();
        System.out.println("Nachricht: " + nachricht1.getMessage());
        System.out.println("Signatur: " + encryptedNachricht1);
        System.out.println("Überprüfung mit Prüfsumme: " + nachricht1.verifySignature(encryptedNachricht1));
        System.out.println("Überprüfung mit alternativer Prüfsumme: " + nachricht1.verifySignature(BigInteger.valueOf(235480)));

    }
}