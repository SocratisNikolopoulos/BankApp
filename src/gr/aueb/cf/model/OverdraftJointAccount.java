package gr.aueb.cf.model;

import gr.aueb.cf.exceptions.InsufficientBalanceException;
import gr.aueb.cf.exceptions.NegativeAmountException;
import gr.aueb.cf.exceptions.SsnIsNotValid;

public class OverdraftJointAccount extends JointAccount {
    public OverdraftJointAccount() {}

    public OverdraftJointAccount(User holder, String iban, double balance, User secondHolder) {
        super(holder, iban, balance, secondHolder);
    }

    @Override
    public void withdraw(double amount, String ssn) throws  SsnIsNotValid, NegativeAmountException {
        try {
            if(amount < 0) throw new NegativeAmountException(amount);
            if(!isSsnValid(ssn)) throw new SsnIsNotValid(ssn);
            setBalance(getBalance() - amount);
        } catch (SsnIsNotValid | NegativeAmountException e) {
            System.out.println("Error : withdrawal");
            throw e;
        }
    }

}
