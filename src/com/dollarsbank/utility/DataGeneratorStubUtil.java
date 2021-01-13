package com.dollarsbank.utility;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class DataGeneratorStubUtil {

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
}
