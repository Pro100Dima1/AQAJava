package org.example.task5.entertainmentPark;

public class RollerCoaster extends Attraction {

    @Override
    public void feelings() {
        System.out.println("Очень быстрые и драйвовые");
    }

    @Override
    public void needTO() {
        System.out.println("Американские горки требуют чательной проверка безопасности");
    }
}
