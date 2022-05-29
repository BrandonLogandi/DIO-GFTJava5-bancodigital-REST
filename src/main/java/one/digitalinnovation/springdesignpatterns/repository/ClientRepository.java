package one.digitalinnovation.springdesignpatterns.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import one.digitalinnovation.springdesignpatterns.model.Client;

@Repository
public interface ClientRepository extends CrudRepository<Client, String> {

}