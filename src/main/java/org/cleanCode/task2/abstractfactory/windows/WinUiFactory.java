package org.cleanCode.task2.abstractfactory.windows;

import org.cleanCode.task2.abstractfactory.Button;
import org.cleanCode.task2.abstractfactory.ElementFactory;
import org.cleanCode.task2.abstractfactory.Menu;
import org.cleanCode.task2.abstractfactory.Window;

public class WinUiFactory implements ElementFactory {
    @Override
    public Button getButton() {
        return new WinButton();
    }

    @Override
    public Menu getMenu() {
        return new WinMenu();
    }

    @Override
    public Window getWindow() {
        return new WinWindow();
    }
}
