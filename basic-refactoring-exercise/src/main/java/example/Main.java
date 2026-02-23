package example;

import example.model.AccountHolder;
import example.model.BankAccount;
import example.model.SimpleBankAccount;

public class Main {

    private static final String Name = "Mario";
    private static final String Surname = "Rossi";
    private static final int Id = 1;
    private static final double DefaultBalance = 0;

    public static void main(String[] args) {
        final AccountHolder accountHolder = new AccountHolder(Name, Surname, Id);
        final BankAccount bankAccount = new SimpleBankAccount(accountHolder, DefaultBalance);
        bankAccount.deposit(accountHolder.id(), 100);
        System.out.println("Current balance is " + bankAccount.getBalance());
        bankAccount.withdraw(accountHolder.id(), 30);
        System.out.println("Current balance is " + bankAccount.getBalance());
        bankAccount.withdraw(accountHolder.id(), 80);
        System.out.println("Current balance is " + bankAccount.getBalance());
    }
}
