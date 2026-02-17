package org.cleanCode.task2.abstractfactory.furniture.classicfurniture;

import org.cleanCode.task2.abstractfactory.furniture.Chair;
import org.cleanCode.task2.abstractfactory.furniture.FurnitureFactory;
import org.cleanCode.task2.abstractfactory.furniture.Table;
import org.cleanCode.task2.abstractfactory.furniture.modernfurniture.ModernTable;

public class ClassicFactory implements FurnitureFactory {

    @Override
    public Chair getChair() {
        return new ClassicChair();
    }

    @Override
    public Table getTable() {
        return new ClassicTable();
    }
}
