package one.digitalinnovation.springdesignpatterns.repository.accounts;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

import one.digitalinnovation.springdesignpatterns.model.Client;
import one.digitalinnovation.springdesignpatterns.model.accounts.Account;

@NoRepositoryBean
public interface AccountBaseRepository<T extends Account> extends CrudRepository<T, Long>  {

    Optional<T> findByClient(Client client);
    
}
