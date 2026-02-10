package org.cleanCode.task2.abstractfactory.os.windows;

import org.cleanCode.task2.abstractfactory.os.Button;

public class WinButton implements Button {
    @Override
    public void createButton() {
        System.out.println("Windows button added");
    }
}
