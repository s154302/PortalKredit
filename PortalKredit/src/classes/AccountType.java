package classes;

import java.math.BigDecimal;

public class AccountType {
	private String name;
	private BigDecimal interestRate;
	
	public AccountType(String name, BigDecimal interestRate){
		this.setName(name);
		this.setInterestRate(interestRate);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getInterestRate() {
		return interestRate;
	}

	public void setInterestRate(BigDecimal interestRate) {
		this.interestRate = interestRate;
	}
}
