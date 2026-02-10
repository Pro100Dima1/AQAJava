package org.cleanCode.task2.abstractfactory.os;

public interface ElementFactory {
    Button getButton();

    Menu getMenu();

    Window getWindow();
}
