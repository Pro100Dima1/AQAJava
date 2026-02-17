package org.cleanCode.task2.abstractfactory.furniture.modernfurniture;

import org.cleanCode.task2.abstractfactory.furniture.Chair;
import org.cleanCode.task2.abstractfactory.furniture.FurnitureFactory;
import org.cleanCode.task2.abstractfactory.furniture.Table;

public class ModernFactory implements FurnitureFactory  {

    @Override
    public Chair getChair() {
        return new ModernChair();
    }

    @Override
    public Table getTable() {
        return new ModernTable();
    }
}
