package com.eric.sample.function.email.verifier;

public interface Effect<T> {
	
	void apply(T t);

}
