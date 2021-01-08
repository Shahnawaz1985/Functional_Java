package com.eric.sample.function.revursive.memoize;

import com.eric.sample.function.intrf.Generic_Functional_Intrf;

/**
 * 
 * @author Shahnawaz
 *
 */
public class CustomUtil {
	
	private static Integer longCalculation(Integer x) {
		try {
			Thread.sleep(1000);
		}catch(InterruptedException ex) {
			ex.printStackTrace();
		}
		
		return x*2;
	}
	
	private static Generic_Functional_Intrf<Integer, Generic_Functional_Intrf<Integer, Generic_Functional_Intrf<Integer, Integer>>> func_memoize = 
					Memoizer.memoize(x -> 
							Memoizer.memoize(y -> 
								Memoizer.memoize(z -> 
								longCalculation(x) + longCalculation(y) - longCalculation(z))));
	
	private static Generic_Functional_Intrf<Tuple3<Integer, Integer, Integer>, Integer> func_tuple = 
											x -> longCalculation(x._1) + longCalculation(x._2) - longCalculation(x._3);
											
	private static Generic_Functional_Intrf<Tuple3<Integer, Integer, Integer>, Integer> func_tuple_memz = Memoizer.memoize(func_tuple);
													
	
	public static void automaticMemoization(int x, int y, int z) {
		long startTime = System.currentTimeMillis();
		Integer result = func_memoize.apply(x).apply(y).apply(z);
		long totalTime = System.currentTimeMillis() - startTime;
		
		startTime = System.currentTimeMillis();
		Integer result_2 = func_memoize.apply(x).apply(y).apply(z);
		long updated_totalTime = System.currentTimeMillis() - startTime;
		
		System.out.println("Result 1 :" +result);
		System.out.println("Result 2 : "+result_2);
		System.out.println("Total time taken in 1st operation : "+totalTime);
		System.out.println("Total time taken in 2nd operation : "+updated_totalTime);
	}
	
	public static void automaticMemoizationWithTuples(int x, int y, int z) {
		long startTime = System.currentTimeMillis();
		Integer result = func_tuple_memz.apply(new Tuple3<>(x, y, z));
		long totalTime = System.currentTimeMillis() - startTime;
		
		startTime = System.currentTimeMillis();
		Integer result_2 = func_tuple_memz.apply(new Tuple3<>(x, y, z));
		long updated_totalTime = System.currentTimeMillis() - startTime;
		
		System.out.println("Result 1 :" +result);
		System.out.println("Result 2 : "+result_2);
		System.out.println("Total time taken in 1st operation : "+totalTime);
		System.out.println("Total time taken in 2nd operation : "+updated_totalTime);
	}
	
	

}
