package one.digitalinnovation.springdesignpatterns.service;

import one.digitalinnovation.springdesignpatterns.model.Transaction;

public interface TransactionService {
    
    Iterable<Transaction> getAll();

    Transaction getById(long id);

    Iterable<Transaction> getBySender(String CPF);

    Iterable<Transaction> getByReceiver(String CPF);

    Iterable<Transaction> getByDate(String dateString);

    Transaction insert(Transaction transaction);

}
