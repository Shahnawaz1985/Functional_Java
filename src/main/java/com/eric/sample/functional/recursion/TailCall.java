package com.eric.sample.functional.recursion;

import com.eric.sample.function.email.revised.Supplier;
import com.eric.sample.function.intrf.Generic_Functional_Intrf;

/**
 * Example of Tail Call Elimination or Tail Call optimization.
 * @author Shahnawaz
 *
 * @param <T>
 */
public abstract class TailCall<T> {
	
	public abstract TailCall<T> resume();
	
	public abstract T eval();
	
	public abstract boolean isSuspend();
	
	private TailCall() {
		
	}
	
	/**
	 * @author Shahnawaz
	 * This subclass represents the last call which is supposed to return the result so we will call it Return. It won't hold a link to the next TailCall, 
	 * because there is nothing next, but it will hold the result.
	 *
	 * @param <T>
	 */
	private static class Return<T> extends TailCall<T>{
		
		private final T t;
		
		private Return(T t) {
			this.t = t;
		}
		
		public T eval() {
			return t;
		}
		
		public boolean isSuspend() {
			return false;
		}
		
		public TailCall<T> resume(){
			throw new IllegalStateException("Return has no resume() call.");
		}		
	}
	
	/**
	 * @author Shahnawaz
	 * This subclass represents an intermediate call when the processing of one step is suspended to call the method again for evaluating the next step. 
	 * It's instantiated with Supplier<TailCall<T>> which represents the next TailCall.
	 * 
	 *
	 * @param <T>
	 */
	private static class Suspend<T> extends TailCall<T>{
		
		private final Supplier<TailCall<T>> resume;
		
		private Suspend(Supplier<TailCall<T>> resume) {
			this.resume = resume;
		}
		
		public T eval() {
			TailCall<T> tailRecord = this;
			while(tailRecord.isSuspend()) {
				tailRecord = tailRecord.resume();				
			}
			return tailRecord.eval();
		}
		
		public boolean isSuspend() {
			return true;
		}
		
		public TailCall<T> resume(){
			return resume.get();
		}
		
		public static <T> Return<T> ret(T t){
			return new Return<>(t);
		}
		
		public static <T> Suspend<T> sus(Supplier<TailCall<T>> s){
			return new Suspend<>(s);
		}
	}
	
	public static int add(int x, int y) {
		return addRec(x, y).eval();
	}
	
	//This variable shows the number of invocation. Number of invocation for add(3, 1000000) is 1000001 which is many fold more than normal recursion threshold.
	private static int call_counter = 0;
	
	private static TailCall<Integer> addRec(int x, int y){
		call_counter++;
		return y == 0
				? Suspend.ret(x)
						: Suspend.sus(() -> addRec(x + 1, y - 1));
	}
	
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
		int result = add(3, 1000000);
		System.out.println("Result of addition after using Tail Call Elimination :"+result);
		System.out.println("Value of call_counter :"+call_counter);
		call_counter = 0;
		int result_1 = add(1000000, 3);
		System.out.println("Updated Result of addition after using Tail Call Elimination :"+result_1);
		System.out.println("Updated Value of call_counter :"+call_counter);
		
		System.out.println("Recursion using lambda:");
		int lambda_test_result = add_lambda.apply(3).apply(1000000);
		System.out.println("Result of lambda test : "+lambda_test_result);
		
	}

}
