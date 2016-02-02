package com.yeluo.mvn.ch1_ch5;
/**
 * 
 * @author YeLuo
 * @function
 */
public class Generic<E> {
	private E[] elementData;
	private int count=0;  
	/*
	 * 默认构造16个空间
	 */
	public Generic(){
		elementData=(E[]) new Object[16];
	}
	/*
	 * 获得指定索引的元素
	 */
	public E get(int i) throws Exception{
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
	public  boolean add(E element) throws Exception{
		int length=elementData.length;
		if(count>=length){
			throw new Exception("最多添加"+length+"个元素,不能再添加元素了");
		}
		elementData[count++]=element;
		return true;
	}
	
}
