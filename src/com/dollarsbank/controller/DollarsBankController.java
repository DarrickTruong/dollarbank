package com.dollarsbank.controller;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Scanner;

import com.dollarsbank.model.Customer;
import com.dollarsbank.utility.ColorsUtility;
import com.dollarsbank.utility.ConsolePrinterUtility;
import com.dollarsbank.utility.DataGeneratorStubUtil;
import com.dollarsbank.utility.FileStorageUtility;
import com.dollarsbank.utility.InputValidation;

public class DollarsBankController implements ColorsUtility{
	

	
	public static void welcome() {
		Scanner sc = new Scanner(System.in);
		LocalDateTime today = LocalDateTime.now();
		
		String formattedDate = today.getDayOfWeek() + " " + today.getMonthValue() + "/" + 
								today.getDayOfMonth() + "/" + today.getYear();
		
		
		
		
		String[] custArr;
		Customer customer = null;
		boolean finished = false;
		
		while(!finished) {
			try {
				ConsolePrinterUtility.welcome();
				System.out.println(YELLOW + "\nEnter Selection (1, 2, 3)" + RESET);
				String selection = sc.nextLine();
				
//				Create customer account
				if (selection.equals("1")) {
					
					custArr = ConsolePrinterUtility.createAccount(sc);
					BigDecimal dep = ConsolePrinterUtility.initialDeposit(sc);
					
					customer = new Customer(custArr[0], custArr[1], custArr[2], custArr[3], custArr[4], dep, new BigDecimal(0));
					
					
					String balance = DataGeneratorStubUtil.formatDollars(customer.getAccount().getBalance());
					String deposit = DataGeneratorStubUtil.formatDollars(dep);

					
					ArrayList<String> transactions = new ArrayList<String>();
					transactions.add("Initial Deposit - Deposit Amount: " + deposit + " into Checking - Checking Balance: " + balance + 
									" - as on " + formattedDate + " " + LocalTime.now());
					
					customer.setTransactions(transactions);
					
					FileStorageUtility.saveCustomer(customer);
					
					customerPage(customer, formattedDate);
					
//					Login
				} else if (selection.equals("2")) {
					boolean validated = false;
					
					
					while(!validated) {
						
						String[] login = ConsolePrinterUtility.login(sc);
						
	//					Validate login here
						String[] valid = FileStorageUtility.validateLogin(login);
						if (valid.length == 8) {
							
//							convert string to arraylist for transactions
//							System.out.println("transactions string "+ valid[7]);
							String[] trans = valid[7].split(", ");
							ArrayList<String> transactions = new ArrayList<>(Arrays.asList(trans));
							
							
							customer = new Customer(valid[0], valid[1], valid[2], valid[3], 
													valid[4], new BigDecimal(valid[5]), 
													new BigDecimal(valid[6]), transactions);
							validated = true; 
							
							
						} else {
							System.out.println("Invalid Login Credentials, Please Try again");
						}
					}
					
					customerPage(customer, formattedDate);
				
				} else if (selection.equals("3")) {
					System.out.println("\nClosing Bank App, Good Bye!");
					System.exit(0);

				} else {
					System.out.println(RED + "Invalid input, try again." + RESET);
					welcome();
				}
				
			} catch (InputMismatchException e){
				System.out.println(RED + "Invalid input, expecting number");
			}
			
		}	
		sc.close();
	}
	
