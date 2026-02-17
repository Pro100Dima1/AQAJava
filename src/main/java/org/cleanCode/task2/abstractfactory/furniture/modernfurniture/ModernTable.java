package org.cleanCode.task2.abstractfactory.furniture.modernfurniture;

import org.cleanCode.task2.abstractfactory.furniture.Table;

public class ModernTable implements Table {

    @Override
    public void createTable() {
        System.out.println("Create modern table");
    }
}
