package com.dollarsbank.model;

import java.io.Serializable;
import java.math.BigDecimal;

public class Account implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private BigDecimal balance;


	public Account(BigDecimal initDeposit) {
		super();
		this.balance = initDeposit;
		
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	@Override
	public String toString() {
		return "Account [balance=" + balance + "]";
	}
	
}
