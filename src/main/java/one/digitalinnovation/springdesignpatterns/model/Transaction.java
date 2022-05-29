package one.digitalinnovation.springdesignpatterns.model;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Getter;
import one.digitalinnovation.springdesignpatterns.model.accounts.Account;

import java.time.LocalDate;

@Entity
@Getter
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @ManyToOne
    private Account sender;
    @ManyToOne
    private Account receiver;
    private double value;
    private LocalDate date;

    public Transaction() {

    }

    public Transaction(double value, Account sender) {
        this.value = value;
        this.sender = sender;
        this.date = LocalDate.now();
    }

    public Transaction(double value, Account sender, Account receiver) {
        this(value, sender);
        this.receiver = receiver;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Transaction)) {
            return false;
        }
        Transaction transaction = (Transaction) o;
        return value == transaction.value && Objects.equals(sender, transaction.sender)
                && Objects.equals(receiver, transaction.receiver) && date == transaction.date;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, sender, receiver, date);
    }

    @Override
    public String toString() {
        return String.format("TRANSACAO: {Valor: R$%.2f, Remetente: %s, Destinatario: %s, Data: '%s'}", value,
                sender, receiver, date);
    }

}
