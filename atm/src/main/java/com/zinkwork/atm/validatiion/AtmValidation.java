package com.zinkwork.atm.validatiion;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.zinkwork.atm.entity.AtmEntity;
import com.zinkwork.atm.entity.UserEntity;
import com.zinkwork.atm.repository.AtmRepository;
import com.zinkwork.atm.repository.UserRepository;

@Component
public class AtmValidation {

	@Autowired
	private AtmRepository atmRepo;
	
	@Autowired
	private UserRepository userRepo;
	
	public boolean atmBalanceCheck(Long withdrawAmount, Integer atmId) { // method to check if the atm has enough withdrawl amount
		Optional<AtmEntity> atmObj=atmRepo.findById(atmId);
		if(atmObj.isPresent()) {
			Double atmBal=atmObj.get().getBalance();
			if(atmBal>withdrawAmount) {
				return true;
			}
		}
		return false;
	}
	
	public boolean userBalanceCheck(Long withdrawAmount,Long accnum) {
		Optional<UserEntity> userObj=userRepo.findById(accnum);
		if(userObj.isPresent()) {
			Double userBal= userObj.get().getBalance();
			Double overDraftamnt= userObj.get().getOverdraft();
			if(userBal+overDraftamnt>=withdrawAmount) {
				return true;
			}
		}
		return false;
	}

}
