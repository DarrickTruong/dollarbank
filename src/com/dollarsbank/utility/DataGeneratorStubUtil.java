package com.dollarsbank.utility;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;

public class DataGeneratorStubUtil {

	public static String formatDollars(BigDecimal balance) {
		NumberFormat usdFormat = NumberFormat.getCurrencyInstance(Locale.US);
		usdFormat.setMinimumFractionDigits(2);
		usdFormat.setMaximumFractionDigits(2);
		return usdFormat.format(balance);
	}
}
