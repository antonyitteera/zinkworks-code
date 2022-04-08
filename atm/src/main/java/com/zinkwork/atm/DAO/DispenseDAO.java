package com.zinkwork.atm.DAO;

import java.util.ArrayList;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DispenseDAO {
	
	private String message;
	private ArrayList<Integer> notes;
	private Double availableBalance;
}
