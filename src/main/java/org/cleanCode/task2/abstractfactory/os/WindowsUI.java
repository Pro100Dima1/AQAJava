package org.cleanCode.task2.abstractfactory.os;

import org.cleanCode.task2.abstractfactory.os.windows.WinUiFactory;

public class WindowsUI {
    public static void main(String[] args) {
        ElementFactory elementFactory = new WinUiFactory();
        Button winButton = elementFactory.getButton();
        Menu winMenu = elementFactory.getMenu();
        Window winWindow = elementFactory.getWindow();

        winButton.createButton();
        winWindow.createWindow();
        winMenu.createMenu();
    }
}
