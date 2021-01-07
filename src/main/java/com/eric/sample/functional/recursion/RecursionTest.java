package com.eric.sample.functional.recursion;

/**
 * @author Shahnawaz
 * This class proves that under normal conditions, maximum recursive steps a JAVA program can take is 62780. On 62781th step it will throw Stack Over Flow error.
 * This has been tested with java version "1.8.0_271"
 *
 */
public class RecursionTest {
	
	private static int call_counter = 0;
	
	public static void main(String[] args) {
		int result = addRec(3, 5000);
		System.out.println("Result of addRec(3, 5000) call : "+result);
		System.out.println("Value of call_counter : "+call_counter);
		call_counter = 0;
		
		int result_1 = addRec(3, 7000);
		System.out.println("Result of addRec(3, 7000) call : "+result_1);
		System.out.println("Value of call_counter : "+call_counter);
		call_counter = 0;
		
		int result_2 = addRec(3, 8000);
		System.out.println("Result of addRec(3, 8000) call : "+result_2);
		System.out.println("Value of call_counter : "+call_counter);
		call_counter = 0;
		
		int result_3 = addRec(3, 10000);
		System.out.println("Result of addRec(3, 10000) call : "+result_3);
		System.out.println("Value of call_counter : "+call_counter);
		call_counter = 0;
		
		int result_4 = addRec(3, 15000);
		System.out.println("Result of addRec(3, 15000) call : "+result_4);
		System.out.println("Value of call_counter : "+call_counter);
		call_counter = 0;
		
		int result_5 = addRec(3, 20000);
		System.out.println("Result of addRec(3, 20000) call : "+result_5);
		System.out.println("Value of call_counter : "+call_counter);
		call_counter = 0;
		
		int result_6 = addRec(3, 30000);
		System.out.println("Result of addRec(3, 30000) call : "+result_6);
		System.out.println("Value of call_counter : "+call_counter);
		call_counter = 0;
		
		int result_7 = addRec(3, 45000);
		System.out.println("Result of addRec(3, 45000) call : "+result_7);
		System.out.println("Value of call_counter : "+call_counter);
		call_counter = 0;
		
		int result_8 = addRec(3, 55000);
		System.out.println("Result of addRec(3, 55000) call : "+result_8);
		System.out.println("Value of call_counter : "+call_counter);
		call_counter = 0;
		
		int result_9 = addRec(3, 60000);
		System.out.println("Result of addRec(3, 60000) call : "+result_9);
		System.out.println("Value of call_counter : "+call_counter);
		call_counter = 0;
		
		int result_10 = addRec(3, 62000);
		System.out.println("Result of addRec(3, 62000) call : "+result_10);
		System.out.println("Value of call_counter : "+call_counter);
		call_counter = 0;
		
		int result_11 = addRec(3, 62780);
		System.out.println("Result of addRec(3, 62780) call : "+result_11);
		System.out.println("Value of call_counter : "+call_counter);
		call_counter = 0;
	}
	
	private static int addRec(int x, int y) {
		call_counter++;
		return y == 0
				? x
				: addRec(++x, --y);
	}

}
