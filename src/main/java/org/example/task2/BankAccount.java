package org.example.task2;

public class BankAccount {
    String owner;
    double balance;

    BankAccount(String owner, double balance) {
        this.owner = owner;
        this.balance = balance;
    }

    String getOwner() {
        return this.owner;
    }

    double getBalance() {
        return this.balance;
    }

    void setOwner(String owner) {
        this.owner = owner;
    }

    double deposit(double amount) {
        this.balance += amount;
        return this.balance;
    }

    double withdraw(double amount) {
        this.balance -= amount;
        return this.balance;
    }

    void printBalance() {
        System.out.println("Вы внесли депозит и ваш счет равен: " + deposit(3000) + ". Вы сняли со счета деньги и ваш счет равен: " + withdraw(1500));
    }
}
