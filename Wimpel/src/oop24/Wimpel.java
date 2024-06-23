package oop24;

public class Wimpel {

    private char color;
    private int count;

    public Wimpel(char color, int count) {
        this.color = color;
        this.count = count;
    }

    public char getColor() {
        return this.color;
    }

    public int getCount() {
        return this.count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
