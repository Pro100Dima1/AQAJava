package org.cleanCode.task2.adapter.pdf;

public class Main {
    public static void main(String[] args) {
        // Работа с DOC
        DocumentProcessor docProcessor = new DOCProcessor();
        docProcessor.process("file.doc");

        System.out.println("----------------");

        // Работа с PDF через адаптер
        PDFProcessor pdfProcessor = new PDFProcessor();
        DocumentProcessor adapter = new DocumentAdapter(pdfProcessor);
        adapter.process("file.pdf");
    }
}
