package com.dollarsbank.utility;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import com.dollarsbank.model.Customer;



public class FileStorageUtility {
	
	public static boolean createCustomerFile() {
		File file = new File("resources/customer.data");
		
		if (!file.exists()) {
			try {
				System.out.println("NEW File");
				file.createNewFile();
				return true;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("File could not be created");
				e.printStackTrace();
			}	
		}
		
		return false;
	}	
	
	public static void saveCustomer(Customer customer) {
		boolean first = createCustomerFile();
		if (first) {
			File file = new File("resources/customer.data");
			
			
			System.out.println("Exists " + file.exists());
			
			try (FileOutputStream fos = new FileOutputStream(file);
					ObjectOutputStream oos = new ObjectOutputStream(fos);
					
					FileInputStream fis = new FileInputStream(file);
					ObjectInputStream ois = new ObjectInputStream(fis);
			){
				
				
					
					System.out.println("First");
					ArrayList<Customer> arr = new ArrayList<Customer>();
					arr.add(customer);
					oos.writeObject(arr);
					
				
				
				
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				System.out.println("File could not be found");
			} catch (IOException e1) {
				System.out.println("File could not be written to");
				e1.printStackTrace();
			} 
		} else {
			System.out.println("second");
			appendCustomers(customer);
		}
		
		
	}
	
	public static void appendCustomers(Customer customer) {
		File file = new File("resources/customer.data");
		ArrayList<Customer> customers = null;
		
		try (	FileOutputStream fos = new FileOutputStream(file);
				ObjectOutputStream oos = new ObjectOutputStream(fos);
						
				FileInputStream fis = new FileInputStream(file);
				ObjectInputStream ois = new ObjectInputStream(fis);
		){
			
			customers = (ArrayList<Customer>) ois.readObject();
			for(Customer cust: customers) {
				System.out.println(cust);
			}
			
			customers.add(customer);
			oos.writeObject(customers);
			
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("File could not be found");
		} catch (IOException e1) {
			System.out.println("File could not be written to");
			e1.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	
		
	}
	
	public static void validateLogin() {
		File file = new File("resources/customer.data");
		try (
				FileInputStream fis = new FileInputStream(file);
				ObjectInputStream ois = new ObjectInputStream(fis);
		){
			
			
			ArrayList<Customer> customers = (ArrayList<Customer>) ois.readObject();
			
			
			for (Customer cust : customers) {
				System.out.println(cust.toString());
			}
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("File could not be found");
		} catch (IOException e1) {
			System.out.println("File could not be written to");
			e1.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	

}
