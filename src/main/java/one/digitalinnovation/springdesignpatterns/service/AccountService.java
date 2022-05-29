package one.digitalinnovation.springdesignpatterns.service;

import java.util.Optional;

import one.digitalinnovation.springdesignpatterns.model.accounts.Account;
import one.digitalinnovation.springdesignpatterns.model.accounts.CheckingAccount;
import one.digitalinnovation.springdesignpatterns.model.accounts.SavingsAccount;

public interface AccountService {

    Iterable<Account> getAll();

    Iterable<CheckingAccount> getAllChecking();

    Iterable<SavingsAccount> getAllSavings();

    Optional<Account> getById(long id);

    Optional<Account> getByClient(String CPF);

    Account insert(Account account, boolean isChecking);

	void delete(long id);
    
}
