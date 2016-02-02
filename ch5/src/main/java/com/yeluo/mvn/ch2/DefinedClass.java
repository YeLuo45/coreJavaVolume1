package com.yeluo.mvn.ch2;
/**
 * 
 * @author YeLuo
 * @function equals方法和hashCode方法
 */
/**
 * 
 */
public class DefinedClass {
	private String name;
	private int age;
	
	public DefinedClass(){
		
	}
	/**
	 * 重写equals方法
	 * 
	 */
	@Override
	public boolean equals(Object otherObject) {
		//第一步:检查是否为指向同一地址
		if(this==otherObject){
			return true;
		}
		//第二步:检测otherObject是否为null
		if(otherObject==null){
			return false;
		}
		//附加条件:equals的语义在每个子类中有所改变(子类有自己的相等概念)
		//第三步:检测otherObject是否与当前对象属于同一个类
		if(getClass()!=otherObject.getClass()){
			return false;
		}
		//附加条件:equals的语义在每个子类中拥有统一的语义(超类决定相等的)
		//第三步:检测otherObject是否与当前对象属于同一个类
		/*if(!(otherObject instanceof DefinedClass.class)){
			return false;
		}*/
		//第四步:检测otherObject的所有域是否与当前对象的相等
		DefinedClass other=(DefinedClass) otherObject;
		if(other.age==age){
			if(other.name==name){
				return true;
			}
		}
		return false;
	}
	/**
	 * 重写hashCode方法
	 */
	@Override
	public int hashCode() {
		
		return 7*name.hashCode()
				+11*new Integer(age).hashCode();
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
	
}
