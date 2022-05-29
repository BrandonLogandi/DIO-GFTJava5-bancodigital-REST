package one.digitalinnovation.springdesignpatterns.service.impl;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import one.digitalinnovation.springdesignpatterns.model.Client;
import one.digitalinnovation.springdesignpatterns.model.Transaction;
import one.digitalinnovation.springdesignpatterns.model.accounts.Account;
import one.digitalinnovation.springdesignpatterns.repository.ClientRepository;
import one.digitalinnovation.springdesignpatterns.repository.TransactionRepository;
import one.digitalinnovation.springdesignpatterns.repository.accounts.AccountRepository;
import one.digitalinnovation.springdesignpatterns.service.TransactionService;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private ClientRepository clientRepository;

    @Override
    public Iterable<Transaction> getAll() {
        return transactionRepository.findAll();
    }

    @Override
    public Transaction getById(long id) {
        return transactionRepository.findById(id).orElseThrow();
    }

    @Override
    public Iterable<Transaction> getBySender(String CPF) {
        return transactionRepository.findAllBySender(findAccountByCPF(CPF));
    }

    @Override
    public Iterable<Transaction> getByReceiver(String CPF) {
        return transactionRepository.findAllByReceiver(findAccountByCPF(CPF));
    }

    @Override
    public Iterable<Transaction> getByDate(String dateString) {
        LocalDate data = LocalDate.parse(dateString);
        return transactionRepository.findAllByDate(data);
    }

    @Override
    public Transaction insert(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    private Account findAccountByCPF(String CPF){
        Client client = clientRepository.findById(CPF).orElseThrow();
        return accountRepository.findByClient(client).orElseThrow();
    }
    
}
