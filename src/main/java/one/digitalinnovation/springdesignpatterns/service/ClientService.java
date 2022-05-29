package one.digitalinnovation.springdesignpatterns.service;

import one.digitalinnovation.springdesignpatterns.model.Client;


public interface ClientService {

	Iterable<Client> getAll();

	Client getByCPF(String CPF);

	Client insert(Client client);

	Client update(String CPF, Client updatedClient);

	void delete(String CPF);

}
