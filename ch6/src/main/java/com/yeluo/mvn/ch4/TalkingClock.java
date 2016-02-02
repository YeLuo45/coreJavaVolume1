package com.yeluo.mvn.ch4;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.Timer;

/**
 * 
 * @author YeLuo
 * @function  语音时钟
 */
public class TalkingClock {
	private int interval;
	private boolean beep;
	
	public TalkingClock(int interval,boolean beep){
		this.interval=interval;
		this.beep=beep;
	}
	
	public void start(){
		//在外围类内部创建内部类的实例
		//1.构造内部类
		ActionListener tp=new TimePrinter();
		//2.构造内部类
		ActionListener listener=this.new TimePrinter();
		//Timer t=new Timer(interval, tp);
		Timer t=new Timer(interval, listener);
		t.start();
	}
	
	/*
	 * inner class
	 */
	public class TimePrinter implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			Date now=new Date();
			System.out.println("At the tone,the time is "+now);
			//1.外围类的隐式引用
			/*if(beep){
				Toolkit.getDefaultToolkit().beep();
			}*/
			//2.OutClass.this
			if(TalkingClock.this.beep){
				Toolkit.getDefaultToolkit().beep();
			}
		}
		
	}
}
