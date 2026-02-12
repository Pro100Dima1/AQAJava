package org.cleanCode.task2.adapter.pdf;

public class DOCProcessor implements DocumentProcessor {
    @Override
    public void process(String fileName) {
        System.out.println("Processing DOC file: " + fileName);
    }
}
