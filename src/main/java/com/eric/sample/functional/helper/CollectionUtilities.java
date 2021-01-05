package com.eric.sample.functional.helper;

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
	
	public static <T> List<T> list(){
		return Collections.emptyList();
	}
	
	public static <T> List<T> list(T t){
		return Collections.singletonList(t);
	}
	
	public static <T> List<T> list(List<T> ts){
		return Collections.unmodifiableList(new ArrayList<>(ts));
	}
	
	@SafeVarargs
	public static <T> List<T> list(T...t){
		return Collections.unmodifiableList(Arrays.asList(Arrays.copyOf(t, t.length)));
	}
	
	<T, U> List<U> map(List<T> list, Generic_Functional_Intrf<T, U> f){
		List<U> newList = new ArrayList<>();
		for(T value : list) {
			newList.add(f.apply(value));
		}
		return newList;
	}
	
	public static <T> T heaf(List<T> list) {
		if(list.size() == 0) {
			throw new IllegalArgumentException("head of empty list identified.");
		}
		return list.get(0);
	}
	
	private static <T> List<T> copy(List<T> ts){
		return new ArrayList<>(ts);
	}
	
	public static <T> List<T> tail(List<T> list){
		if(list.size() == 0) {
			throw new IllegalStateException("tail of empty list identified!");
		}
		
		List<T> workList = copy(list);
		workList.remove(0);
		return Collections.unmodifiableList(workList);
	}
	
	public static <T> List<T> append(List<T> list, T t){
		List<T> tempList = copy(list);
		tempList.add(t);
		return Collections.unmodifiableList(tempList);
	}

}
