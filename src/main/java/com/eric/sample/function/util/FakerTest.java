package com.eric.sample.function.util;

import com.github.javafaker.Faker;

public class FakerTest {
	
	public static void main(String[] args) {
		Faker faker = new Faker();
		String name = faker.name().fullName();
		String firstName = faker.name().firstName();
		String lastName = faker.name().lastName();
		String address = faker.address().fullAddress();
		
		System.out.printf("Generated full name, firstName and lastName %s, %s, %s", name, firstName, lastName);
		System.out.println();
		System.out.printf("Generated full address %s ", address);
	}

}
