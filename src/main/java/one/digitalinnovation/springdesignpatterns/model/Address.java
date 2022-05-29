package one.digitalinnovation.springdesignpatterns.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class Address {

	@Id
	@Column(length = 9)
	private String cep;
	private String logradouro;
	private String complemento;
	private String bairro;
	private String localidade;
	@Column(length = 2)
	private String uf;
	private String ibge;
	private String gia;
	@Column(length = 2)
	private int ddd;
	private String siafi;

}
