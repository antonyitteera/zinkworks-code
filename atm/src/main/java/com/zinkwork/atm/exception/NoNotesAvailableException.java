package com.zinkwork.atm.exception;

public class NoNotesAvailableException extends RuntimeException{

	public NoNotesAvailableException(String message) {
		super(message);
	}
}
