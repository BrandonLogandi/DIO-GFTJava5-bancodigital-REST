package one.digitalinnovation.springdesignpatterns.repository;

import java.time.LocalDate;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import one.digitalinnovation.springdesignpatterns.model.Transaction;
import one.digitalinnovation.springdesignpatterns.model.accounts.Account;

@Repository
public interface TransactionRepository extends CrudRepository<Transaction, Long> {

    Iterable<Transaction> findAllBySender(Account sender);
    Iterable<Transaction> findAllByReceiver(Account receiver);
    Iterable<Transaction> findAllByDate(LocalDate date);

}
