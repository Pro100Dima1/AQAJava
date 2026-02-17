package org.cleanCode.task2.adapter.pdf;

public class PDFProcessor implements DocumentProcessor {

    @Override
    public void process(String filename) {
        System.out.println("Processing PDF file : " + filename);
    }
}
