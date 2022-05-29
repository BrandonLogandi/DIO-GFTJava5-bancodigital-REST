package one.digitalinnovation.springdesignpatterns.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import one.digitalinnovation.springdesignpatterns.model.accounts.Account;
import one.digitalinnovation.springdesignpatterns.model.accounts.CheckingAccount;
import one.digitalinnovation.springdesignpatterns.model.accounts.SavingsAccount;
import one.digitalinnovation.springdesignpatterns.service.AccountService;

@RestController
@RequestMapping("contas")
public class AccountRestController {

    @Autowired
    private AccountService accountService;

    @GetMapping
    public ResponseEntity<Iterable<Account>> buscarTodas() {
        return ResponseEntity.ok(accountService.getAll());
    }

    @GetMapping("/corrente")
    public ResponseEntity<Iterable<CheckingAccount>> buscarTodasCorrente() {
        return ResponseEntity.ok(accountService.getAllChecking());
    }

    @GetMapping("/poupanca")
    public ResponseEntity<Iterable<SavingsAccount>> buscarTodasPoupanca() {
        return ResponseEntity.ok(accountService.getAllSavings());
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Optional<Account>> buscarPorId(@PathVariable long id) {
        return ResponseEntity.ok(accountService.getById(id));
    }

    @GetMapping("/CPF/{CPF}")
    public ResponseEntity<Optional<Account>> buscarPorCliente(@PathVariable String CPF) {
        return ResponseEntity.ok(accountService.getByClient(CPF));
    }

    @PostMapping("/{isCorrente}")
    public ResponseEntity<Account> inserir(@RequestBody Account conta, @PathVariable boolean isCorrente) {
        return ResponseEntity.ok(accountService.insert(conta, isCorrente));
    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity<Void>deletar(@PathVariable long id) {
        accountService.delete(id);
        return ResponseEntity.ok().build();
    }
    
}
