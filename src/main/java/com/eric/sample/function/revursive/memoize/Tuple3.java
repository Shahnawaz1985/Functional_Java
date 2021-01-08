package com.eric.sample.function.revursive.memoize;

import java.util.Objects;

/**
 * 
 * @author Shahnawaz
 *
 * @param <T> <T>
 * @param <U> <U>
 * @param <V> <V>
 */
public class Tuple3<T, U, V> {
	
	public final T _1;
	public final U _2;
	public final V _3;
	
	public Tuple3(T t, U u, V v) {
		_1 = Objects.requireNonNull(t);
		_2 = Objects.requireNonNull(u);
		_3 = Objects.requireNonNull(v);
	}
	
	public boolean equals(Object o) {
		if(!(o instanceof Tuple3))
			return false;
		
		Tuple3 that = (Tuple3)o;
		return _1.equals(that._1) && _2.equals(that._2)
								&& _3.equals(that._3);
	}
	
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = result*prime + _1.hashCode();
		result = result*prime + _2.hashCode();
		result = result*prime + _3.hashCode();
		return result;
	}

}
