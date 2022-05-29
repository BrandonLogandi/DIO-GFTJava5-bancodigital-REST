package one.digitalinnovation.springdesignpatterns.model.accounts;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;
import one.digitalinnovation.springdesignpatterns.model.Transaction;
import one.digitalinnovation.springdesignpatterns.model.Client;

@Getter
@Entity
@Table(name = "account")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "accountType", discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue("account")
public class Account {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	protected int branch;
	protected double balance;
	@OneToOne
	protected Client client;
	protected @Setter String password;
	@OneToMany
	@JsonIgnore
	protected List<Transaction> transactions = new ArrayList<>();

	Account() {

	}

	protected Account(Client client, String password, int branch) {
		this.branch = branch;
		this.client = client;
		this.password = password;
	}

	public void withdraw(double value) throws Exception {
		if ((balance - value) < 0)
			throw new Exception(String.format("Saldo insuficiente (R$%.2f)", balance));

		balance -= value;
	}

	public void deposit(double value) {
		balance += value;
	}

	public void transfer(double value, Account receiver) throws Exception {
		this.withdraw(value);
		receiver.deposit(value);
	}

	public void printStatement() {
		for (Transaction transaction : transactions) {
			System.out.println(transaction.toString());
		}
	}

	@Override
	public boolean equals(Object o) {
		if (o == this)
			return true;
		if (!(o instanceof Account)) {
			return false;
		}
		Account account = (Account) o;
		return branch == account.branch && id == account.id;
	}

	@Override
	public int hashCode() {
		return Objects.hash(branch, id);
	}

	@Override
	public String toString() {
		return String.format(
				"CONTA { Agencia: '%d', Numero: '%d', Titular: '%s', Saldo: R$%.2f}",
				getBranch(), getId(), getClient().getCPF(), getBalance());
	}

}
