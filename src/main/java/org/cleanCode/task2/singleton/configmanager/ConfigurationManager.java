package org.cleanCode.task2.singleton.configmanager;

public class ConfigurationManager {

    public String urlDB;
    public String userName;
    public String userPassword;

    private static ConfigurationManager configurationManager;

    private ConfigurationManager() {

    }

    public static ConfigurationManager getInstance() {
        if (configurationManager == null) {
            configurationManager = new ConfigurationManager();
        }
        return configurationManager;
    }

    public void displayInfo(){
        System.out.println("User " + userName + " with password " + userPassword + " open " + urlDB);
    }

}
