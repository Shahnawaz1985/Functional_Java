package com.eric.sample.function.email.revised;

import java.util.regex.Pattern;

import com.eric.sample.function.email.verifier.Effect;
import com.eric.sample.function.email.verifier.Result_v1;
import com.eric.sample.function.intrf.Generic_Functional_Intrf;
import static com.eric.sample.function.email.revised.Case.mcase;
import static com.eric.sample.function.email.revised.Case.match;

/**
 * 
 * @author Shahnawaz
 *
 */
public class RevisedEmailValidator {
	
	final static Pattern emailPattern = Pattern.compile("^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$");
	
	static Effect<String> success = s -> System.out.println("Mail sent to :"+ s);
	
	static Effect<String> failure = f -> System.out.println("Error message logged during email send :"+ f);
	
	static Generic_Functional_Intrf<String, Result_v1<String>> emailChecker = s -> match(
				mcase(() -> Result_v1.<String>success(s)),
				mcase(() -> s == null, () -> Result_v1.<String>failure("email must not be null")),
				mcase(() -> s.length() == 0, () -> Result_v1.<String>failure("email must not be empty")),
				mcase(() -> !emailPattern.matcher(s).matches(), () -> Result_v1.<String>failure("email " + s + " is invalid"))
				
			);
			
	public static void main(String[] args) {
		emailChecker.apply("this.is@my.email").bind(success, failure);
		emailChecker.apply(null).bind(success, failure);
		emailChecker.apply("").bind(success, failure);
		emailChecker.apply("test.abc@ace.com").bind(success, failure);
	}	

}
