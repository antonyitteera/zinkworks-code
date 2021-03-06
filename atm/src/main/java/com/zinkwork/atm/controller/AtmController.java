package com.zinkwork.atm.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zinkwork.atm.DAO.DispenseDAO;
import com.zinkwork.atm.DAO.RespDAO;
import com.zinkwork.atm.entity.UserEntity;
import com.zinkwork.atm.repository.UserRepository;
import com.zinkwork.atm.service.AtmService;
import com.zinkwork.atm.utils.JwtUtil;

@RestController
@RequestMapping(value = "/api/v1")
@CrossOrigin("*")

public class AtmController {
	
	@Autowired
	UserRepository userRepo;
	
	@Autowired
	AtmService atmService;
	
	@Autowired
	private JwtUtil jwt;
	
	public void loadCash() {
		
	}
	
	@RequestMapping(value = "/retrievebalance")
	public ResponseEntity<RespDAO> checkBalance(@RequestHeader("Authorization") String authToken ) {
		String[] token=authToken.split(" ");
		String accno=jwt.extractusername(token[1]);
		RespDAO resp= new RespDAO("Avaliable balance is "+atmService.retrieveBalance(Long.valueOf(accno)));
		return new ResponseEntity<RespDAO>(resp, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/withdraw/{amount}")
	public ResponseEntity<Object> withdrawAmount(@PathVariable Long amount,@RequestHeader("Authorization") String authToken ) {
		String[] token=authToken.split(" ");
		String accno=jwt.extractusername(token[1]);
		try {
			ArrayList<Integer> noteDispensed= atmService.withdrawAmount(amount, 1, Long.valueOf(accno));
			
			Map<String, String> respObj= new HashMap<>();
			respObj.put("message", "Amount deducted successfully");
			return new ResponseEntity<Object>(new DispenseDAO("Amount deducted successfully", noteDispensed, atmService.retrieveBalance(Long.valueOf(accno))), HttpStatus.OK);
		}catch (NumberFormatException e) {
			RespDAO respObj= new RespDAO(e.getMessage());
			return new ResponseEntity<Object>(respObj,HttpStatus.BAD_REQUEST);
		}catch (Exception e) {
			RespDAO respObj= new RespDAO(e.getMessage());
			return new ResponseEntity<Object>(respObj,HttpStatus.BAD_REQUEST);
		}
		
	}
}
