package com.eric.sample.function.intrf.impl;

import com.eric.sample.function.intrf.Functional_Intrf;
import com.eric.sample.function.intrf.Generic_Functional_Intrf;

public class Functional_Intrf_Impl{

	public static void main(String[] args) {
		
		Functional_Intrf square = new Functional_Intrf() {

			@Override
			public int apply(int arg) {
				return arg * arg;
			}			
		};
		
		Functional_Intrf cube = new Functional_Intrf() {

			@Override
			public int apply(int arg) {
				return arg * arg * arg;
			}			
		};
		
		System.out.println("Testing square of square.apply(5) : " + square.apply(5));
		
		System.out.println("Testing cube of cube.apply(5) : " + cube.apply(5));
		
		
		Generic_Functional_Intrf<Integer, Integer> triple = new Generic_Functional_Intrf<Integer, Integer>(){

			@Override
			public Integer apply(Integer arg) {				
				return arg * 3;
			}
		};
		
		Generic_Functional_Intrf<Integer, Integer> quadruplet = new Generic_Functional_Intrf<Integer, Integer>(){

			@Override
			public Integer apply(Integer arg) {				
				return arg * 4;
			}
		};
		
		System.out.println("Generic version of triplet function triple.apply(7) : " + triple.apply(7));
		
		System.out.println("Generic version of triplet function quadruplet.apply(8) : " + quadruplet.apply(8));
		
		
		System.out.println("Composing two functional interface compose_ver1(triple, quadruplet).apply(9) :" + compose_ver1(triple, quadruplet).apply(9));
		
		System.out.println("Composing two functional interface compose_ver2(triple, quadruplet).apply(11) :" + compose_ver1(triple, quadruplet).apply(11));
		
		
	}
	
	
	
	static Generic_Functional_Intrf<Integer, Integer> compose_ver1(final Generic_Functional_Intrf<Integer, Integer> g1,
			final Generic_Functional_Intrf<Integer, Integer> g2) {
		return new Generic_Functional_Intrf<Integer, Integer>() {

			@Override
			public Integer apply(Integer arg) {
				return g1.apply(g2.apply(arg));
			}

		};
	}

	static Generic_Functional_Intrf<Integer, Integer> compose_ver2(final Generic_Functional_Intrf<Integer, Integer> g1,
			final Generic_Functional_Intrf<Integer, Integer> g2) {
		return arg -> g1.apply(g2.apply(arg));
	}

}
