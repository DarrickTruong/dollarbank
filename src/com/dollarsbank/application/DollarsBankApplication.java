package com.dollarsbank.application;



import java.math.BigDecimal;

import com.dollarsbank.model.Customer;
import com.dollarsbank.utility.FileStorageUtility;


public class DollarsBankApplication {
	public static void main(String[] args) {
		
//		DollarsBankController.welcome();
		
		
		Customer customer = new Customer("Darrick", "San Jose", "4084440865", 
				"givemedarock08", "Hello123!", new BigDecimal(10000));
		Customer customer2 = new Customer("Joey", "San Jose", "4084440865", 
				"givemedarock08", "Bye123!", new BigDecimal(10000));
		
		
//		FileStorageUtility.saveCustomer(customer);
		FileStorageUtility.saveCustomer(customer2);
//
//
//		FileStorageUtility.validateLogin();
	}
}
