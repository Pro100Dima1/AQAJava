package org.cleanCode.task2.adapter.pdf;

public class Pdf implements DocumentProcessor{
    @Override
    public void process() {
        System.out.println("DOC convert to PDF");
    }
}
