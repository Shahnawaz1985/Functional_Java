package com.eric.sample.functional.recursion;

import java.math.BigInteger;
import java.util.List;
import static com.eric.sample.functional.collection.helper.CollectionUtilities.append;
import static com.eric.sample.functional.collection.helper.CollectionUtilities.list;
import static com.eric.sample.functional.collection.helper.CollectionUtilities.tail;
import static com.eric.sample.functional.collection.helper.CollectionUtilities.head;
import static com.eric.sample.functional.collection.recursion.RecursiveCollectionUtils.foldLeft;

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
		counter = BigInteger.ZERO;
		System.out.println("-------------------------------------------------");
		System.out.println(fibo(1000)); 
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
	
	private static String fibo(int number) {
		List<BigInteger> list = fibo_(list(BigInteger.ZERO), BigInteger.ONE, 
										BigInteger.ZERO, BigInteger.valueOf(number)).eval();
		String fibo_str =  makeString(list, ", ");
		fibo_str = fibo_str.substring(0, fibo_str.length()-1);
		return fibo_str;
	}
	
	/**
	 * @param <T> <T>
	 * @param acc acc
	 * @param acc1 acc1
	 * @param acc2 acc2
	 * @param x x
	 * @return TailCall
	 */
	private static <T> TailCall<List<BigInteger>> fibo_(List<BigInteger> acc, BigInteger acc1, 
																BigInteger acc2, BigInteger x){
		return x.equals(BigInteger.ZERO)
				? Suspend.ret(acc)
					: x.equals(BigInteger.ONE)
						? Suspend.ret(append(acc, acc1.add(acc2)))
							: Suspend.sus(() -> fibo_(append(acc, acc1.add(acc2)), 
									acc2, acc1.add(acc2), x.subtract(BigInteger.ONE)));
	}
	
	private static String makeString(List<BigInteger> list, String separator) {
		return list.isEmpty()
				? ""
				  : tail(list).isEmpty()
				  	? head(list).toString()
				  		: head(list) + foldLeft(tail(list), "", x -> y -> x + separator + y);
	}

}
