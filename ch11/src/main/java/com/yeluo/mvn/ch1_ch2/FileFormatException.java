package com.yeluo.mvn.ch1_ch2;

import java.io.IOException;
/**
 * 
 * @author YeLuo
 * @function
 */
/*
 * 自定义异常类包含两个构造器,一个是默认的构造器;另一个是带有详细描述信息的构造器.
 * 习惯上,定义的类应该
 */
public class FileFormatException extends IOException {
	//默认的构造器
	public FileFormatException(){
		
	}
	
	//带有详细描述信息的构造器
	public FileFormatException(String description){
		super(description);
	}
}
