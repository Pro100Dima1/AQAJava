package org.example.task5.museum;

public class Manuscript extends Exhibits {
    @Override
    public void historyOfExhibits() {
        System.out.println("Это манускрипт древних разработчиков. На нем можно разобрать ассемблер, нолики и единички");
    }

    @Override
    public void StorageConditions() {
        System.out.println("Этот манускрипт нуждается в рестоврации");
    }
}
