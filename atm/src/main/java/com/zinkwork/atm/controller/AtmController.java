package com.zinkwork.atm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zinkwork.atm.entity.UserEntity;
import com.zinkwork.atm.repository.UserRepository;
import com.zinkwork.atm.utils.JwtUtil;

@RestController
@RequestMapping(value = "/api/v1")
public class AtmController {
	
	@Autowired
	UserRepository userRepo;
	
	@Autowired
	private JwtUtil jwt;
	
	public void loadCash() {
		
	}
	
	@RequestMapping(value = "/checkBalance")
	public ResponseEntity<UserEntity> checkBalance(@RequestHeader("Authorization") String authToken ) {
		//userRepo.getOne(accno);
		String[] token=authToken.split(" ");
		String accno=jwt.extractusername(token[1]);
		return new ResponseEntity<UserEntity>(userRepo.findById(Long.valueOf(accno)).get(), HttpStatus.OK);
				
	}

}
