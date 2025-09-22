package org.expressJava.task10.ex9;

public class Main {
    public static void main(String[] args) {

    }

    public static int countWords(String sentence) {
        return sentence.trim().isEmpty() ? 0 : sentence.split("\\s+").length;
    }
}
