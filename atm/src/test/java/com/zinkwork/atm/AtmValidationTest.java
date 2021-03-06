package com.zinkwork.atm;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.zinkwork.atm.entity.AtmEntity;
import com.zinkwork.atm.entity.NoteEntity;
import com.zinkwork.atm.entity.UserEntity;
import com.zinkwork.atm.repository.AtmRepository;
import com.zinkwork.atm.repository.UserRepository;
import com.zinkwork.atm.validatiion.AtmValidation;

@RunWith(MockitoJUnitRunner.class)
public class AtmValidationTest {

	@Rule
	public ExpectedException exceptionRule= ExpectedException.none();

	@Mock
	private AtmRepository atmRepo;
	
	@Mock
	private UserRepository userRepo;

	@InjectMocks
	private AtmValidation atmValidation;

	@Test
	public void whenNoAtmBalanceThenFalse() {
		AtmEntity atmObj= new AtmEntity(1, 1300.0, null);
		when(atmRepo.findById(ArgumentMatchers.anyInt())).thenReturn(Optional.ofNullable(atmObj));
		assertEquals(false, atmValidation.atmBalanceCheck(1500L, atmObj.getId()));
		
	}
	
	@Test
	public void whenAtmBalanceThenTrue() {
		AtmEntity atmObj= new AtmEntity(1, 1300.0, null);
		when(atmRepo.findById(ArgumentMatchers.anyInt())).thenReturn(Optional.ofNullable(atmObj));
		assertEquals(true, atmValidation.atmBalanceCheck(1200L, atmObj.getId()));
		
	}
	
	@Test
	public void whenNoUserBalanceThenFalse() {
		UserEntity atmObj= new UserEntity(123456789L, 800.0, 200.0);
		when(userRepo.findById(atmObj.getAccountnum())).thenReturn(Optional.ofNullable(atmObj));
		assertEquals(false, atmValidation.userBalanceCheck(1100L, atmObj.getAccountnum()));
		
	}
	
	@Test
	public void whenUserBalanceThenTrue() {
		UserEntity atmObj= new UserEntity(123456789L, 800.0, 200.0);
		when(userRepo.findById(atmObj.getAccountnum())).thenReturn(Optional.ofNullable(atmObj));
		assertEquals(true, atmValidation.userBalanceCheck(600L, atmObj.getAccountnum()));
		
	}
	
	
}
