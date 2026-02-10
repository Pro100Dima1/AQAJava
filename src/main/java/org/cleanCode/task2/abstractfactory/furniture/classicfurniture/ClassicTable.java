package org.cleanCode.task2.abstractfactory.furniture.classicfurniture;

import org.cleanCode.task2.abstractfactory.furniture.Table;

public class ClassicTable implements Table {
    @Override
    public void createTable() {
        System.out.println("Create Classic table");
    }
}
