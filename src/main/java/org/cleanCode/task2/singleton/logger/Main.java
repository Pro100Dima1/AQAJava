package org.cleanCode.task2.singleton.logger;

public class Main {
    public static void main(String[] args) {
        Logger logger = Logger.getInstance();

        logger.exceptions = "Not found exceptions";
        logger.displayInfo();
    }
}
