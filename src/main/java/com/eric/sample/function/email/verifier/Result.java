package com.eric.sample.function.email.verifier;

/*
 * Result Interface
 */
public interface Result {
	
	class Success implements Result {}
	
	class Failure implements Result {
		
		private final String errorMessage;
		
		public Failure(String errorMessage) {
			this.errorMessage = errorMessage;
		}
		
		public String getMessage() {
			return errorMessage;
		}
	}

}
