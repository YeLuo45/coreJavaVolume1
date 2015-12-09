package com.yeluo.mvn.ch6;
/**
 * 
 * @author YeLuo
 * @function StringBuilder
 */
/*
 * 1.问题：由于采用字符串链接的方式达到此目的效率比较低。每次构建一个新的String对象，既耗时，有浪费空间。
 * 解决：使用StringBuilder类（线程不安全,效率高）和StringBuffer类（线程安全，效率低）就可以避免这个问题的发生。
 * 
 */
public class StringBuilderTest {
	public static void main(String[] args) {
		StringBuildTest();
	}
	/*
	 * 测试StringBuilder类
	 */
	private static void StringBuildTest() {
		/*
		 * append方法的返回值是this，故可以连续调用。
		 * 该方法也重载了
		 * public StringBuilder appendCodePoint(int codePoint)
		 * 将 codePoint 参数的字符串表示形式追加到此序列。
		 */
		StringBuilder strB=new StringBuilder();
		strB.append("Y").append("e");   
		System.out.println(strB);
		System.out.println(strB.appendCodePoint(48)); //48对应Unicode的0的代码点
		
		//在构建字符串时，调用toString方法
		String s=strB.toString();
		System.out.println(s);
		
		/*
		 *  void setCharAt(int index, char ch)     将给定索引处的字符设置为 ch。
		 *  StringBuilder insert(int offset, char c)  将 char 参数的字符串表示形式插入此序列中。
		 *  这个方法也重载了
		 *  public StringBuilder delete(int start,int end)
		 *  移除此序列的子字符串中的字符。该子字符串从指定的 start 处开始，一直到索引 end - 1 处的字符，
		 *  如果不存在这种字符，则一直到序列尾部。如果 start 等于 end，则不发生任何更改。 
		 *  参数： start - 起始索引（包含）             end - 结束索引（不包含）。 
		 */
		strB.setCharAt(2, 'l');
		System.out.println(strB);
		System.out.println(strB.insert(2, "123456"));
		System.out.println(strB.delete(2, 8));
		/*
		 * int capacity() 返回当前容量。 容量指可用于最新插入字符的存储量，超过这一容量便需要再次分配。 
		 */
         System.out.println(strB.capacity()); //默认初始容量为16
         
         /*
          *public StringBuilder reverse()
          *将此字符序列用其反转形式取代。 
          */
         System.out.println(strB.reverse());
	}
}
