package com.zinkwork.atm.utils;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.stereotype.Component;

@Component
public class NoteDispenserUtils {

	static Integer minCoin = Integer.MAX_VALUE;
    static ArrayList<Integer> res = new ArrayList<>();
    
    public static void initialise() {
    	minCoin = Integer.MAX_VALUE;
    	res = new ArrayList<>();
    }

    public static void backtrack(Long amount, Integer count, Integer start, ArrayList<Integer> path, Integer[][] coins){
        if(amount == 0){
            if(minCoin > count ){
                res = path; //used notes array
                minCoin = count;
//                path = new ArrayList<>();
            }
        }else if(amount > 0){
            for(int i = 0; i < coins.length;i++){
                if(coins[i][1]>0){
                    coins[i][1] = coins[i][1] - 1; //Coin[i][0]= Notecount ;reduce the used coin
                    ArrayList newPath = new ArrayList(path);
                    newPath.add(coins[i][0]); //Coin[i][0]= Note ;add the reduced coin to the path
                    backtrack(amount - coins[i][0], count + 1, i, newPath , coins);
                }
            }
        }
    }

    public static ArrayList<Integer> coinChange(Integer[][] coins, Long amount){
    	initialise();
        //implement sort descending based on denomination
        ArrayList<Integer> path = new ArrayList<>();
        backtrack(amount,0,0, path,coins);
        System.out.println(res);
        if(minCoin == Integer.MAX_VALUE){
            return null;
        }
        

        
        return res;
    }
}
