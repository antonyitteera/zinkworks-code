package com.zinkwork.atm.service;

import org.springframework.http.ResponseEntity;

import com.zinkwork.atm.DAO.RespDAO;

public interface AtmService {

	void withdrawAmount(Long withdrawAmount, Integer atmId, Long accnum);
	
	ResponseEntity<RespDAO> checkBalance(Long accno);
}
