package one.digitalinnovation.springdesignpatterns.service;

import one.digitalinnovation.springdesignpatterns.model.Bank;
import one.digitalinnovation.springdesignpatterns.model.Transaction;
import one.digitalinnovation.springdesignpatterns.model.accounts.Account;

public interface BankService {

	Bank getBank();

	Bank setYieldRate(double yieldRate);

	Transaction withdraw(double value, String CPFOwner, String password) throws Exception;

	Transaction deposit(double value, String CPFOwner);

	Transaction transfer(double value, String CPFSender, String CPFReceiver, String passwordSender) throws Exception;

	boolean authorize(Account account, String password);

}
