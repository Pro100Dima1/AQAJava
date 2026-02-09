package org.cleanCode.task2.abstractfactory.macos;

import org.cleanCode.task2.abstractfactory.Window;

public class MacOsWindow implements Window {
    @Override
    public void createWindow() {
        System.out.println("macOS window created");
    }
}
