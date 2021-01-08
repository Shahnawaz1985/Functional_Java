package com.eric.sample.functional.collection.recursion;

import java.util.List;
import com.eric.sample.function.intrf.Generic_Functional_Intrf;
import com.eric.sample.functional.recursion.TailCall;
import com.eric.sample.functional.recursion.TailCall.Suspend;
import static com.eric.sample.functional.collection.helper.CollectionUtilities.tail;
import static com.eric.sample.functional.collection.helper.CollectionUtilities.head;
import static com.eric.sample.functional.collection.helper.CollectionUtilities.append;
import static com.eric.sample.functional.collection.helper.CollectionUtilities.list;
import static com.eric.sample.functional.collection.helper.CollectionUtilities.reverse;

/**
 * Recursive version of CollectionUtils.
 * @author Shahnawaz
 *
 */
public class RecursiveCollectionUtils {
	
	/**
	 * 
	 * @param <T> <T>
	 * @param <U> <U>
	 * @param ts ts
	 * @param identity identity
	 * @param func func
	 * @return U
	 */
	public static <T, U> U foldLeft(List<T> ts, U identity, 
								Generic_Functional_Intrf<U, Generic_Functional_Intrf<T, U>> func) {
		return foldLeft_(ts, identity, func).eval();
	}
	
	private static <T, U> TailCall<U> foldLeft_(List<T> ts, U identity, 
								Generic_Functional_Intrf<U, Generic_Functional_Intrf<T, U>> func_1){
		return ts.isEmpty()
				? Suspend.ret(identity)
					: Suspend.sus(() -> foldLeft_(tail(ts), func_1.apply(identity).apply(head(ts)), func_1));
	}
	
	/**
	 * @param start start
	 * @param end end
	 * @return List<Integer>
	 */
	public static List<Integer> range(Integer start, Integer end){
		return range_(list(), start, end).eval();
	}
	
	private static TailCall<List<Integer>> range_(List<Integer> acc, Integer start, Integer end){
		return end <= start
				? Suspend.ret(acc)
					: Suspend.sus(() -> range_(append(acc,start), start + 1, end));
	}
	
	/**
	 * @param <T> <T>
	 * @param <U> <U>
	 * @param ts ts
	 * @param identity identity
	 * @param func func
	 * @return U
	 */
	public static <T, U> U foldRight(List<T> ts, U identity, 
								Generic_Functional_Intrf<T, Generic_Functional_Intrf<U, U>> func) {
		return foldRight_(identity, reverse(ts), func).eval();
	}
	
	private static <T, U> TailCall<U> foldRight_(U acc, List<T> ts, 
								Generic_Functional_Intrf<T, Generic_Functional_Intrf<U, U>> func_1){
		return ts.isEmpty()
				? Suspend.ret(acc)
					: Suspend.sus(() -> foldRight_(func_1.apply(head(ts)).apply(acc), tail(ts), func_1));
	}
	

}
