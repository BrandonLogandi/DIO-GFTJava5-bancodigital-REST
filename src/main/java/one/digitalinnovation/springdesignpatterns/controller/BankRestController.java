package one.digitalinnovation.springdesignpatterns.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import one.digitalinnovation.springdesignpatterns.model.Bank;
import one.digitalinnovation.springdesignpatterns.model.Transaction;
import one.digitalinnovation.springdesignpatterns.service.BankService;

@RestController
@RequestMapping("banco")
public class BankRestController {

    @Autowired
    private BankService bankService;

    @GetMapping
    public ResponseEntity<Bank> buscarBanco() {
        return ResponseEntity.ok(bankService.getBank());
    }

    @PutMapping("/{rendimentoRate}")
    public ResponseEntity<Bank> mudarRendimentoRate(@PathVariable double rendimentoRate) {
        return ResponseEntity.ok(bankService.setYieldRate(rendimentoRate));
    }

    @PutMapping("/sacar/valor={valor}&CPFTitular={CPFTitular}&senha={senha}")
    public ResponseEntity<Transaction> sacar(@PathVariable double valor, @PathVariable String CPFTitular, @PathVariable String senha) throws Exception {
        return ResponseEntity.ok(bankService.withdraw(valor, CPFTitular, senha));    
    }

    @PutMapping("/depositar/valor={valor}&CPFTitular={CPFTitular}")
    public ResponseEntity<Transaction> depositar(@PathVariable double valor, @PathVariable String CPFTitular) {
        return ResponseEntity.ok(bankService.deposit(valor, CPFTitular));
    }

    @PutMapping("/transferir/valor={valor}&remetente={remetente}&destinatario={destinatario}&senhaRementente={senhaRementente}")
    public ResponseEntity<Transaction> transferir(@PathVariable double valor, @PathVariable String remetente, @PathVariable String destinatario, @PathVariable String senhaRementente) throws Exception {
        return ResponseEntity.ok(bankService.transfer(valor, remetente, destinatario, senhaRementente));
    }
    
}
