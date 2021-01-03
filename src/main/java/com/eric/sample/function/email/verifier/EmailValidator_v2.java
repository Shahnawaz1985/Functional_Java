package com.eric.sample.function.email.verifier;

import java.util.regex.Pattern;

import com.eric.sample.function.intrf.Generic_Functional_Intrf;

public class EmailValidator_v2 {
	
	final static Pattern emailPattern = Pattern.compile("^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$");
	
	private static Generic_Functional_Intrf<String, Result> emailChecker = s -> {
		
		if(s == null) {
			return new Result.Failure("email must not be null!");
		}else if(s.length() == 0){
			return new Result.Failure("email must not be empty");
		}else if(emailPattern.matcher(s).matches()) {
			return new Result.Success();
		}else {
			return new Result.Failure("email " + s + " is invalid!");
		}
	};
	
	private static void sendVerificationEmail(String emailContent) {
		System.out.println("Verification email sent to "+emailContent);
	}
	
	private static void logError(String errorMsg) {
		System.out.println("Error message logged: "+errorMsg);
	}
	
	private static void validate(String email) {
		Result result = emailChecker.apply(email);
		
		if(result instanceof Result.Success) {
			sendVerificationEmail(email);
		}else {
			logError(((Result.Failure)result).getMessage());
		}
	}
	
	public static void main(String[] args) {
		validate("this.is@my.email");
		validate(null);
		validate("");
		validate("john.doe@gmail.com");
	}

}
