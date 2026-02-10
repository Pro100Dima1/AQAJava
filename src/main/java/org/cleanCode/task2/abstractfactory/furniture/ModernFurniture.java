package org.cleanCode.task2.abstractfactory.furniture;

import org.cleanCode.task2.abstractfactory.furniture.modernfurniture.ModernFactory;

public class ModernFurniture {
    public static void main(String[] args) {
        FurnitureFactory furnitureFactory = new ModernFactory();
        Chair modernChair = furnitureFactory.getChair();
        Table modernTable = furnitureFactory.getTable();

        System.out.println("START: ");

        modernChair.createChair();
        modernTable.createTable();
    }
}
