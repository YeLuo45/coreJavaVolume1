package com.yeluo.mvn.ch;
/**
 * 
 * @author YeLuo
 * @function
 */
public class Bank {
	//所有账户的集合数组
	private final double[] accounts;
	
	/**
	 * 根据指定的n来创建n个账户
	 * 根据指定的initialBalance来初始化每个账户的金额
	 * @param n
	 * @param initialBalance
	 */
	public Bank(int n,double initialBalance){
		accounts=new double[n];
		for(int i=0;i<accounts.length;i++){
			accounts[i]=initialBalance;
		}
	}
	/**
	 * 从账户from转账amount金额到账户to
	 * @param from
	 * @param to
	 * @param amount
	 */
	public void transfer(int from,int to,double amount){
		if(accounts[from]<amount){
			return;
		}
		System.out.println(Thread.currentThread());
		accounts[from]-=amount;
		System.out.printf("%10.2f from %d to %d",amount,from,to);
		accounts[to]+=amount;
		System.out.printf("Total Balance:%10.2f%n",getTotalBalance());
	}
	
	/**
	 * 获得银行里所有账户总的金额
	 * @return
	 */
	public double getTotalBalance() {
		double sum=0;
		for(double d:accounts){
			sum+=d;
		}
		return sum;
	}
	/**
	 * 返回银行里账户的数量
	 * @return
	 */
	public int size(){
		return accounts.length;
	}
	
}
