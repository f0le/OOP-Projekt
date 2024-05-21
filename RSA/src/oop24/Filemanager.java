package oop24;

import java.io.*;
import java.nio.file.*;                                                     //für die Aufgabe

public class Filemanager {

    private String filePath;

    public Filemanager(String filePath) {

        this.filePath = filePath;
    }

    public String read() {

        try {

            return new String(Files.readAllBytes(Paths.get(filePath)));

        } catch (IOException e) {

            e.printStackTrace();
            return null;
        }
    }

    public void write(String content) {

        try {

            Files.write(Paths.get(filePath), content.getBytes());

        } catch (IOException e) {

            e.printStackTrace();
        }
    }
}
