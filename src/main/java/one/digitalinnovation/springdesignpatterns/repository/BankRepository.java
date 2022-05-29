package one.digitalinnovation.springdesignpatterns.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import one.digitalinnovation.springdesignpatterns.model.Bank;

@Repository
public interface BankRepository extends CrudRepository<Bank, String> {
    
}
