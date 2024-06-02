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

        // Nachricht 1

        KeyPair keyNachricht1 = new KeyPair(997, 421);

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
            new Filemanager("RSA\\extern\\Zusatzmaterial 3\\test1.txt")
        );


        BigInteger encryptedNachricht1 = nachricht1.signMessage();
        System.out.println("Nachricht: " + nachricht1.getMessage());
        System.out.println("Signatur: " + encryptedNachricht1);
        System.out.println("Überprüfung mit Prüfsumme: " + nachricht1.verifySignature(encryptedNachricht1));
        System.out.println("Überprüfung mit falscher Prüfsumme: " + nachricht1.verifySignature(BigInteger.valueOf(235480)));


        System.out.println('\n' + "[###################]" + '\n');
        // Nachricht 2

        KeyPair keyNachricht2 = new KeyPair(1103, 439);

        System.out.println(
            "Publickey: {" +
            keyNachricht2.getPublicKey() +
            "," +
            keyNachricht2.getGeneratorNumber() +
            "}"
        );

        System.out.println(
            "Privatekey : {" +
            keyNachricht2.getPrivateKey() +
            "," +
            keyNachricht2.getGeneratorNumber() +
            "}"
        );

        Signature nachricht2 = new Signature(
            keyNachricht2,
            new Filemanager("RSA\\extern\\Zusatzmaterial 3\\test2.txt")
        );


        BigInteger encryptedNachricht2 = nachricht2.signMessage();
        System.out.println("Nachricht: " + nachricht2.getMessage());
        System.out.println("Signatur: " + encryptedNachricht2);
        System.out.println("Überprüfung mit Prüfsumme: " + nachricht2.verifySignature(encryptedNachricht2));
        System.out.println("Überprüfung mit falscher Prüfsumme: " + nachricht2.verifySignature(BigInteger.valueOf(235480)));

    }
}