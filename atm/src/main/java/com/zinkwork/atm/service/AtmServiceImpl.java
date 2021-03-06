package com.zinkwork.atm.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.stereotype.Service;

import com.zinkwork.atm.DAO.RespDAO;
import com.zinkwork.atm.entity.UserEntity;
import com.zinkwork.atm.exception.InSufficientBalanceException;
import com.zinkwork.atm.exception.NoAtmBalanceException;
import com.zinkwork.atm.repository.UserRepository;
import com.zinkwork.atm.utils.CommonUtils;
import com.zinkwork.atm.validatiion.AtmValidation;

@Service
public class AtmServiceImpl implements AtmService{
	
	@Autowired
	private AtmValidation atmValidation;
	
	@Autowired
	private CommonUtils commonUtils;
	
	@Autowired
	private UserRepository userRepo;
	
	private static final Logger logger = LogManager.getLogger(AtmServiceImpl.class);

	@Override
	public ArrayList<Integer> withdrawAmount(Long withdrawAmount, Integer atmId,Long accno) {
		if(atmValidation.atmBalanceCheck(withdrawAmount, atmId)) {
			if(atmValidation.userBalanceCheck(withdrawAmount, accno)) {
				return commonUtils.deductAmount(withdrawAmount, atmId, accno);
			}else {
				throw new InSufficientBalanceException("No sufficient balance in account");
			}
		}else {
			throw new NoAtmBalanceException("No sufficinet Balance in ATM");
		}
	}

	@Override
	public Double retrieveBalance(Long accno) {
		UserEntity userObj=userRepo.findById(accno).get();
		return userObj.getBalance();
		
	}
}
