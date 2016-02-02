
package com.yeluo.mvn.ch1;
/**
 * 
 * @author YeLuo
 * @function
 */
public class Father {	
	private String name;
	private Integer age;
	private Long ID;
	public Father(){
		
	}
	public Father(String name,Integer age,Long ID){
		this.name=name;
		this.age=age;
		this.ID=ID;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public Long getID() {
		return ID;
	}
	public void setID(Long iD) {
		ID = iD;
	}
	
}
