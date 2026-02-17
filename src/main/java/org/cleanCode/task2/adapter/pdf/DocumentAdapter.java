package org.cleanCode.task2.adapter.pdf;

public class DocumentAdapter implements DocumentProcessor {

    private PDFProcessor pdfProcessor;

    public DocumentAdapter(PDFProcessor pdfProcessor) {
        this.pdfProcessor = pdfProcessor;
    }

    @Override
    public void process(String filename) {
        System.out.println("Converting PDF file to DOC file");
        pdfProcessor.process(filename);
    }
}
