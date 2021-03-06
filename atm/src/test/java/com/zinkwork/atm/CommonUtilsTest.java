package com.zinkwork.atm;

import static org.hamcrest.CoreMatchers.theInstance;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Optional;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.MockedStatic.Verification;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;

import com.zinkwork.atm.entity.AtmEntity;
import com.zinkwork.atm.entity.NoteEntity;
import com.zinkwork.atm.entity.UserEntity;
import com.zinkwork.atm.exception.NoNotesAvailableException;
import com.zinkwork.atm.repository.AtmRepository;
import com.zinkwork.atm.repository.NoteRepository;
import com.zinkwork.atm.repository.UserRepository;
import com.zinkwork.atm.utils.CommonUtils;
import com.zinkwork.atm.utils.NoteDispenserUtils;

@RunWith(MockitoJUnitRunner.class)
public class CommonUtilsTest {
	
	@Mock
	private NoteDispenserUtils noteDispenser;
	
	@Mock
	private AtmRepository atmRepo;
	
	@Mock
	private UserRepository userRepo;
	
	@Mock
	private NoteRepository noteRepo;
	
	@InjectMocks
	private CommonUtils commonUtils;
	
	@Rule
	public ExpectedException exception= ExpectedException.none();
	
	@Test(expected = NoNotesAvailableException.class)
	public void whenNoNotesThenException() {
		
		UserEntity userObj= new UserEntity(123456789L, 800.0, 200.0);
		//NoteEntity note= new NoteEntity();
		AtmEntity atmObj= new AtmEntity(1, 1500.0, new HashSet<NoteEntity>());
//		exception.expectMessage("Notes not available. Please try other amount");
//		exception.expect(NoNotesAvailableException.class);
		when(userRepo.findById(userObj.getAccountnum())).thenReturn(Optional.ofNullable(userObj));
		when(atmRepo.findById(atmObj.getId())).thenReturn(Optional.ofNullable(atmObj));
		ArrayList<Integer> ii = new ArrayList<>();
		ii.add(1);
		try(MockedStatic<NoteDispenserUtils> theMock = Mockito.mockStatic(NoteDispenserUtils.class)){
		
			theMock.when(()->NoteDispenserUtils.coinChange(ArgumentMatchers.any(), ArgumentMatchers.any())).thenReturn(null);
									commonUtils.deductAmount(2000L, atmObj.getId(), userObj.getAccountnum());
			
		}
		
		
//		ii.add(2);
//		assertEquals(null, commonUtils.deductAmount(2000L, atmObj.getId(), userObj.getAccountnum()));
		
		
	}

}
