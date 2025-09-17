package org.expressJava.task7.fileReaderTask;

public class FileNotFoundCustomException extends RuntimeException {
    public FileNotFoundCustomException(String message){
        super(message);
    }
}
