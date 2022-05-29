package one.digitalinnovation.springdesignpatterns.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import one.digitalinnovation.springdesignpatterns.model.Client;
import one.digitalinnovation.springdesignpatterns.model.Address;
import one.digitalinnovation.springdesignpatterns.repository.ClientRepository;
import one.digitalinnovation.springdesignpatterns.repository.AddressRepository;
import one.digitalinnovation.springdesignpatterns.service.ClientService;
import one.digitalinnovation.springdesignpatterns.service.ViaCepService;

@Service
public class ClientServiceImpl implements ClientService {

	@Autowired
	private ClientRepository clientRepository;
	@Autowired
	private AddressRepository addressRepository;
	@Autowired
	private ViaCepService viaCepService;


	@Override
	public Iterable<Client> getAll() {
		return clientRepository.findAll();
	}

	@Override
	public Client getByCPF(String CPF) {
		return clientRepository.findById(CPF).orElseThrow();
	}

	@Override
	public Client insert(Client client) {
		Optional<Client> existent = clientRepository.findById(client.getCPF());
		if (existent.isPresent()) 
			return null;
		
		return saveWithCep(client);
	}

	@Override
	public Client update(String CPF, Client updatedClient) {
		Client clienteBd = getByCPF(CPF);
		
		if (updatedClient.getName() != null) 
			clienteBd.setName(updatedClient.getName());
		if (updatedClient.getAddress() != null)
			clienteBd.setAddress(updatedClient.getAddress());

		return saveWithCep(clienteBd);

	}

	@Override
	public void delete(String CPF) {
		clientRepository.deleteById(CPF);
	}

	private Client saveWithCep(Client client) {
		String cep = client.getAddress().getCep();

		Address address = addressRepository.findById(cep).orElseGet(() -> {
			Address newAddress = viaCepService.getCep(cep);
			addressRepository.save(newAddress);
			return newAddress;
		});
		
		client.setAddress(address);

		return clientRepository.save(client);
	}

}
