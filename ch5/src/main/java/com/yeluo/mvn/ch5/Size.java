package com.yeluo.mvn.ch5;
/**
 * 
 * @author YeLuo
 * @function
 */
/*
 * 1.在枚举类型中，构造器只能是私有的。
 * 
 * 2.由于枚举类型默认继承Enum类，故枚举类型不能在继承其他类。
 */
public enum Size  {
	//利用构造方法 传递参数
	SMALL("S"),MEDIUM("M"),LARGE("L"),EXTRA_LARGE("XL");
	//定义私有域
	private String abbreviation;
	//私有化构造器
	private Size(String abbreviation){
		this.abbreviation=abbreviation;
	}
	//实例域abbreviation的访问器
	public String getAbbreviation(){
		return this.abbreviation;
	}
}
