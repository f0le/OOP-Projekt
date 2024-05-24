package oop24;

public class Main {

    public static void main(String[] args) {

        System.out.println("setup test"); // [TEST]

        Filemanager testFileManager = new Filemanager( // [FILEMANAGERTEST]
                "RSA\\extern\\test.txt"); // test.txt manuell erstellen

        testFileManager.write("This is test content.");
        KeyPair keyPair = new KeyPair(431, 19);
        keyPair.generateKeyPair();
        System.out.println("Publickey: {" + keyPair.getPublicKey() + "," + keyPair.getGeneratorNumber() + "}");
        System.out.println("Privatekey : {" + keyPair.getPrivateKey() + "," + keyPair.getGeneratorNumber() + "}");

        String content = testFileManager.read();

        System.out.println(content);
    }
}