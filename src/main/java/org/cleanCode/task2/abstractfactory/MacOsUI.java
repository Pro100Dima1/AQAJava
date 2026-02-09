package org.cleanCode.task2.abstractfactory;

import org.cleanCode.task2.abstractfactory.macos.MacOsButton;
import org.cleanCode.task2.abstractfactory.macos.MacOsUiFactory;

public class MacOsUI {
    public static void main(String[] args) {
        ElementFactory elementFactory = new MacOsUiFactory();
        Button macOsButton = elementFactory.getButton();
        Menu macOsMenu = elementFactory.getMenu();
        Window macOsWindow = elementFactory.getWindow();

        System.out.println("UI MacOS UI start ...");

        macOsButton.createButton();
        macOsMenu.createMenu();
        macOsWindow.createWindow();
    }
}
