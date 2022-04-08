package com.zinkwork.atm.service;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.http.ResponseEntity;

import com.zinkwork.atm.DAO.RespDAO;

public interface AtmService {

	ArrayList<Integer> withdrawAmount(Long withdrawAmount, Integer atmId, Long accnum);
	
	Double checkBalance(Long accno);
}
