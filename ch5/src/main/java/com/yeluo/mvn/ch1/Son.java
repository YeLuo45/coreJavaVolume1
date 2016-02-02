package com.yeluo.mvn.ch1;

public class Son extends Father {
	private String feeling;
	public Son(){
		super();
	}
	/*
	 *  子类的构造函数如果要引用super的话，必须把super放在函数的首位.
	 *  子类的构造函数如果要引用this的话，必须把this放在函数的首位.
	 *  在调用构造器的时候，这两个关键字的使用方式很相似。调用构造器的语句只能作为另一个构造器的第一条语句。
	 * 也就是说在子类的构造器中不能同时使用super和this这两个关键字。
	 */
	public Son(String name,Integer age,Long ID,String feeling){
		
		super(name,age,ID);
		//this();  ////error   Constructor call must be the first statement in a constructor
		this.feeling=feeling;
		//super(name,age,ID);//error   Constructor call must be the first statement in a constructor
	}
	
	public void toFatherString(){
		//System.out.println(super.name);  //The field Father.name is not visible
		System.out.println(super.getName()+" "+super.getAge()+" "+super.getID());
		System.out.println(this.feeling);
	}
}
