package org.example.task7.fileReaderTask;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ReadFile {
    public static void main(String[] args) throws IOException {
        readFile();
    }

    public static void readFile() {
        try {
            FileReader file = new FileReader("data.txt");
        } catch (FileNotFoundException e) {
            System.out.println("Файл не найден");
        }
    }

}
