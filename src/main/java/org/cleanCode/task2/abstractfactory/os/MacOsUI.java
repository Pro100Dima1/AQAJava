package org.cleanCode.task2.abstractfactory.os;

import org.cleanCode.task2.abstractfactory.os.macos.MacOsUiFactory;

public class MacOsUI {
    public static void main(String[] args) {
        ElementFactory elementFactory = new MacOsUiFactory();
        Button macOsButton = elementFactory.getButton();
        Menu macOsMenu = elementFactory.getMenu();
        Window macOsWindowMenu = elementFactory.getWindow();

        macOsButton.createButton();
        macOsMenu.createMenu();
        macOsWindowMenu.createWindow();
    }
}
