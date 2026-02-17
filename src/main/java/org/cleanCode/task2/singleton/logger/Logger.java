package org.cleanCode.task2.singleton.logger;

public class Logger {

    public String exceptions;

    private static Logger logger;

    private Logger() {
    }

    public static Logger getInstance() {
        if (logger == null) {
            logger = new Logger();
        }
        return logger;
    }

    public void displayInfo(){
        System.out.println(exceptions);
    }
}
