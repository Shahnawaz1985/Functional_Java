package com.eric.sample.function.revursive.memoize;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.eric.sample.function.intrf.Generic_Functional_Intrf;

/**
 * 
 * @author Shahnawaz
 *
 * @param <T>
 * @param <U>
 */
public class Memoizer<T, U> {
	
	private final Map<T, U> cache = new ConcurrentHashMap<>();
	
	private Memoizer() {
		
	}
	
	/**
	 * @param <T> <T>
	 * @param <U> <U>
	 * @param func func
	 * @return Generic_Functional_Intrf<T, U>
	 */
	public static <T, U> Generic_Functional_Intrf<T, U> memoize(Generic_Functional_Intrf<T, U> func){
		return new Memoizer<T, U>().doMemoize(func);
	}
	
	/**
	 * @param func_1 func_1
	 * @return Generic_Functional_Intrf<T, U>
	 */
	private Generic_Functional_Intrf<T, U> doMemoize(Generic_Functional_Intrf<T, U> func_1){
		return input -> cache.computeIfAbsent(input, func_1::apply);
	}

}
