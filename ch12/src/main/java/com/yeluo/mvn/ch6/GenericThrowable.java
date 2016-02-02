package com.yeluo.mvn.ch6;
/**
 * 
 * @author YeLuo
 * @function
 * @param <T>
 */
/*
 * error
 * The generic class GenericThrowable<T> may not subclass java.lang.Throwable
 */
/*public class GenericThrowable<T> extends Exception {

}*/
public class GenericThrowable<T>{
	public static void main(String[] args) {
		try {
			
		}finally{
			
		}
		/*
		 * error
		 * Cannot make a static reference to the non-static type T
		 */
		/*catch (T e) {
			
		}*/
	}
}