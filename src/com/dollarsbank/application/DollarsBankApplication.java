package com.dollarsbank.application;



import java.util.ArrayList;

import com.dollarsbank.controller.DollarsBankController;


public class DollarsBankApplication {
	public static void main(String[] args) {
		
		DollarsBankController.welcome();
		
		
//		Customer customer = new Customer("Darrick", "San Jose", "4084440865", 
//				"givemedarock08", "Hello123!", new BigDecimal(10000));
//		Customer customer2 = new Customer("Joey", "San Jose", "4084440865", 
//				"givemedarock08", "Bye123!", new BigDecimal(10000));
//		
//		
//		FileStorageUtility.saveCustomer(customer);
//		FileStorageUtility.saveCustomer(customer2);
//		
//		String[] arr = {"givemedarock08", "Hello123!"};
//		System.out.println("validated " + FileStorageUtility.validateLogin(arr));
		
//		ArrayList<String> arr = new ArrayList<String>();
//		arr.add("Initial Deposit - Deposit Amount: $10000 into Checking - Checking Balance: $10000 as on WEDNESDAY - 1/13/2021 08:42:00.192711");
//		arr.add("Deposit Amount into Savings - Deposit Amount: $1000 - Savings Balance: $1000 as on WEDNESDAY - 1/13/2021 08:42:13.312415");
//		
//		String[] arrs;
//		String strs="";
//		for (String str:arr) {
//			arrs = str.split(" - ");
//			
//			for (String words : arrs) {
//				strs += words+"\n";
//			}
//			
//			strs  += "\n----\n";
//		}
//		System.out.println(strs);
//		String str = String.join(", ", arr);
//		
//		System.out.println(str);
//		
//		String[] strArr = str.split(", ");
//		
//		arr = new ArrayList<>(Arrays.asList(strArr));
//		
//		System.out.println(arr.size());
//		System.out.println(DataGeneratorStubUtil.formatBalance(new BigDecimal(1000)));
		
	}
}
