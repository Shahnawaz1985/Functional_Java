package com.eric.sample.function.email.verifier;

import java.util.regex.Pattern;

import com.eric.sample.function.intrf.Generic_Functional_Intrf;

public class EmailValidator_v4 {

	final static Pattern emailPattern = Pattern.compile("^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$");
	
	private static Effect<String> success = s -> System.out.println("Email sent to " + s);
	
	private static Effect<String> failure = s -> System.out.println("Error message logged: "+s);

	private static Generic_Functional_Intrf<String, Result_v1<String>> emailChecker = s -> {

		if (s == null) {
			return Result_v1.<String>failure("email must not be null!");
		} else if (s.length() == 0) {
			return Result_v1.<String>failure("email must not be empty");
		} else if (emailPattern.matcher(s).matches()) {
			return Result_v1.<String>success(s);
		} else {
			return Result_v1.<String>failure("email " + s + " is invalid!");
		}
	};
	
	public static void main(String[] args) {
		emailChecker.apply("this.is@my.email").bind(success, failure);
		emailChecker.apply(null).bind(success, failure);
		emailChecker.apply("").bind(success, failure);
		emailChecker.apply("john.doe@acme.com").bind(success, failure);
	}

}
