package gr.aueb.cf.model;

import gr.aueb.cf.exceptions.InsufficientBalanceException;
import gr.aueb.cf.exceptions.NegativeAmountException;
import gr.aueb.cf.exceptions.SsnIsNotValid;

public class Account extends IdentifiableEntity {
    private User holder;
    private String iban;
    private double balance;
    public Account() {}

    public Account(User holder, String iban, double balance) {
        this.holder = holder;
        this.iban = iban;
        this.balance = balance;
    }

    public User getHolder() {
        return holder;
    }

    public void setHolder(User holder) {
        this.holder = holder;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "Account{" +
                "holder=" + holder +
                ", iban='" + iban + '\'' +
                ", balance=" + balance +
                '}';
    }
    // public API
    public void deposit(double amount) throws NegativeAmountException {
        try {
            if(amount < 0) {
                throw new NegativeAmountException(amount);
            }
            balance += amount;
        } catch (NegativeAmountException e) {
            System.err.println("Error : Negative amount");
            throw e;
        }
    }
    public void withdraw(double amount, String ssn) throws InsufficientBalanceException, SsnIsNotValid, NegativeAmountException {
        try {
            if(amount < 0) throw new NegativeAmountException(amount);
            if(amount > balance) throw new InsufficientBalanceException(getBalance(), amount);
            if(!isSsnValid(ssn)) throw new SsnIsNotValid(ssn);
            balance -= amount;
        } catch (InsufficientBalanceException | SsnIsNotValid | NegativeAmountException e) {
            System.out.println("Error:withdrawal");
            throw e;
        }
    }
    protected boolean isSsnValid(String ssn) {
        if (ssn == null || getHolder().getSsn() == null) {
            return false;
        }
        return holder.getSsn().equals(ssn);
    }
}
