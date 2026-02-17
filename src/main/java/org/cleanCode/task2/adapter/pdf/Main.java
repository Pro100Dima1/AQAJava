package org.cleanCode.task2.adapter.pdf;

public class Main {
    public static void main(String[] args) {
        PDFProcessor pdfProcessor = new PDFProcessor();
        DocumentProcessor adapter = new DocumentAdapter(pdfProcessor);
        adapter.process("pdf");

    }
}
