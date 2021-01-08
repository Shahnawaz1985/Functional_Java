package com.eric.sample.functional.recursion;

import java.math.BigInteger;

import com.eric.sample.functional.recursion.TailCall.Suspend;

/**
 * Fibonacci test using Tail Call elimination.
 * @author Shahnawaz
 *
 */
public class FibonacciTest {
	
	private static BigInteger counter = BigInteger.ZERO;
	
	public static void main(String[] args) {
		BigInteger result = fib_gen(200);
		System.out.println("Fibonnaci sequence for fib_gen(200) : "+result.intValue());
		System.out.println("Call sequence for fib_gen(200) : "+counter.intValue());
	}
	
	/**
	 * @param x x
	 * @return
	 */
	private static BigInteger fib_gen(int x) {
		return fib_(BigInteger.ONE, BigInteger.ZERO, BigInteger.valueOf(x)).eval();
	}
	
	/**
	 * @param acc1 acc1
	 * @param acc2 acc2
	 * @param x x
	 * @return
	 */
	private static TailCall<BigInteger> fib_(BigInteger acc1, BigInteger acc2,
									BigInteger x) {
		counter = counter.add(BigInteger.ONE);
		if(x.equals(BigInteger.ZERO)) {
			return Suspend.<BigInteger>ret(BigInteger.ZERO);
		}else if(x.equals(BigInteger.ONE)) {
			return Suspend.<BigInteger>ret(BigInteger.ONE);			
		}else {
			return Suspend.<BigInteger>sus(() -> fib_(acc2, acc1.add(acc2), x.subtract(BigInteger.ONE)));
		}
	}

}
