package org.cleanCode.task2.singleton.configmanager;

public class Main {

    public static void main(String[] args) {
        ConfigurationManager configurationManager = ConfigurationManager.getInstance();

        configurationManager.urlDB = "C:\\common\\app";
        configurationManager.userPassword = "QAZWSXEDC";
        configurationManager.userName = "Petya";

        configurationManager.displayInfo();
    }
}
