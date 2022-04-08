package com.zinkwork.atm.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.zinkwork.atm.DAO.RespDAO;

@ControllerAdvice
public class AtmException {

	@ExceptionHandler(value = NoAtmBalanceException.class)
	public ResponseEntity<Object> exception(NoAtmBalanceException exception){
		RespDAO respObj= new RespDAO(exception.getMessage());
		return new ResponseEntity<Object>(respObj, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(value= InSufficientBalanceException.class)
	public ResponseEntity<Object> exception(InSufficientBalanceException exception){
		RespDAO respObj= new RespDAO(exception.getMessage());
		return new ResponseEntity<Object>(respObj, HttpStatus.BAD_REQUEST);
	}
}
