package org.cleanCode.task2.abstractfactory.furniture;

import org.cleanCode.task2.abstractfactory.furniture.classicfurniture.ClassicFactory;

public class ClassicFurniture {
    public static void main(String[] args) {
        FurnitureFactory furnitureFactory = new ClassicFactory();
        Chair classicChair = furnitureFactory.getChair();
        Table classicTable = furnitureFactory.getTable();

        System.out.println("STAART : ");

        classicChair.createChair();
        classicTable.createTable();
    }
}
