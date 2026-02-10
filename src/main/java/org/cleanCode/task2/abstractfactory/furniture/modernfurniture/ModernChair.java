package org.cleanCode.task2.abstractfactory.furniture.modernfurniture;

import org.cleanCode.task2.abstractfactory.furniture.Chair;

public class ModernChair implements Chair {
    @Override
    public void createChair() {
        System.out.println("Create Modern chair");
    }
}
