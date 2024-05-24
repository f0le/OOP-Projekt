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

    public void setMessage(String message) {

        this.message = message;
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

    //TODO                                                                       // [IMPLEMENTIERUNG FEHLT TLW.]
}