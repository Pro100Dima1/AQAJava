package org.example.task2;

public class BankAccount {
    String owner;
    double balance;

    BankAccount(String owner, double balance){
        this.balance = balance;
        this.owner = owner;
    }

    public double getBalance() {
        return this.balance;
    }

    public String getOwner() {
        return this.owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    double deposit(double amount){
       double depositBalance = this.balance + amount;
return depositBalance;
    }

    double withdraw(double amount){
        double withdrawBalance = this.balance - amount;
        return withdrawBalance;
    }

    void printBalance(){
        System.out.println("Вы пополнили ваш счет и баланс теперь составляет: " + deposit(300) + " Вы сняли со счета сумму и теперь баланс составляет: " + withdraw(500));
    }




}
