package com.zinkwork.atm;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import com.zinkwork.atm.entity.UserEntity;
import com.zinkwork.atm.exception.InSufficientBalanceException;
import com.zinkwork.atm.exception.NoAtmBalanceException;
import com.zinkwork.atm.repository.UserRepository;
import com.zinkwork.atm.service.AtmServiceImpl;
import com.zinkwork.atm.utils.CommonUtils;
import com.zinkwork.atm.validatiion.AtmValidation;

@RunWith(MockitoJUnitRunner.class)
public class AtmApplicationTests {
	
	@Rule
	public ExpectedException exceptionRule =ExpectedException.none();
	
	@Mock
	private AtmValidation atmValidation;
	
	@Mock
	private CommonUtils commonUtils;
	
	@Mock
	private UserRepository userRepository;
	
	@InjectMocks
	private AtmServiceImpl atmService;
	
	@Test
	public void withdrawAmountThenNoAtmBalanceTest() {
		exceptionRule.expectMessage("No sufficinet Balance in ATM");
		exceptionRule.expect(NoAtmBalanceException.class);
		when(atmValidation.atmBalanceCheck(ArgumentMatchers.anyLong(),ArgumentMatchers.anyInt())).thenReturn(false);
		atmService.withdrawAmount(220L,3,340L);
	}
	
	@Test
	public void withdrawAmountThenNoUserBalanceTest() {
		exceptionRule.expectMessage("No sufficient balance in account");
		exceptionRule.expect(InSufficientBalanceException.class);
		when(atmValidation.atmBalanceCheck(ArgumentMatchers.anyLong(),ArgumentMatchers.anyInt())).thenReturn(true);
		when(atmValidation.userBalanceCheck(ArgumentMatchers.anyLong(), ArgumentMatchers.anyLong())).thenReturn(false);
		atmService.withdrawAmount(220L, 3, 340L);
	}
	
	@Test
	public void checkBalanceThenNoUserBalanceTest() {
		UserEntity userEntity = new UserEntity();
		userEntity.setBalance(10.0);
		userEntity.setAccountnum(123456789L);
		when(userRepository.findById(ArgumentMatchers.anyLong())).thenReturn(Optional.ofNullable(userEntity));
		assertEquals(new Double(10.0), atmService.retrieveBalance(userEntity.getAccountnum()));
	}
	
}
