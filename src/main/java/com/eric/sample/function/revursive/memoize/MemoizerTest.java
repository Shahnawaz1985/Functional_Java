package com.eric.sample.function.revursive.memoize;

/**
 * 
 * @author Shahnawaz
 *
 */
public class MemoizerTest {
	
	public static void main(String[] args) {
		System.out.println("Testing for Memoization with arguments");
		CustomUtil.automaticMemoization(2, 3, 4);
		System.out.println("-----------------------------");
		System.out.println("Testing for Memoization with Tuples");
		CustomUtil.automaticMemoizationWithTuples(2, 3, 4);
	}

}
