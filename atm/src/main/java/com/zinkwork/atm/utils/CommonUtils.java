package com.zinkwork.atm.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.zinkwork.atm.entity.AtmEntity;
import com.zinkwork.atm.entity.NoteEntity;
import com.zinkwork.atm.entity.UserEntity;
import com.zinkwork.atm.exception.NoNotesAvailableException;
import com.zinkwork.atm.repository.AtmRepository;
import com.zinkwork.atm.repository.NoteRepository;
import com.zinkwork.atm.repository.UserRepository;

@Component
public class CommonUtils {
	
	@Autowired
	private AtmRepository atmRepo;
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private NoteRepository noteRepo;
	
	private static final Logger logger = LogManager.getLogger(CommonUtils.class);
	
	@Transactional
	public ArrayList<Integer> deductAmount(Long withdrawAmount,Integer atmId,Long accno) {
		UserEntity userObj=userRepo.findById(accno).get();
		AtmEntity atmObj=atmRepo.findById(atmId).get();
		Set<NoteEntity> noteObj= atmObj.getNoteEntity();
		logger.info("Note Deno:{}",noteObj);
		Integer[][] noteDetails=noteObj.stream().map(x->new Integer[] {x.getNote(),x.getCount()}).collect(Collectors.toList()).stream().sorted((x,y)->y[0].compareTo(x[0])).toArray(Integer[][]::new);
		logger.info("Available note denomination: {}",noteDetails);
		ArrayList<Integer> dispensedNotes=NoteDispenserUtils.coinChange(noteDetails, withdrawAmount);
		System.out.println(dispensedNotes + "dispensed notessssssssssssssss");
		if(dispensedNotes==null) {
			throw new NoNotesAvailableException("Notes not available. Please try other amount");
		}else {
			
			System.out.println(dispensedNotes);
			
	        HashMap<Integer, Integer> notesMap = new HashMap<>();
	        
	        for(Integer i : dispensedNotes) {
	        	if(notesMap.get(i) == null) {
	        		notesMap.put(i, 1);
	        	}else {
	        		notesMap.put(i, notesMap.get(i)+1);
	        	}
	        }
			
			noteObj.stream().forEach(x->{
				if(notesMap.get(x.getNote()) != null) {
					System.out.println(x.getNote() + "   " + x.getNote());
					x.setCount(x.getCount() - notesMap.get(x.getNote()));
				}
			});
			
			System.out.println(noteObj);
			noteRepo.saveAll(noteObj);
		}
		Double currBal=userObj.getBalance();
		Double newBal= currBal-withdrawAmount;
		if(newBal>0) {
			userObj.setBalance(newBal);
		}else {
			userObj.setBalance(0.0);
		}
		Double currAtmBal= atmObj.getBalance();
		Double newAtmBal=currAtmBal-withdrawAmount;
		atmObj.setBalance(newAtmBal);
		UserEntity savedUser=userRepo.save(userObj);
		logger.debug("transaction of user commited succesfully");
		logger.debug("UserInfo:{}",savedUser);
		AtmEntity savedAtm=atmRepo.save(atmObj);
		logger.debug("transaction of atm commited succesfully");
		logger.debug("AtmInfo:{}",savedAtm);
		return dispensedNotes;
	}
}
