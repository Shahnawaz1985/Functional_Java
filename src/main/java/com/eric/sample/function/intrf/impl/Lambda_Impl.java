package com.eric.sample.function.intrf.impl;

import com.eric.sample.function.intrf.Generic_Functional_Intrf;
import com.eric.sample.function.intrf.Generic_Functional_Intrf2;

public class Lambda_Impl {
	
	public static void main(String[] args) {
		
		
		Generic_Functional_Intrf<Generic_Functional_Intrf<Integer, Integer>, 
					Generic_Functional_Intrf<Generic_Functional_Intrf<Integer, Integer>, 
						Generic_Functional_Intrf<Integer, Integer>>> compose = x -> y -> z -> x.apply(y.apply(z));
		
		Generic_Functional_Intrf<Integer, Integer> triple = x -> x * 3;
		
		Generic_Functional_Intrf<Integer, Integer> square = x -> x * x;
		
		Generic_Functional_Intrf<Integer, Integer> result = compose.apply(square).apply(triple);
		
		System.out.println("Lambda based implementation for Generic_Functional_Intrf :" + result.apply(5));
		
		Generic_Functional_Intrf<Integer, Integer> higher_order_result = Lambda_Impl.<Integer, Integer, Integer> higherCompose().apply(triple).apply(square);
		
		System.out.println("Lambda based implementation for higher order function result :" + higher_order_result.apply(7));
		
		Generic_Functional_Intrf2<Double, Double, Double> taxCalculator = (taxRate, price) -> price + price * taxRate;
		
		double priceWithTax = taxCalculator.apply(.03, 100.0);
		
		System.out.println("Updated price with tax: "+ priceWithTax);
		
		Generic_Functional_Intrf<Double, Generic_Functional_Intrf<Double, Double>> taxCal2 = taxRate2 -> price -> price + price * taxRate2;
		
		Generic_Functional_Intrf<Double, Double> estimatedTax = taxCal2.apply(.09);
		
		double priceWithTax2 = estimatedTax.apply(460.0);
		
		System.out.println("Updated price with Generic_Functional_Intrf: "+ priceWithTax2);
		
		
	}
	
	static <T, U, V> Generic_Functional_Intrf<Generic_Functional_Intrf<U, V>, 
							Generic_Functional_Intrf<Generic_Functional_Intrf<T, U>, 
										Generic_Functional_Intrf<T, V>>> higherCompose() {
		return x -> y -> z -> x.apply(y.apply(z));
	}
	
	/**
	static <T, U, V, W> Generic_Functional_Intrf2<Generic_Functional_Intrf2<T, U, V>, Generic_Functional_Intrf2<Generic_Functional_Intrf2<T, V, U>, 
				Generic_Functional_Intrf2<U, V, T>, Generic_Functional_Intrf2<U, T, V>>, 
				Generic_Functional_Intrf2<Generic_Functional_Intrf2<Generic_Functional_Intrf2<T, U, V>, Generic_Functional_Intrf2<T, V, U>, Generic_Functional_Intrf2<U, V, T>>, 
				Generic_Functional_Intrf2<Generic_Functional_Intrf2<U, T, V>, Generic_Functional_Intrf2<T, V, U>, Generic_Functional_Intrf2<U, V, T>>, 
				Generic_Functional_Intrf2<Generic_Functional_Intrf2<T, U, V>, Generic_Functional_Intrf2<T, V, U>, Generic_Functional_Intrf2<U, V, T>>>> higherCompose_tuple() {
		
		 return x -> y -> z -> w ->  x.apply(y, z.apply(w, x));
	}
	**/

}
