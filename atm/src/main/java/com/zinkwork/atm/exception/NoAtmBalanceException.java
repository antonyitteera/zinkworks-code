package com.zinkwork.atm.exception;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class NoAtmBalanceException extends RuntimeException {

	public NoAtmBalanceException(String message) {
		super(message);
	}
}
