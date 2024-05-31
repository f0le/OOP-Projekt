package oop24;

import java.io.*;
import java.nio.file.*;                                                     //für die Aufgabe

/**
 * Die Filemanager Klasse handelt das lesen & schreiben von Dateien.
 * 
 * @author Peter Krahl
 * @author Patrick Folie
 * @version 1.0
 */

public class Filemanager {

    private String filePath;

    /**
     * Kontrstuktor für die Filemanager Klasse. Nimmt einen PFad zu der Datei.
     * @param filePath Dateipfad
     */
    public Filemanager(String filePath) {

        this.filePath = filePath;
    }

    /**
     * Liest Datei ein und gibt sie als String zurück.
     * @return Inhalt als String
     */
    public String read() {

        try {

            return new String(Files.readAllBytes(Paths.get(filePath)));

        } catch (IOException e) {

            e.printStackTrace();
            return null;
        }
    }

    /**
     * Nimmt einen String und schreibt ihn in eine Datei.
     * @param content
     */
    public void write(String content) {

        try {

            Files.write(Paths.get(filePath), content.getBytes());

        } catch (IOException e) {

            e.printStackTrace();
        }
    }
}
