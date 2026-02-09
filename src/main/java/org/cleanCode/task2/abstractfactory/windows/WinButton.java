package org.cleanCode.task2.abstractfactory.windows;

import org.cleanCode.task2.abstractfactory.Button;

public class WinButton implements Button {
    @Override
    public void createButton() {
        System.out.println("Windows button added");
    }
}
