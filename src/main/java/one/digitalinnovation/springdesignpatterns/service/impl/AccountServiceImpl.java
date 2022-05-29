package one.digitalinnovation.springdesignpatterns.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import one.digitalinnovation.springdesignpatterns.model.Client;
import one.digitalinnovation.springdesignpatterns.model.accounts.Account;
import one.digitalinnovation.springdesignpatterns.model.accounts.CheckingAccount;
import one.digitalinnovation.springdesignpatterns.model.accounts.SavingsAccount;
import one.digitalinnovation.springdesignpatterns.repository.BankRepository;
import one.digitalinnovation.springdesignpatterns.repository.ClientRepository;
import one.digitalinnovation.springdesignpatterns.repository.accounts.CheckingAccountRepository;
import one.digitalinnovation.springdesignpatterns.repository.accounts.SavingsAccountRepository;
import one.digitalinnovation.springdesignpatterns.repository.accounts.AccountRepository;
import one.digitalinnovation.springdesignpatterns.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private CheckingAccountRepository checkingAccountRepository;
    @Autowired
    private SavingsAccountRepository savingsAccountRepository;
    @Autowired
	private ClientRepository clientRepository;
    @Autowired
    private BankRepository bankRepository;

    @Override
    public Iterable<Account> getAll() {
        return accountRepository.findAll();
    }

    @Override
    public Iterable<CheckingAccount> getAllChecking() {
        return checkingAccountRepository.findAll();
    }

    @Override
    public Iterable<SavingsAccount> getAllSavings() {
        return savingsAccountRepository.findAll();
    }

    @Override
    public Optional<Account> getById(long id) {
        return accountRepository.findById(id);
    }

    @Override
    public Optional<Account> getByClient(String CPF) {
        return accountRepository.findByClient(clientRepository.findById(CPF).orElseThrow());
    }

    @Override
    public Account insert(Account account, boolean isChecking) {
        Optional<Client> owner = clientRepository.findById(account.getClient().getCPF());
        if(owner.isEmpty())
            return null;

        Optional<Account> existing = accountRepository.findByClient(owner.get());
        if(existing.isPresent())
            return null;

        if (isChecking) 
            return saveAccount(new CheckingAccount(
                owner.get(), 
                account.getPassword(), 
                bankRepository.findById("Banco DIO").get().getDefaultBranch()));

        return saveAccount(new SavingsAccount(
            owner.get(), 
            account.getPassword(),
            bankRepository.findById("Banco DIO").get().getDefaultBranch()));
    }

    @Override
    public void delete(long id) {
        accountRepository.delete(getById(id).get());
    }

    private Account saveAccount(Account account) {
        if(account instanceof CheckingAccount)
            return checkingAccountRepository.save((CheckingAccount) account);
        return savingsAccountRepository.save((SavingsAccount) account);
    }
    
    
}
