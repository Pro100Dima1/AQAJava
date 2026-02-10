package org.cleanCode.task2.adapter.pdf;

public class Doc implements DocumentProcessor{
    @Override
    public void process() {
        System.out.println("PDF convert to DOC");
    }
}
