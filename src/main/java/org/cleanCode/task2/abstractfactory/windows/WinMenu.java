package org.cleanCode.task2.abstractfactory.windows;

import org.cleanCode.task2.abstractfactory.Menu;

public class WinMenu implements Menu {
    @Override
    public void createMenu() {
        System.out.println("Windows menu added");
    }
}
