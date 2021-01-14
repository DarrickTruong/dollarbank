package com.dollarsbank.utility;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;

import com.dollarsbank.model.Customer;

public class DataGeneratorStubUtil implements ColorsUtility{

	public static String formatDollars(BigDecimal balance) {
		
		NumberFormat usdFormat = NumberFormat.getCurrencyInstance(Locale.US);
		usdFormat.setMinimumFractionDigits(2);
		usdFormat.setMaximumFractionDigits(2);
		return usdFormat.format(balance);
		
	}
	
	
	public static String formatTransaction(String transaction) {
		
		String[] arrs = transaction.split(" - ");
		String strs="";
			
			for (String words : arrs) {
				strs += words+"\n";
			}
		
		return strs;
		
	}
	
	
	public static String formatUserInfo(Customer customer) {
		String checking = DataGeneratorStubUtil.formatDollars(customer.getAccount().getBalance());
		String savings = DataGeneratorStubUtil.formatDollars(customer.getSavings().getBalance());
		
		String customerInfo = CYAN + "Customer Profile" + RESET +
							"\nName: " + customer.getName() +
							"\nAddress: " + customer.getAddress() +
							"\nPhone: " + customer.getPhone() +
							"\nUsername: " + customer.getUsername() + 
							"\nPassword: " + customer.passwordHide() + 
							CYAN + "\n\nAccount Info" + RESET +
							"\nChecking Balance: " + GREEN + checking + RESET + 
							"\nSavings Balance: " + GREEN + savings + RESET;
		
		return customerInfo;
		
	}
}