	public static void customerPage(Customer customer, String formattedDate) {
		Scanner sc = new Scanner(System.in);
		ArrayList<String> transactions = customer.getTransactions();
		boolean finished = false;
		
		while(!finished) {
			try {
				ConsolePrinterUtility.customerPage();
				System.out.println(YELLOW + "Enter Selection (1, 2, 3, 4, 5, 6)" + RESET);
				String selection = sc.nextLine();

					
//				Deposit
				if (selection.equals("1")) {
					
//					get account and amount to deposit
					String account = ConsolePrinterUtility.depositAccount(sc);
					BigDecimal dep = ConsolePrinterUtility.depositAmount(sc);
					String deposit = DataGeneratorStubUtil.formatDollars(dep);
					
//					transfer to checking
					if (account.equals("1")) {
//						BigDecimal.add() adds deposit to current balance
						BigDecimal checking = customer.getAccount().getBalance();
						customer.getAccount().setBalance(checking.add(dep));
						
						String balance = DataGeneratorStubUtil.formatDollars(customer.getAccount().getBalance());
						
						transactions.add("Deposit into Checking - Deposit Amount: " + deposit + " - Checking Balance: " + balance + 
									" - as on " + formattedDate + " " + LocalTime.now());
				
						
//					deposit to savings	
					} else if (account.equals("2")) {
						BigDecimal savings = customer.getSavings().getBalance();
						customer.getSavings().setBalance(savings.add(dep));
						
						String balance = DataGeneratorStubUtil.formatDollars(customer.getSavings().getBalance());
						transactions.add("Deposit into Savings - Deposit Amount: " + deposit + " - Savings Balance: " + balance + 
									" - as on " + formattedDate + " " + LocalTime.now());

						
					}
					
//				Withdraw
				} else if (selection.equals("2")) {
					String account = ConsolePrinterUtility.withdrawAccount(sc);
					BigDecimal withd = ConsolePrinterUtility.withdrawAmount(sc);
					String withdraw = DataGeneratorStubUtil.formatDollars(withd);
					
//					withdraw from checking
					if (account.equals("1")) {
						
						BigDecimal checkingBalance = customer.getAccount().getBalance();
						
						if (withd.compareTo(checkingBalance) == 1) {
							System.out.println(RED + "Insufficient funds for withdrawal amount");
						} else {
							
							customer.getAccount().setBalance(checkingBalance.subtract(withd));
							
							String balance = DataGeneratorStubUtil.formatDollars(customer.getAccount().getBalance());
							transactions.add("Withdraw from Checking - Withdraw Amount: " + withdraw + " - Checking Balance: " + 
												balance + " - as on " + formattedDate + " " + LocalTime.now());
							

						}
						
//					withdraw from savings
					} else if (account.equals("2")) {
						
						BigDecimal savingsBalance = customer.getSavings().getBalance();
						
						if (withd.compareTo(savingsBalance) == 1) {
							System.out.println(RED + "Insufficient funds for withdrawal amount");
						
						} else {
							customer.getSavings().setBalance(savingsBalance.subtract(withd));;
							
							String balance = DataGeneratorStubUtil.formatDollars(customer.getSavings().getBalance());
							transactions.add("Withdraw from Savings - Withdraw Amount: " + withdraw + 
												" - Savings Balance: " + balance + " - as on " 
												+ formattedDate + " " + LocalTime.now());


					}
						customer.setTransactions(transactions);
						
						
					}
					
//				transfer funds
				} else if (selection.equals("3")) {
					
//					get account and amount to transfer from
					String account = ConsolePrinterUtility.transferAccount(sc);
					BigDecimal transfer =  new BigDecimal(ConsolePrinterUtility.transferAmount(sc));
					String transferAmt = DataGeneratorStubUtil.formatDollars(transfer);
					
					
//					1 = transfer from checking to savings
					if (account.equals("1")) {
						
						if (transfer.compareTo(customer.getAccount().getBalance()) == 1) {
							System.out.println(RED + "Insufficient funds for withdrawal amount");
						} else {
							BigDecimal checkingsBalance = customer.getAccount().getBalance();
							BigDecimal savingsBalance = customer.getSavings().getBalance();
							
							customer.getAccount().setBalance(checkingsBalance.subtract(transfer));
							customer.getSavings().setBalance(savingsBalance.add(transfer));
							
							String checking = DataGeneratorStubUtil.formatDollars(customer.getAccount().getBalance());
							String savings = DataGeneratorStubUtil.formatDollars(customer.getSavings().getBalance());
							transactions.add("Transfer funds from Checking->Savings - Transfer Amount: $" + 
												transferAmt + " - Checking Acct Balance: " + checking + 
												" - Savings Acct Balance: $" + savings + 
												" - as on " + formattedDate + " " + LocalTime.now());

							
							customer.setTransactions(transactions);
						}
						
//					2 = transfer from savings to checking
					} else if (account.equals("2")) {
						
						if (transfer.compareTo(customer.getSavings().getBalance()) == 1) {
							System.out.println(RED + "Insufficient funds for withdrawal amount");
						} else {
							
							BigDecimal savingsBalance = customer.getSavings().getBalance();
							BigDecimal checkingsBalance = customer.getAccount().getBalance();
							
							customer.getSavings().setBalance(savingsBalance.subtract(transfer));
							customer.getAccount().setBalance(checkingsBalance.add(transfer));
							
							String checking = DataGeneratorStubUtil.formatDollars(customer.getAccount().getBalance());
							String savings = DataGeneratorStubUtil.formatDollars(customer.getSavings().getBalance());
							transactions.add("Transfer funds from Savings->Checking - Transfer Amount: $" + 
												transferAmt + " - Savings Acct Balance: " + savings + 
												" - Checking Acct Balance: $" + checking + 
												" - as on " + formattedDate + " " + LocalTime.now());
		
							
							customer.setTransactions(transactions);
						}
					}
					
//				Last 5 transactions
				} else if (selection.equals("4")) {
					ConsolePrinterUtility.lastFiveTrans();
					
					
					int i = 0;
					int j = transactions.size()-1;
					while (i < 5 && j >=0) {
						
						String transaction = DataGeneratorStubUtil.formatTransaction(transactions.get(j));
						System.out.println(i+1 +". " + transaction);
						
						System.out.println("\n--------------------------------------------\n");
						i++;
						j--;
					}
					
					
					
				
//				Customer Information
				} else if (selection.equals("5")) {
					System.out.println(customer.toString());
					
//				Log off	
				} else if (selection.equals("6")) {
					
					FileStorageUtility.saveCustomer(customer);
					
					System.out.println("Logging off...");
					finished = true;
				} 
				
				
			} catch (InputMismatchException e){
				System.out.println(RED + "Invalid input, expecting number");
			}
		}
		
	}
	

}
