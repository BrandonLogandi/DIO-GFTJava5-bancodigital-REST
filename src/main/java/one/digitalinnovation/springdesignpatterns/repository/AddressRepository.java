package one.digitalinnovation.springdesignpatterns.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import one.digitalinnovation.springdesignpatterns.model.Address;

@Repository
public interface AddressRepository extends CrudRepository<Address, String> {

}