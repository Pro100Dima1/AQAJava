package org.cleanCode.task2.abstractfactory.os.windows;

import org.cleanCode.task2.abstractfactory.os.Button;
import org.cleanCode.task2.abstractfactory.os.ElementFactory;
import org.cleanCode.task2.abstractfactory.os.Menu;
import org.cleanCode.task2.abstractfactory.os.Window;

public class WinUiFactory implements ElementFactory  {

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
