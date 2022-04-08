package com.zinkwork.atm.exception;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class InSufficientBalanceException extends RuntimeException{

	public InSufficientBalanceException(String message) {
		super(message);
	}
}
