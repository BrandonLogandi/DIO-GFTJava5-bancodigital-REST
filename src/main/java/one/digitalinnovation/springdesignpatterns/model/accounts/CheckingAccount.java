package one.digitalinnovation.springdesignpatterns.model.accounts;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

import one.digitalinnovation.springdesignpatterns.model.Client;

@Entity
@Table(name = "checkingAccount")
@DiscriminatorValue("checkingAccount")
public class CheckingAccount extends Account {

	CheckingAccount() {

	}

	public CheckingAccount(Client client, String password, int branch) {
		super(client, password, branch);
	}

	@Override
	public void printStatement() {
		System.out.println("=== Extrato Conta Corrente ===");
		super.printStatement();
	}

}
