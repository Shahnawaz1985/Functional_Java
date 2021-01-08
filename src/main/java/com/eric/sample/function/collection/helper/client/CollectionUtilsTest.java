package com.eric.sample.function.collection.helper.client;

import java.util.List;

import com.eric.sample.functional.collection.helper.CollectionUtilities;

/**
 * 
 * @author Shahnawaz
 *
 */
public class CollectionUtilsTest {
	
	/**
	 * @param args args
	 */
	public static void main(String[] args) {
		List<Integer> intList = CollectionUtilities.list(1, 2, 3, 4, 5);
		System.out.println("Integer list :"+ intList);
		
		Integer result = CollectionUtilities.fold(intList, 0, x -> y -> x+y);
		System.out.println("Result after list fold :"+ result);
		
		Integer result_2 = CollectionUtilities.foldLeft(intList, 0, x -> y -> x+y);
		System.out.println("Result after left fold :"+ result_2);
		
		
		Integer result_3 = CollectionUtilities.foldRight(intList, 0, x -> y -> x+y);
		System.out.println("Result after fold right:"+ result_3);
		
		Integer result_v4 = CollectionUtilities.foldRight_v2(intList, 0, x -> y -> x+y);
		System.out.println("Result for updated fold right:"+ result_v4);
		
		List<Integer> updatedList = CollectionUtilities.prepend(0, intList);
		System.out.println("Updated List: "+ updatedList);
		
		List<Object> resultList = CollectionUtilities.mapViaFoldLeft(intList, x ->  x*x);
		System.out.println("Final list after mapping it through Fold left: "+resultList);
		
		List<Object> resultList2 = CollectionUtilities.mapViaFoldRight(intList, x -> x*x);
		System.out.println("Final list after mapping it through Fold Right: "+resultList2);
	}

}
