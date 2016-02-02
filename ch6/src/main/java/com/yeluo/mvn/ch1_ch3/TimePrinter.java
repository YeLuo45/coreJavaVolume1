package com.yeluo.mvn.ch1_ch3;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

/**
 * 
 * @author YeLuo
 * @function
 */
public class TimePrinter implements ActionListener{

	public void actionPerformed(ActionEvent e) {
		Date now=new Date();
		System.out.println("At the tone,the time is "+now);
		Toolkit.getDefaultToolkit().beep();
	}
	
}
