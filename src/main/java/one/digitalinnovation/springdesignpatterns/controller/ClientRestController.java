package one.digitalinnovation.springdesignpatterns.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import one.digitalinnovation.springdesignpatterns.model.Client;
import one.digitalinnovation.springdesignpatterns.service.ClientService;

@RestController
@RequestMapping("clientes")
public class ClientRestController {

	@Autowired
	private ClientService clientService;

	@GetMapping
	public ResponseEntity<Iterable<Client>> buscarTodos() {
		return ResponseEntity.ok(clientService.getAll());
	}

	@GetMapping("/{CPF}")
	public ResponseEntity<Client> buscarPorId(@PathVariable String CPF) {
		return ResponseEntity.ok(clientService.getByCPF(CPF));
	}

	@PostMapping
	public ResponseEntity<Client> inserir(@RequestBody Client cliente) {
		return ResponseEntity.ok(clientService.insert(cliente));
	}

	@PutMapping("/{CPF}")
	public ResponseEntity<Client> atualizar(@PathVariable String CPF, @RequestBody Client clienteAtualizado) {
		return ResponseEntity.ok(clientService.update(CPF, clienteAtualizado));
	}

	@DeleteMapping("/{CPF}")
	public ResponseEntity<Void> deletar(@PathVariable String CPF) {
		clientService.delete(CPF);
		return ResponseEntity.ok().build();
	}
}
