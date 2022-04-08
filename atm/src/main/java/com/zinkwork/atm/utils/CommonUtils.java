package com.zinkwork.atm.utils;

import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.zinkwork.atm.entity.AtmEntity;
import com.zinkwork.atm.entity.UserEntity;
import com.zinkwork.atm.repository.AtmRepository;
import com.zinkwork.atm.repository.UserRepository;
import com.zinkwork.atm.service.AtmServiceImpl;

@Component
public class CommonUtils {
	
	@Autowired
	private AtmRepository atmRepo;
	
	@Autowired
	private UserRepository userRepo;
	
	private static final Logger logger = LogManager.getLogger(CommonUtils.class);
	
	@Transactional
	public void deductAmount(Long withdrawAmount,Integer atmId,Long accno) {
		UserEntity userObj=userRepo.findById(accno).get();
		Double currBal=userObj.getBalance();
		Double newBal= currBal-withdrawAmount;
		if(newBal>0) {
			userObj.setBalance(newBal);
		}else {
			userObj.setBalance(0.0);
		}
		AtmEntity atmObj=atmRepo.findById(atmId).get();
		Double currAtmBal= atmObj.getBalance();
		Double newAtmBal=currAtmBal-withdrawAmount;
		atmObj.setBalance(newAtmBal);
		UserEntity savedUser=userRepo.save(userObj);
		logger.debug("transaction of user commited succesfully");
		logger.debug("UserInfo:{}",savedUser);
		AtmEntity savedAtm=atmRepo.save(atmObj);
		logger.debug("transaction of atm commited succesfully");
		logger.debug("AtmInfo:{}",savedAtm);
		
	}
}
