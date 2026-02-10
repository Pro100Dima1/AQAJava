package org.cleanCode.task2.abstractfactory.furniture.classicfurniture;

import org.cleanCode.task2.abstractfactory.furniture.Chair;

public class ClassicChair implements Chair {
    @Override
    public void createChair() {
        System.out.println("Create Classic chair");
    }
}
