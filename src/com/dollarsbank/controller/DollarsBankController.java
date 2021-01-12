package com.dollarsbank.controller;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import com.dollarsbank.model.Customer;
import com.dollarsbank.utility.ColorsUtility;
import com.dollarsbank.utility.ConsolePrinterUtility;

public class DollarsBankController implements ColorsUtility{
	

	
	public static void welcome() {
		Scanner sc = new Scanner(System.in);
		
		LocalDateTime today = LocalDateTime.now();
		
		String formattedDate = today.getDayOfWeek() + ", " + today.getMonthValue() + "/" + 
								today.getDayOfMonth() + "/" + today.getYear();
		
		
		String[] custArr;
		Customer customer = null;
		boolean finished = false;
		
		while(!finished) {
			try {
				ConsolePrinterUtility.welcome();
				System.out.println(YELLOW + "\nEnter Selection (1, 2, 3)" + RESET);
				String selection = sc.nextLine();
				
				if (selection.equals("1")) {

					custArr = ConsolePrinterUtility.createAccount(sc);
					String password = ConsolePrinterUtility.createPassword(sc);
					BigDecimal deposit = ConsolePrinterUtility.initialDeposit(sc);
					
					customer = new Customer(custArr[0], custArr[1], custArr[2], custArr[3], custArr[4], deposit);
					
					ArrayList<String> transactions = new ArrayList<String>();
					transactions.add("Initial Deposit:" + 
									"\nDeposit Amount: $" + deposit + " into Checking" + 
					                "\nChecking Balance: $" + customer.getAccount().getBalance() + 
					                "\nas on " + formattedDate + " " + LocalTime.now());
					customer.setTransactions(transactions);
					customerPage(customer);
					
				} else if (selection.equals("2")) {
					String[] login = ConsolePrinterUtility.login(sc);
//					Validate login here
				
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
	
	public static void customerPage(Customer customer) {
		
		Scanner sc = new Scanner(System.in);
		ArrayList<String> transactions = customer.getTransactions();
		LocalDateTime today = LocalDateTime.now();
		
		String formattedDate = today.getDayOfWeek() + ", " + today.getMonthValue() + "/" + 
								today.getDayOfMonth() + "/" + today.getYear();
		
		boolean finished = false;
		
		while(!finished) {
			try {
				ConsolePrinterUtility.customerPage();
				System.out.println(YELLOW + "Enter Selection (1, 2, 3, 4, 5, 6)" + RESET);
				String selection = sc.nextLine();

					
//				Deposit
				if (selection.equals("1")) {
					
					String account = ConsolePrinterUtility.depositAccount(sc);
					BigDecimal deposit = ConsolePrinterUtility.depositAmount(sc);
					
					if (account.equals("1")) {
//						BigDecimal.add() adds current with deposit
						BigDecimal checking = customer.getAccount().getBalance();
						customer.getAccount().setBalance(checking.add(deposit));
						
						
						
						transactions.add("Deposit Amount into Checking:" +
								"\nDeposit Amount: $" + deposit +
								"\nChecking Balance: $" + customer.getAccount().getBalance() +
								"\nas on " + formattedDate + " " + LocalTime.now());
						
						
					} else if (account.equals("2")) {
						BigDecimal savings = customer.getSavings().getBalance();
						customer.getSavings().setBalance(savings.add(deposit));
						
						transactions.add("Deposit Amount into Savings:" +
								"\nDeposit Amount: $" + deposit +
								"\nSavings Balance: $" + customer.getSavings().getBalance() +
								"\nas on " + formattedDate + " " + LocalTime.now());
						
					}
					
				
					
//				Withdraw
				} else if (selection.equals("2")) {
					String account = ConsolePrinterUtility.withdrawAccount(sc);
					BigDecimal withdraw = ConsolePrinterUtility.withdrawAmount(sc);
					
					
					if (account.equals("1")) {
						
						BigDecimal checkingBalance = customer.getAccount().getBalance();
						
						if (withdraw.compareTo(checkingBalance) == 1) {
							System.out.println(RED + "Insufficient funds for withdrawal amount");
						} else {
							customer.getAccount().setBalance(checkingBalance.subtract(withdraw));
							transactions.add("Withdraw from Checking:" +
								    "\nWithdraw Amount: $" + withdraw +
									"\nChecking Balance: $" + customer.getAccount().getBalance() +
									"\nas on " + formattedDate + " " + LocalTime.now());
						}
						
						
						
					} else if (account.equals("2")) {
						
						BigDecimal savingsBalance = customer.getSavings().getBalance();
						
						if (withdraw.compareTo(savingsBalance) == 1) {
							System.out.println(RED + "Insufficient funds for withdrawal amount");
						
						} else {
							customer.getSavings().setBalance(savingsBalance.subtract(withdraw));;
							
							transactions.add("Withdraw from Savings:" +
									"\nWithdraw Amount: $" + withdraw +
									"\nSavings Balance: $" + customer.getSavings().getBalance() +
									"\nas on " + formattedDate + " " + LocalTime.now());
					}
						customer.setTransactions(transactions);
						
						
					}
					
				
				} else if (selection.equals("3")) {
					
					String account = ConsolePrinterUtility.transferAccount(sc);
					BigDecimal transferAmt =  new BigDecimal(ConsolePrinterUtility.transferAmount(sc));
					
					
//					1 = transfer from checking to savings
					if (account.equals("1")) {
						
						if (transferAmt.compareTo(customer.getAccount().getBalance()) == 1) {
							System.out.println(RED + "Insufficient funds for withdrawal amount");
						} else {
							BigDecimal checkingsBalance = customer.getAccount().getBalance();
							BigDecimal savingsBalance = customer.getSavings().getBalance();
							
							customer.getAccount().setBalance(checkingsBalance.subtract(transferAmt));
							customer.getSavings().setBalance(savingsBalance.add(transferAmt));
							
							transactions.add("Transfer funds from Checking->Savings" +
											"\nTransfer Amount: $" + transferAmt +
											"\nChecking Acct Balance: $" + customer.getAccount().getBalance() +
											"\nSavings Acct Balance: $" + customer.getSavings().getBalance() + 
							                "\nas on " + formattedDate + " " + LocalTime.now());
							
							customer.setTransactions(transactions);
						}
//					
					} else if (account.equals("2")) {
						
						if (transferAmt.compareTo(customer.getSavings().getBalance()) == 1) {
							System.out.println(RED + "Insufficient funds for withdrawal amount");
						} else {
							
							BigDecimal savingsBalance = customer.getSavings().getBalance();
							BigDecimal checkingsBalance = customer.getAccount().getBalance();
							
							customer.getSavings().setBalance(savingsBalance.subtract(transferAmt));
							customer.getAccount().setBalance(checkingsBalance.add(transferAmt));
							
							transactions.add("Transfer funds from Savings->Checking" +
									"\nTransfer Amount: $" + transferAmt +
									"\nChecking Acct Balance: $" + customer.getAccount().getBalance() +
									"\nSavings Acct Balance: $" + customer.getSavings().getBalance() +
									"\nas on " + formattedDate + " " + LocalTime.now());
							
							customer.setTransactions(transactions);
						}
					}
					
					
				
				} else if (selection.equals("4")) {
					ConsolePrinterUtility.lastFiveTrans();
					int i = 0;
					int j = transactions.size()-1;
					while (i < 5 && j >=0) {
						
						System.out.println(i+1 +". " + transactions.get(j));
						
						System.out.println("\n---------------------------------\n");
						i++;
						j--;
					}
				
				} else if (selection.equals("5")) {
					System.out.println(customer.toString());
					
				} else if (selection.equals("6")) {
					
					System.out.println("Logging off...");
					finished = true;
				} 
				
				
			} catch (InputMismatchException e){
				System.out.println(RED + "Invalid input, expecting number");
				sc.close();
			}
		}
	}
	

}
