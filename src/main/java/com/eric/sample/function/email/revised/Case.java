package com.eric.sample.function.email.revised;

import com.eric.sample.function.email.verifier.Result_v1;
import com.eric.sample.function.util.Tuple;


/**
 * <b>javadoc<b> for Case class.
 * @author Shahnawaz
 *
 * @param <T>
 */
public class Case<T> extends Tuple<Supplier<Boolean>, Supplier<Result_v1<T>>> {

	/**
	 * 
	 * @param booleanSupplier booleanSupplier
	 * @param resultSupplier resultSupplier
	 */
	private Case(Supplier<Boolean> booleanSupplier, Supplier<Result_v1<T>> resultSupplier) {
		super(booleanSupplier, resultSupplier);
	}

	/**
	 * 
	 * @param <T> <T>
	 * @param condition condition
	 * @param value value
	 * @return Case object
	 */
	public static <T> Case<T> mcase(Supplier<Boolean> condition, Supplier<Result_v1<T>> value){
		return new Case<>(condition, value);
	}

	/**
	 * 
	 * @param <T> <T>
	 * @param value value
	 * @return DefaultCase object
	 */
	public static <T> DefaultCase<T> mcase(Supplier<Result_v1<T>> value){
		return new DefaultCase<>(() -> true, value);
	}

	/**
	 * 
	 * @author Shahnawaz
	 *
	 * @param <T> <T>
	 */
	private static class DefaultCase<T> extends Case<T>{
		private DefaultCase(Supplier<Boolean> booleanSupplier, Supplier<Result_v1<T>> resultSupplier) {
			super(booleanSupplier, resultSupplier);
		}
	}

	/**
	 * 
	 * @param <T>
	 * @param defaultCase defaultCase
	 * @param matchers matchers
	 * @return Result_v1 object
	 */
	@SafeVarargs
	public static <T> Result_v1<T> match(DefaultCase<T> defaultCase, Case<T>...matchers){
		for(Case<T> aCase : matchers) {
			if(aCase._1.get())
				return aCase._2.get();
		}
		return defaultCase._2.get();
	}

}
