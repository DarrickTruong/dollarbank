package com.dollarsbank.controller;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

import com.dollarsbank.model.Customer;
import com.dollarsbank.utility.ColorsUtility;
import com.dollarsbank.utility.ConsolePrinterUtility;
import com.dollarsbank.utility.DataGeneratorStubUtil;
import com.dollarsbank.utility.FileStorageUtility;

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
					transactions.add("Initial Deposit - Deposit Amount: " + deposit + " into Checking - \nChecking Balance: " + balance + 
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
							
							customerPage(customer, formattedDate);
							
						} else {
							System.out.println(RED + "Invalid Login Credentials, Please Try again");
						}
					}
					
					
				
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
					
					boolean accountValid = false;
					String account= "";
					while(!accountValid) {
						
//						get account and amount to deposit
						account = ConsolePrinterUtility.depositAccount(sc);
						if (account.equals("1") || account.equals("2")) {
							accountValid = true;
						} else {
							System.out.println(RED + "Invalid Selection. Expecting 1 or 2" + RESET);
						}
					}
					
					
					BigDecimal dep = ConsolePrinterUtility.depositAmount(sc);
					String deposit = DataGeneratorStubUtil.formatDollars(dep);
					
//					deposit to checking
					if (account.equals("1")) {
//						BigDecimal.add() adds deposit to current balance
						BigDecimal checking = customer.getAccount().getBalance();
						customer.getAccount().setBalance(checking.add(dep));
						
						String balance = DataGeneratorStubUtil.formatDollars(customer.getAccount().getBalance());
						
						transactions.add("Deposit into Checking - Deposit Amount: " + deposit + " - \nChecking Balance: " + balance + 
									" - as on " + formattedDate + " " + LocalTime.now());
				
						
//					deposit to savings	
					} else if (account.equals("2")) {
						BigDecimal savings = customer.getSavings().getBalance();
						customer.getSavings().setBalance(savings.add(dep));
						
						String balance = DataGeneratorStubUtil.formatDollars(customer.getSavings().getBalance());
						transactions.add("Deposit into Savings - Deposit Amount: " + deposit + " - \nSavings Balance: " + balance + 
									" - as on " + formattedDate + " " + LocalTime.now());

					} else {
						
					}
					
					
					
//				Withdraw
				} else if (selection.equals("2")) {
					
					boolean accountValid = false;
					String account= "";
					while(!accountValid) {
						
//						get account and amount to deposit
						account = ConsolePrinterUtility.withdrawAccount(sc);
						if (account.equals("1") || account.equals("2")) {
							accountValid = true;
						} else {
							System.out.println(RED + "Invalid Selection. Expecting 1 or 2" + RESET);
						}
					}
					
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
							transactions.add("Withdraw from Checking - Withdraw Amount: " + withdraw + " - \nChecking Balance: " + 
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
												" - \nSavings Balance: " + balance + " - as on " 
												+ formattedDate + " " + LocalTime.now());


					}
						customer.setTransactions(transactions);	
						
					}
					
//				transfer funds
				} else if (selection.equals("3")) {
					boolean accountValid = false;
					String account= "";
					while(!accountValid) {
						
//						get account and amount to deposit
						account = ConsolePrinterUtility.transferAccount(sc);
						if (account.equals("1") || account.equals("2")) {
							accountValid = true;
						} else {
							System.out.println(RED + "Invalid Selection. Expecting 1 or 2" + RESET);
						}
					}
//					get account and amount to transfer from
					
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
							transactions.add("Transfer funds from Checking->Savings - Transfer Amount: " + 
												transferAmt + " - \nChecking Acct Balance: " + GREEN + checking + RESET + 
												" - Savings Acct Balance: " + GREEN + savings + RESET + 
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
							transactions.add("Transfer funds from Savings->Checking - Transfer Amount: " + 
												transferAmt + " - \nSavings Acct Balance: " + savings + 
												" - Checking Acct Balance: " + checking + 
												" - as on " + formattedDate + " " + LocalTime.now());
		
							
							customer.setTransactions(transactions);
						}
					}
					
					
//				Last 5 transactions
				} else if (selection.equals("4")) {
					ConsolePrinterUtility.lastFiveTrans(transactions);
//					ConsolePrinterUtility.printTransaction(transactions);
						
					
				
//				Customer Information
				} else if (selection.equals("5")) {
					String formattedUserInfo = DataGeneratorStubUtil.formatUserInfo(customer);
					
					ConsolePrinterUtility.customerInformation(formattedUserInfo);
				
					
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
