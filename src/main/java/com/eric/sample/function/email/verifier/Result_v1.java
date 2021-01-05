package com.eric.sample.function.email.verifier;

/**
 * 
 * @author Shahnawaz
 *
 * @param <T>
 */
public interface Result_v1<T> {
	
	void bind(Effect<T> success, Effect<String> failure);
	
	static <T> Result_v1<T> failure(String message){
		return new Failure<>(message);
	}
	
	static <T> Result_v1<T> success(T value){
		return new Success<>(value);
	}
	
	class Success<T> implements Result_v1<T> {
		
		private final T value;
		
		private Success(T t) {
			value = t;
		}

		@Override
		public void bind(Effect<T> success, Effect<String> failure) {
			success.apply(value);			
		}
	}
	
	class Failure<T> implements Result_v1<T>{
		
		private final String errorMessage;
		
		private Failure(String errMsg) {
			this.errorMessage = errMsg;
		}

		@Override
		public void bind(Effect<T> success, Effect<String> failure) {
			failure.apply(errorMessage);
		}
	}

}
