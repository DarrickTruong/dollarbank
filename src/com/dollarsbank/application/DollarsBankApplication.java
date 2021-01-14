package com.dollarsbank.application;



import com.dollarsbank.controller.DollarsBankController;


public class DollarsBankApplication {
	public static void main(String[] args) {

//		Notes: 
//			Application only takes one user at the moment because of file writing implementation, couldn't figure out objectoutputstream. can login and logoff

//		Improvements:
//			use dao/jdbc for database next time
//			transfer money between accounts only. implement transfer between users with dao/jdbc implementation
//			use javabeans instead of static methods - consoleprinterutility, filestorageutility, etc. 
//			validate dollar to $xx.xx format
		
		DollarsBankController.welcome();
		
	}
}
