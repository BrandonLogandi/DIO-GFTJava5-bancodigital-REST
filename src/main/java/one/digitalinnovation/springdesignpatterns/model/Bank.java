package one.digitalinnovation.springdesignpatterns.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class Bank {

	@Id
	private String name;
	private int defaultBranch = 1;
	private double yieldRate = 0.00028d;

	Bank() {

	}

	public Bank(String name) {
		this.name = name;
	}

}
