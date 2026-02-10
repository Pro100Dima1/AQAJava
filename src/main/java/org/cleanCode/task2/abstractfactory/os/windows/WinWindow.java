package org.cleanCode.task2.abstractfactory.os.windows;

import org.cleanCode.task2.abstractfactory.os.Window;

public class WinWindow implements Window {
    @Override
    public void createWindow() {
        System.out.println("Windows window added");
    }
}
