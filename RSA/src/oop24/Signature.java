package oop24;

public class Signature {
    
    private Key key;
    private int checksum;
    private String message;

    public Signature(Key key) {

        //TODO
        this.key = key;
    }

    public void setMessage(String message) {

        this.message = message;
    }

    public String getMessage() {

        return this.message;
    }

    private void generateChecksum() {

        //TODO
        //this.checksum = checksum;
    }

    //TODO                                                                       // [IMPLEMENTIERUNG FEHLT TLW.]
}