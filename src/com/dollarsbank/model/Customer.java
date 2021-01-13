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
	
	public Customer(String name, String address, String phone, String userName, String password, BigDecimal initDeposit, BigDecimal initSavings) {
		super();
		this.name = name;
		this.address = address;
		this.phone = phone;
		this.userName = userName;
		this.password = password;
		this.checking = new CheckingAccount(initDeposit);
		this.savings = new SavingsAccount(initSavings);

	}
	
//	constructor to generate customer from file after login
	public Customer(String name, String address, String phone, String userName, String password, BigDecimal initDeposit, BigDecimal initSavings, ArrayList<String> transactions) {
		super();
		this.name = name;
		this.address = address;
		this.phone = phone;
		this.userName = userName;
		this.password = password;
		this.checking = new CheckingAccount(initDeposit);
		this.savings = new SavingsAccount(initSavings);
		this.transactions = transactions;
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
	
	public String toFileString() {
//		convert to string to store in file, convert string to arraylist after login
		String transactionsString = String.join(", ", transactions);
		return "" + name +"&"+ address +"&"+ phone +"&"+ userName +"&"+ password +"&"+ checking.getBalance() +"&"+ savings.getBalance() +"&"+ transactionsString;
	}
	

	@Override
	public String toString() {
		return "Customer [name=" + name + ", address=" + address + ", phone=" + phone + ", userName=" + userName
				+ ", password=" + password + ", checking=" + checking + ", savings=" + savings + "]";
	}

	

	
	
}
