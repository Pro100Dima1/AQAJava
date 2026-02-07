package org.cleanCode.task2.singleton.configmanager;

public class Main {

    public static void main(String[] args) {
        ConfigurationManager config1 = ConfigurationManager.getInstance();

        config1.urlDB = "C:\\Users\\Public";
        config1.userName = "AEZAKMI";
        config1.userPassword = "Qazwsx934!";

        config1.diaplayConfigOnfo();

    }

}
