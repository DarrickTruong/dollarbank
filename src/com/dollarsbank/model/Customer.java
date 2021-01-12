package com.dollarsbank.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;

public class Customer implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String name;
	private String address;
	private String phone;
	private String userName;
	private String password;
	private CheckingAccount checking;
	private SavingsAccount savings;
	private ArrayList<String> transactions;
	
	public Customer(String name, String address, String phone, String userName, String password, BigDecimal initDeposit) {
		super();
		this.name = name;
		this.address = address;
		this.phone = phone;
		this.userName = userName;
		this.password = password;
		this.checking = new CheckingAccount(initDeposit);
		this.savings = new SavingsAccount(new BigDecimal(0));
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getUsername() {
		return userName;
	}

	public void setUsername(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public CheckingAccount getAccount() {
		return checking;
	}

	public void setAccount(CheckingAccount account) {
		this.checking = account;
	}

	public SavingsAccount getSavings() {
		return savings;
	}

	public void setSavings(SavingsAccount savings) {
		this.savings = savings;
	}
	
	public ArrayList<String> getTransactions() {
		return transactions;
	}

	public void setTransactions(ArrayList<String> transactions) {
		this.transactions = transactions;
	}

	@Override
	public String toString() {
		return "Customer [name=" + name + ", address=" + address + ", phone=" + phone + ", userName=" + userName
				+ ", password=" + password + ", checking=" + checking + ", savings=" + savings + "]";
	}

	

	
	
}
