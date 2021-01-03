package com.eric.sample.function.util;

import com.github.javafaker.Faker;

public class FakerTest {
	
	public static void main(String[] args) {
		Faker faker = new Faker();
		String name = faker.name().fullName();
		String firstName = faker.name().firstName();
		String lastName = faker.name().lastName();
		
		System.out.printf("Generated full name, firstName and lastName %s, %s, %s", name, firstName, lastName);
	}

}
