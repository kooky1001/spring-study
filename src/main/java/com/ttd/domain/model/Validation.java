package com.ttd.domain.model;

public class Validation {
	private boolean valid;
	private String message;
	
	private Validation(boolean valid, String message) {
		this.valid = valid;
		this.message = message;
	}

	public boolean isValid() {
		return valid;
	}
	public String getMessage() {
		return message;
	}
	
	public static Validation ok() {
		return new Validation(true, null);
	}
	public static Validation fail(String message) {
		return new Validation(false, message);
	}
}
