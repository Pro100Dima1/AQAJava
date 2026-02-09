package org.cleanCode.task2.abstractfactory.macos;

import org.cleanCode.task2.abstractfactory.Button;

public class MacOsButton implements Button {
    @Override
    public void createButton() {
        System.out.println("macOS button has been create");
    }
}
