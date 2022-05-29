package one.digitalinnovation.springdesignpatterns.repository.accounts;

import java.time.LocalDate;

import one.digitalinnovation.springdesignpatterns.model.accounts.Account;
import one.digitalinnovation.springdesignpatterns.model.accounts.SavingsAccount;

public interface SavingsAccountRepository extends AccountBaseRepository<SavingsAccount> {

    Iterable<Account> findByLastYieldDate(LocalDate lastYieldDate);
    
}
