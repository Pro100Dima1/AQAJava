package org.cleanCode.task2.abstractfactory.windows;

import org.cleanCode.task2.abstractfactory.Window;

public class WinWindow implements Window {
    @Override
    public void createWindow() {
        System.out.println("Windows window added");
    }
}
