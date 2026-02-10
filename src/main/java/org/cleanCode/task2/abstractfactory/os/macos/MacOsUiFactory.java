package org.cleanCode.task2.abstractfactory.os.macos;

import org.cleanCode.task2.abstractfactory.os.Button;
import org.cleanCode.task2.abstractfactory.os.ElementFactory;
import org.cleanCode.task2.abstractfactory.os.Menu;
import org.cleanCode.task2.abstractfactory.os.Window;

public class MacOsUiFactory implements ElementFactory {

    @Override
    public Button getButton() {
        return new MacOsButton();
    }

    @Override
    public Menu getMenu() {
        return new MacOsMenu();
    }

    @Override
    public Window getWindow() {
        return new MacOsWindow();
    }
}
