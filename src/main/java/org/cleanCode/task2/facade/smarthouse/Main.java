package org.cleanCode.task2.facade.smarthouse;

public class Main {
    public static void main(String[] args) {

        Condicioner condicioner = new Condicioner();
        Light light = new Light();
        SecuritySystem securitySystem = new SecuritySystem();

        SmartHousFacade smartHousFacade = new SmartHousFacade(condicioner, light, securitySystem);

        smartHousFacade.allOff();

        smartHousFacade.allOn();
    }
}
