package com.yeluo.mvn.ch1_ch5;
/**
 * 
 * @author YeLuo
 * @function
 */
/*
 * before generic classes
 */
public class BeforeGeneric {
	private Object[] elementData;
	private int count=0;
	public BeforeGeneric(){
		elementData=new Object[16];
	}
	/*
	 * 获得指定索引的元素
	 */
	public Object get(int i) throws Exception{
		int length=elementData.length;
		if(i<0){
			throw new Exception("索引的范围不为负的!");
		}
		if(i>=length){
			throw new Exception("索引的范围不能超过数组的长度:"+length);
		}
		return elementData[i];
	}
	/*
	 * 添加元素
	 */
	public void add(Object object) throws Exception{
		int length=elementData.length;
		if(count>=length){
			throw new Exception("最多添加"+length+"个元素,不能再添加元素了");
		}
		elementData[count++]=object;
	}
}
