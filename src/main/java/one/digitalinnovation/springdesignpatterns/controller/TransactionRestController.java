package one.digitalinnovation.springdesignpatterns.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import one.digitalinnovation.springdesignpatterns.model.Transaction;
import one.digitalinnovation.springdesignpatterns.service.TransactionService;

@RestController
@RequestMapping("transacoes")
public class TransactionRestController {

    @Autowired
    private TransactionService transactionService;

    @GetMapping
    public ResponseEntity<Iterable<Transaction>> buscarTodos() {
        return ResponseEntity.ok(transactionService.getAll());
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Transaction> buscarPorId(@PathVariable long id) {
        return ResponseEntity.ok(transactionService.getById(id));
    }

    @GetMapping("/remetente/{CPF}")
    public ResponseEntity<Iterable<Transaction>> buscarPorRementente(@PathVariable String CPF) {
        return ResponseEntity.ok(transactionService.getBySender(CPF));
    }

    @GetMapping("/destinatario/{CPF}")
    public ResponseEntity<Iterable<Transaction>> buscarPorDestinatario(@PathVariable String CPF) {
        return ResponseEntity.ok(transactionService.getByReceiver(CPF));
    }

    @GetMapping("/data/{dataString}")
    public ResponseEntity<Iterable<Transaction>> buscarPorData(@PathVariable String dataString) {
        return ResponseEntity.ok(transactionService.getByDate(dataString));
    }
    
}
