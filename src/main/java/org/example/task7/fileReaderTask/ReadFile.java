package org.example.task7.fileReaderTask;

import java.io.FileReader;
import java.io.IOException;

public class ReadFile {
    public static void main(String[] args) {
        try {
            readFile();
        } catch (IOException e) {
            throw new FileNotFoundCustomException("Файл не найден");
        }
    }

    public static void readFile() throws IOException {
        FileReader fileReader = new FileReader("data.txt");
        int character;
        while ((character = fileReader.read()) != -1) {
            System.out.println((char) character);
        }
    }
}
