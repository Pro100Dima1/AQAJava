package org.cleanCode.task2.abstractfactory.os.windows;

import org.cleanCode.task2.abstractfactory.os.Menu;

public class WinMenu implements Menu {

    @Override
    public void createMenu() {
        System.out.println("Create windows menu");
    }
}
