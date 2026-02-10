package org.cleanCode.task2.abstractfactory.os.macos;

import org.cleanCode.task2.abstractfactory.os.Window;

public class MacOsWindow implements Window {
    @Override
    public void createWindow() {
        System.out.println("macOS window created");
    }
}
