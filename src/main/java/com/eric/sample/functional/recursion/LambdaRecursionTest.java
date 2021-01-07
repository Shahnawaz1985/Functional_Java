package com.eric.sample.functional.recursion;

import com.eric.sample.function.intrf.Generic_Functional_Intrf;
import com.eric.sample.functional.recursion.TailCall.Suspend;

/**
 * 
 * @author Shahnawaz
 *
 */
public class LambdaRecursionTest {
	
	/**
	 * Instead of referring to the anonymous class instance, the this reference used in a lambda refers to the enclosing instance.
	 */
	private static Generic_Functional_Intrf<Integer, Generic_Functional_Intrf<Integer, Integer>> add_lambda = x -> y -> {
		
		class AddHelper{
			
			Generic_Functional_Intrf<Integer, Generic_Functional_Intrf<Integer, TailCall<Integer>>> addHelper = 
					
					a -> b -> b == 0
								? Suspend.ret(a)
										: Suspend.sus(() -> this.addHelper.apply(a+1).apply(b-1));
		}
		
		return new AddHelper().addHelper.apply(x).apply(y).eval();
		
	};
	
	public static void main(String[] args) {		
		System.out.println("Recursion using lambda:");
		int lambda_test_result = add_lambda.apply(3).apply(1000000);
		System.out.println("Result of lambda test : "+lambda_test_result);
		
	}

}
