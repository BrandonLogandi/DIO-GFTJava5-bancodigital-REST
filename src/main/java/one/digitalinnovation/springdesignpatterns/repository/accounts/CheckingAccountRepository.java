package one.digitalinnovation.springdesignpatterns.repository.accounts;

import javax.transaction.Transactional;

import one.digitalinnovation.springdesignpatterns.model.accounts.CheckingAccount;

@Transactional
public interface CheckingAccountRepository extends AccountBaseRepository<CheckingAccount> {

    
}
