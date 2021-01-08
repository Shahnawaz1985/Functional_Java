package com.eric.sample.function.collection.helper.client;

import java.util.List;

import com.eric.sample.functional.collection.helper.CollectionUtilities;
import com.eric.sample.functional.collection.recursion.RecursiveCollectionUtils;

/**
 * Test client for Recursive variant of Collection utils.
 * @author Shahnawaz
 *
 */
public class RecursiveCollectionUtilsTest {
	
	public static void main(String[] args) {
		
		List<Integer> intList = CollectionUtilities.list(1, 2, 3, 4, 5);
		System.out.println("Integer list :"+ intList);
		
		Integer result_2 = RecursiveCollectionUtils.foldLeft(intList, 0, x -> y -> x+y);
		System.out.println("Result after recursive left fold :"+ result_2);
		
		List<Integer> range_list = RecursiveCollectionUtils.range(5, 11);
		System.out.println("Range identified : "+range_list);
		
		
		Integer result_3 = RecursiveCollectionUtils.foldRight(range_list, 0, x -> y -> x+y);
		System.out.println("Result after fold right:"+ result_3);
		
	}

}
