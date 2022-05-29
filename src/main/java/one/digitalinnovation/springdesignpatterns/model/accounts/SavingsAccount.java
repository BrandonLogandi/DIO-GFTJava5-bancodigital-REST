package one.digitalinnovation.springdesignpatterns.model.accounts;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

import one.digitalinnovation.springdesignpatterns.model.Client;

@Entity
@Table(name = "savingsAccount")
@DiscriminatorValue("savingsAccount")
public class SavingsAccount extends Account {

	private LocalDate lastYieldDate;

	SavingsAccount() {

	}

	public SavingsAccount(Client client, String password, int branch) {
		super(client, password, branch);
		lastYieldDate = LocalDate.now();
	}

	public void yield(double yieldRate) {
		long days = ChronoUnit.DAYS.between(lastYieldDate, LocalDate.now());

		if (days > 0) {
			double totalYield = 0;

			for (int i = 0; i < days; i++) {
				double yield = super.getBalance() * yieldRate;
				totalYield += yield;
				super.deposit(yield);
			}

			System.out.printf("Conta %s rendeu R$%.2f desde %s\n",
					getClient().getCPF(), totalYield, lastYieldDate);

			lastYieldDate = LocalDate.now();
		}

	}

	@Override
	public void printStatement() {
		System.out.println("=== Extrato Conta Poupanca ===");
		super.printStatement();
	}

}
