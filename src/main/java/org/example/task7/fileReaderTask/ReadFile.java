package org.example.task7.fileReaderTask;

import java.io.FileReader;
import java.io.IOException;

public class ReadFile {
    public static void main(String[] args) {
        try {
            readFile();
        } catch (IOException e) {
            throw new FileNotFoundCustomException("НЕ НАЙДЕН ФАЙЛ КАСТОМ ЭКСЕПШН");
        }
    }

    public static void readFile() throws IOException {
        FileReader file = new FileReader("ata.txt");
        int character;
        while ((character = file.read()) != -1) {
            System.out.println((char) character);
        }
    }
}
