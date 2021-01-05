package com.eric.sample.function.email.verifier;

import java.util.regex.Pattern;

import com.eric.sample.function.intrf.Generic_Functional_Intrf;

public class EmailValidator_v3 {
	
	final static Pattern emailPattern = Pattern.compile("^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$");
	
	private static Generic_Functional_Intrf<String, Result> emailChecker = s ->
			s == null
				? new Result.Failure("email must not be null!")
				: s.length() == 0
						? new Result.Failure("email must not be empty!")
						: emailPattern.matcher(s).matches()
									? new Result.Success()
									: new Result.Failure("email " + s + " is invalid!");
									
									
	private static void sendVerificationEmail(String emailContent) {
		System.out.println("Verification email sent to "+emailContent);
	}
									
	private static void logError(String errorMsg) {
		System.out.println("Error message logged: "+errorMsg);
	}
					
	static Executable validate(String emailContent) {
		Result result = emailChecker.apply(emailContent);
		
		return (result instanceof Result.Success) ? () -> sendVerificationEmail(emailContent) : () -> logError(((Result.Failure)result).getMessage());
	}
	
	
	public static void main(String[] args) {
		validate("this.is@my.email").exec();;
		validate(null).exec();;
		validate("").exec();;
		validate("john.doe@gmail.com").exec();;
	}

}
