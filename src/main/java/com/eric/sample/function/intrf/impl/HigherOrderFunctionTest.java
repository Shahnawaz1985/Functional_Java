package com.eric.sample.function.intrf.impl;

import com.eric.sample.function.intrf.Generic_Functional_Intrf;

public class HigherOrderFunctionTest {
	
	public static void main(String[] args) {
		
		Generic_Functional_Intrf<Double, Generic_Functional_Intrf<Double, Double>> addTax = x -> y -> y + y/100 * x;
		
		Generic_Functional_Intrf<Double, Double> taxPercent = addTax.apply(0.1);
		
		Double priceWithTax = taxPercent.apply(500.0);
		
		System.out.println("Total price after including tax :"+priceWithTax);
		
		Generic_Functional_Intrf<Double, Generic_Functional_Intrf<Double, Double>> updatedFuncWithReverseArgs = reverseArgs(addTax);
		
		Generic_Functional_Intrf<Double, Double> priceTag = updatedFuncWithReverseArgs.apply(348.00);
		
		Double taxUpdatedPrice = priceTag.apply(0.18);
		
		System.out.println("Total price after updated tax info :"+taxUpdatedPrice);		
	}
	
	public static <T, U, V> Generic_Functional_Intrf<U, Generic_Functional_Intrf<T, V>> reverseArgs(Generic_Functional_Intrf<T, Generic_Functional_Intrf<U, V>> func){
		return u -> t -> func.apply(t).apply(u);
	}

}
