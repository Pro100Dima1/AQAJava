package org.example.task5.entertainmentPark;

public class RollerCoaster extends Attraction {

    @Override
    public void feelings() {
        System.out.println("Американские горки очень быстрые и нагруженые");
    }

    @Override
    public void service() {
        System.out.println("Американские горки нуждаются в чательной проверке безопасности");
    }
}
