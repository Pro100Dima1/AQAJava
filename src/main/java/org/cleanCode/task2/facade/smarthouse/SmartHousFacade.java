package org.cleanCode.task2.facade.smarthouse;

public class SmartHousFacade {

    private Condicioner condicioner;
    private Light light;
    private SecuritySystem securitySystem;

    public SmartHousFacade(Condicioner condicioner, Light light, SecuritySystem securitySystem) {
        this.condicioner = condicioner;
        this.light = light;
        this.securitySystem = securitySystem;
    }

    public void  allOn(){
        condicioner.condicionerOn();
        light.lightOn();
        securitySystem.securityOn();
    }

    public void allOff(){
        condicioner.condicionerOff();
        light.lightOff();
        securitySystem.securityOff();
    }
}
