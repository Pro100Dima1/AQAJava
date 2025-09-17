package org.expressJava.task5.museum;

public class Manuscript extends Exhibits  {

    @Override
    public void history() {
        System.out.println("Это манускрипт с древними писаниями на асемблере. Сложно читать");
    }

    @Override
    public void storageConditions() {
        System.out.println("Манускрипт — требует контролируемой влажности");
    }
}
