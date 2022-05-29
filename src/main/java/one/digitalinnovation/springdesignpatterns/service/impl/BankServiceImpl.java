package one.digitalinnovation.springdesignpatterns.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import one.digitalinnovation.springdesignpatterns.model.Bank;
import one.digitalinnovation.springdesignpatterns.model.Transaction;
import one.digitalinnovation.springdesignpatterns.model.accounts.Account;
import one.digitalinnovation.springdesignpatterns.repository.BankRepository;
import one.digitalinnovation.springdesignpatterns.service.BankService;
import one.digitalinnovation.springdesignpatterns.service.AccountService;
import one.digitalinnovation.springdesignpatterns.service.TransactionService;

@Service
public class BankServiceImpl implements BankService {

    @Autowired
    private BankRepository bankRepository;
    @Autowired
    private AccountService accountService;
    @Autowired
    private TransactionService transactionService;

    @Override
    public Bank getBank() {
        return bankRepository.findById("Banco DIO").get();
    }

    @Override
    public Bank setYieldRate(double yieldRate) {
        Bank bank = getBank();
        bank.setYieldRate(yieldRate);
        return bankRepository.save(bank);
    }

    @Override
    public Transaction withdraw(double value, String CPFOwner, String password) throws Exception {
        Account account = accountService.getByClient(CPFOwner).get();
        if(!authorize(account, password))
            return null;

        account.withdraw(value);
        Transaction t = new Transaction(value*(-1), account);
        account.getTransactions().add(t);
        
        return transactionService.insert(t);
    }

    @Override
    public Transaction deposit(double value, String CPFOwner) {
        Account account = accountService.getByClient(CPFOwner).get();

        account.deposit(value);
        Transaction t = new Transaction(value, account);
        account.getTransactions().add(t);
        
        return transactionService.insert(t);
    }

    @Override
    public Transaction transfer(double value, String CPFSender, String CPFReceiver, String passwordSender) throws Exception {
        Account sender = accountService.getByClient(CPFSender).get();
        Account receiver = accountService.getByClient(CPFReceiver).get();
        if (!authorize(sender, passwordSender)) 
            return null;

        sender.transfer(value, receiver);
        Transaction t = new Transaction(value*(-1), sender, receiver);
        Transaction t2 = new Transaction(value, sender, receiver);

        sender.getTransactions().add(t);
        transactionService.insert(t);
        receiver.getTransactions().add(t2);
        return transactionService.insert(t2);
    }

    @Override
    public boolean authorize(Account account, String password) {
        return account.getPassword().equals(password);
    }
    
}
