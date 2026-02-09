package org.cleanCode.task2.abstractfactory.macos;

import org.cleanCode.task2.abstractfactory.Button;
import org.cleanCode.task2.abstractfactory.ElementFactory;
import org.cleanCode.task2.abstractfactory.Menu;
import org.cleanCode.task2.abstractfactory.Window;

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
