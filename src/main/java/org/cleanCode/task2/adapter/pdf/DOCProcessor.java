package org.cleanCode.task2.adapter.pdf;

public class DOCProcessor implements DocumentProcessor  {

    @Override
    public void process(String filename) {
        System.out.println("Processing DOC file : " + filename);
    }
}
