package com.yeluo.mvn.ch;
/**
 * 
 * @author YeLuo
 * @function
 */
public class UnsynchBankTest {
	//定义一个银行的账户数量为100个
	public static final int NACCOUNTS=100;
	//定义一个银行的每个账户的初始金额为1000
	public static final double INITIAL_BALANCE=1000;
	
	public static void main(String[] args) throws InterruptedException {
		Bank b=new Bank(NACCOUNTS, INITIAL_BALANCE);
		for(int i=0;i<NACCOUNTS;i++){
			TransferRunnable r=new TransferRunnable(b, i, INITIAL_BALANCE);
			Thread t=new Thread(r);
			t.start();	
		}			
	
	}
}
