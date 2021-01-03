package com.eric.sample.function.email.verifier;

public interface Result {
	
	public class Success implements Result {}
	
	public class Failure implements Result {
		
		private final String errorMessage;
		
		public Failure(String errorMessage) {
			this.errorMessage = errorMessage;
		}
		
		public String getMessage() {
			return errorMessage;
		}
	}

}
