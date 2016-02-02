package com.yeluo.mvn.ch2;

import java.util.Date;

public class DeepClone implements Cloneable{
	private StringBuilder sb;
	private Date birthday;

	public DeepClone(StringBuilder sb, Date birthday) {
		super();
		this.sb = sb;
		this.birthday = birthday;
	}

	public DeepClone(StringBuilder sb) {
		super();
		this.sb = sb;
	}

	public StringBuilder getSb() {
		return sb;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public void setSb(StringBuilder sb) {
		this.sb = sb;
	}
	@Override
	public String toString() {		
		return getClass().getName()+"[ sb="+sb+",birthday="+birthday+" ]";
	}
	@Override
	public DeepClone clone() throws CloneNotSupportedException {
		DeepClone deepClone=(DeepClone)super.clone();
		//由于StringBuilder的对象是可变对象，需要对其克隆，进行深拷贝 
		//StringBuilder类实现的克隆，直接调用即可
		//StringBuilder类没有实现Cloneable接口和重写clone方法，故不能调用clone方法。
		//deepClone.sb=(StringBuilder) this.sb.clone();   
		//问题：StringBuilder的对象怎么进行克隆？？？
		deepClone.birthday=(Date) this.birthday.clone();
		return deepClone;
	}
}
