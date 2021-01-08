package com.eric.sample.functional.collection.helper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.eric.sample.function.intrf.Generic_Functional_Intrf;

/**
 * 
 * @author Shahnawaz
 *
 */
public class CollectionUtilities {
	
	/**
	 * 
	 * @param <T> <T>
	 * @return List
	 */
	public static <T> List<T> list(){
		return Collections.emptyList();
	}
	
	/**
	 * 
	 * @param <T> <T>
	 * @param t t
	 * @return List
	 */
	public static <T> List<T> list(T t){
		return Collections.singletonList(t);
	}
	
	/**
	 * 
	 * @param <T> <T>
	 * @param ts ts
	 * @return List
	 */
	public static <T> List<T> list(List<T> ts){
		return Collections.unmodifiableList(new ArrayList<>(ts));
	}
	
	/**
	 * 
	 * @param <T> <T>
	 * @param t t 
	 * @return List
	 */
	@SafeVarargs
	public static <T> List<T> list(T...t){
		return Collections.unmodifiableList(Arrays.asList(Arrays.copyOf(t, t.length)));
	}
	
	/**
	 * 
	 * @param <T> <T>
	 * @param <U> <U>
	 * @param list list
	 * @param f f
	 * @return List
	 */
	<T, U> List<U> map(List<T> list, Generic_Functional_Intrf<T, U> f){
		List<U> newList = new ArrayList<>();
		for(T value : list) {
			newList.add(f.apply(value));
		}
		return newList;
	}
	
	/**
	 * 
	 * @param <T> <T>
	 * @param list list
	 * @return first element of the list
	 */
	public static <T> T head(List<T> list) {
		if(list.size() == 0) {
			throw new IllegalArgumentException("head of empty list identified.");
		}
		return list.get(0);
	}
	
	/**
	 * 
	 * @param <T> <T>
	 * @param ts ts
	 * @return List
	 */
	private static <T> List<T> copy(List<T> ts){
		return new ArrayList<>(ts);
	}
	
	/**
	 * 
	 * @param <T> <T>
	 * @param list list
	 * @return List
	 */
	public static <T> List<T> tail(List<T> list){
		if(list.size() == 0) {
			throw new IllegalStateException("tail of empty list identified!");
		}
		
		List<T> workList = copy(list);
		workList.remove(0);
		return Collections.unmodifiableList(workList);
	}
	
	/**
	 * 
	 * @param <T> <T>
	 * @param list list
	 * @param t t
	 * @return List
	 */
	public static <T> List<T> append(List<T> list, T t){
		List<T> tempList = copy(list);
		tempList.add(t);
		return Collections.unmodifiableList(tempList);
	}
	
	/**
	 * @param is is
	 * @param identity identity
	 * @param f f
	 * @return Integer
	 */
	public static Integer fold(List<Integer> intList, Integer identity, 
						Generic_Functional_Intrf<Integer, Generic_Functional_Intrf<Integer, Integer>> f) {
		int result  = identity;
		for(Integer i : intList) {
			result = f.apply(result).apply(i);
		}
		return result;
	}
	
	/**
	 * @param <T> <T>
	 * @param <U> <U>
	 * @param ts ts
	 * @param identity identity
	 * @param f f
	 * @return result after folding left.
	 */
	public static <T, U> U foldLeft(List<T> ts, U identity, 
						Generic_Functional_Intrf<U, Generic_Functional_Intrf<T, U>> f) {
		U result = identity;
		for(T t : ts) {
			result = f.apply(result).apply(t);
		}
		return result;
	}
	
	/**
	 * @param <T> <T>
	 * @param <U> <U>
	 * @param ts ts
	 * @param identity identity
	 * @param func func
	 * @return result after fold right.
	 */
	public static <T, U> U foldRight(List<T> ts, U identity, 
						Generic_Functional_Intrf<T, Generic_Functional_Intrf<U, U>> func){
		U result = identity;
		for(int i = ts.size(); i>0; i--) {
			result = func.apply(ts.get(i-1)).apply(result);
		}
		return result;
	}
	
	/**
	 * @param ts ts
	 * @param identity identity
	 * @param func func
	 */
	public static <T, U> U foldRight_v2(List<T> ts, U identity, 
						Generic_Functional_Intrf<T, Generic_Functional_Intrf<U, U>> func) {
		return ts.isEmpty()
				? identity
				: func.apply(head(ts)).apply(foldRight_v2(tail(ts), identity, func));	
	}
	
	/**
	 * @param <T> <T>
	 * @param t t
	 * @param list list
	 * @return List after adding element at beginning.
	 */
	public static <T> List<T> prepend(T t, List<T> list){
		return foldLeft(list, list(t), a -> b -> append(a,b));
	}
	
	/**
	 * @param <T> <T>
	 * @param list list
	 * @return reversed list
	 */
	public static <T> List<T> reverse(List<T> list){
		List<T> result = new ArrayList<T>();
		for(int i = list.size() - 1; i >= 0; i--) {
			result.add(list.get(i));
		}
		return Collections.unmodifiableList(result);
	}
	
	/**
	 * @param <T> <T>
	 * @param list list
	 * @return updated reversed list
	 */
	public static <T> List<T> reverse_v1(List<T> list){
		return foldLeft(list, list(), x -> y -> prepend(y,x));
	}
	
	/**
	 * @param <T> <T>
	 * @param list list
	 * @return reversed list
	 */
	public static <T> List<T> reverse_v2(List<T> list){
		return foldLeft(list, list(), x -> y -> 
											foldLeft(x, list(y), a -> b -> append(a, b)));
	}
	
	/**
	 * @param <T> <T>
	 * @param <U> <U>
	 * @param list list
	 * @param func func
	 * @return List using mapping via Fold Left
	 */
	public static <T, U> List<U> mapViaFoldLeft(List<T> list, Generic_Functional_Intrf<T, U> func){
		return foldLeft(list, list(), x -> y -> append(x, func.apply(y)));
	}
	
	/**
	 * @param <T> <T>
	 * @param <U> <U>
	 * @param list list
	 * @param func func
	 * @return List using mapping via Fold Right
	 */
	public static <T, U> List<U> mapViaFoldRight(List<T> list, Generic_Functional_Intrf<T, U> func){
		return foldRight(list, list(), x -> y -> prepend(func.apply(x), y));
	}
}
