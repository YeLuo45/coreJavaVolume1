package com.yeluo.mvn.ch;
/**
 * 
 * @author YeLuo
 * @function
 */
public class TransferRunnable implements Runnable {
	//定义一个银行属性
	private Bank bank;
	//定义一个转账账户
	private int fromAccount;
	//定义一个最大转账金额
	private double maxAmount;
	//定义一个延迟参数
	private int DEDAY=10000;
	
	/**
	 * 创建一个转账线程
	 * @param b 	银行
	 * @param from	转账账户
	 * @param max	最大转账金额
	 */
	public TransferRunnable(Bank b,int from,double max){
		bank=b;
		fromAccount=from;
		maxAmount=max;
	}

	@Override
	public void run() {
		try{
			while(true)
			{
				//随机生成一个银行账户
				int toAccount=(int) (bank.size()*Math.random());
				//随机生成一个转账金额
				//double amount=maxAmount*Math.random();
				//指定数额的转账
				double amount=maxAmount;
				//调用银行的转账功能
				bank.transfer(fromAccount, toAccount, amount);
				//线程睡眠
				Thread.sleep((long) (DEDAY*Math.random()));
			}
		}catch(InterruptedException e){
			
		}
		
	}
	
}
