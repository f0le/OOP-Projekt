package oop24;

import java.math.BigInteger;

public class Main {

    public static void main(String[] args) {

        // [SETUP TEST]
        {
            System.out.println("setup test");
        }

        // [FILEMANAGER TEST]
        {
            Filemanager testFileManager = new Filemanager(
                    "RSA\\extern\\test.txt");

            testFileManager.write("This is test content.");

            String content = testFileManager.read();

            System.out.println(content);

        }

        // [KEYPAIR TEST]
        {
            KeyPair keyPair = new KeyPair(431, 19);

            System.out.println(
                    "Publickey: {" +
                            keyPair.getPublicKey() +
                            "," +
                            keyPair.getGeneratorNumber() +
                            "}");

            System.out.println(
                    "Privatekey : {" +
                            keyPair.getPrivateKey() +
                            "," +
                            keyPair.getGeneratorNumber() +
                            "}");

        }

        // [SIGNATURE TEST]
        {
            Signature testSignature = new Signature(
                    new KeyPair(431, 19),
                    new Filemanager(
                            "RSA\\extern\\test.txt"));

            long encryptedTestSignature = testSignature.signMessage();

            System.out.println(testSignature.getChecksum());
            System.out.println(encryptedTestSignature);
            System.out.println(testSignature.verifySignature(encryptedTestSignature));
            System.out.println(testSignature.verifySignature(12345));
        }
    }
}