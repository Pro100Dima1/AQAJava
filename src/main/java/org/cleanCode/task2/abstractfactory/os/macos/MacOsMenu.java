package org.cleanCode.task2.abstractfactory.os.macos;

import org.cleanCode.task2.abstractfactory.os.Menu;

public class MacOsMenu implements Menu {
    @Override
    public void createMenu() {
        System.out.println("macOS menu has been created");
    }
}
