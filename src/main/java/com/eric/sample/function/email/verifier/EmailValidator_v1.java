package com.eric.sample.function.email.verifier;

import java.util.regex.Pattern;

import com.eric.sample.function.intrf.Generic_Functional_Intrf;

public class EmailValidator_v1 {
	
	final static Pattern emailPattern = Pattern.compile("^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$");
	
	final static Generic_Functional_Intrf<String, Boolean> emailChecker = s -> emailPattern.matcher(s).matches();
	
	private static void testMail(String email) {
		if(emailChecker.apply(email)) {
			sendVerificationEmail(email);
		} else {
			logError(email);
		}
	}
	
	private static void sendVerificationEmail(String emailContent) {
		System.out.println("Verification email sent to "+emailContent);
	}
	
	private static void logError(String errorMsg) {
		System.out.println("Error message logged: "+errorMsg);
	}
	
	public static void main(String[] args) {
		testMail("john.doe@gmail.com");
		testMail(null);
	}

}
