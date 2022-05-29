package one.digitalinnovation.springdesignpatterns.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Data;

@Data
@Entity
public class Client {

	@Id
	@Column(length = 14)
	private String CPF;
	private String name;
	@ManyToOne
	private Address address;

}
