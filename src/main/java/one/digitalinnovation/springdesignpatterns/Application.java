package one.digitalinnovation.springdesignpatterns;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import one.digitalinnovation.springdesignpatterns.model.Bank;
import one.digitalinnovation.springdesignpatterns.model.accounts.SavingsAccount;
import one.digitalinnovation.springdesignpatterns.repository.BankRepository;
import one.digitalinnovation.springdesignpatterns.repository.accounts.SavingsAccountRepository;

@EnableFeignClients
@SpringBootApplication
public class Application {

	@Autowired
	private BankRepository bankRepository;
	@Autowired
	private SavingsAccountRepository savingsAccountRepository;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public ApplicationRunner createBank() {
		return (args) -> {
			if (!bankRepository.findById("Banco DIO").isPresent()) 
				bankRepository.save(new Bank("Banco DIO"));
		};
	}

	@Bean
	public ApplicationRunner yieldAccounts() {
		return (args) -> {
			Iterable<SavingsAccount> accounts = savingsAccountRepository.findAll();
			for (SavingsAccount savingsAccount : accounts) {
				savingsAccount.yield(
					bankRepository.findById("Banco DIO").get().getYieldRate()
				);
			}
			savingsAccountRepository.saveAll(accounts);
		};
	}

}
