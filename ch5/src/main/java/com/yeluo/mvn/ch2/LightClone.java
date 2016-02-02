package com.yeluo.mvn.ch2;
/**
 * 
 * @author YeLuo
 * @function
 */
public class LightClone implements Cloneable{
	private int i;
	private long l;
	private String s;
	private StringBuilder sb;
	public LightClone(int i, long l, String s,StringBuilder sb) {
		super();
		this.i = i;
		this.l = l;
		this.s = s;
		this.sb=sb;
	}
	public StringBuilder getSb() {
		return sb;
	}
	public void setSb(StringBuilder sb) {
		this.sb = sb;
	}
	public int getI() {
		return i;
	}
	public void setI(int i) {
		this.i = i;
	}
	public long getL() {
		return l;
	}
	public void setL(long l) {
		this.l = l;
	}
	public String getS() {
		return s;
	}
	public void setS(String s) {
		this.s = s;
	}
	@Override
	public String toString() {		
		return getClass().getName()+"[ i="+i+",l="+l+",s="+s+",sb="+sb+" ]";
	}
	@Override
	public LightClone clone() throws CloneNotSupportedException {
		return (LightClone)super.clone();
	}
}
