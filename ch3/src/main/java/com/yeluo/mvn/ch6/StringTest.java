package com.yeluo.mvn.ch6;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * 
 * @author YeLuo
 * @function  字符串
 */
/*
 * 1.每个用双引号括起来的字符都是String类的一个实例
 * 2.String和StringBuffer的异同
 * 	1.String对象传递的时候是值传递,StringBuffer是引用传递.
	String是一种强不变类型,它的值一旦被赋予之后,在内存中的相应位置上的值就不会变化了.
	即便你用String的方法:concat(String str)和replace(char oldChar, char newChar)等等,
	所返回的值都是新创建的一个String类型,而不是在原内存地址上去更改,因此比如这样的一个赋值: 
	String s="Hello Baidu"; 
	s=new String("Hello Baidu"); 
	这样在内存中其实是开辟了两个内存空间来存放Hello Baidu,前面那个对象依然留在内存当中,而: 
	s=s.concat("!"); 
	加上这样一句,s的值变为了Hello Baidu!,在内存中实际上是新创建了一个String对象装Hello Baidu!,
	而原有的Hello Baidu依然存在 StringBuffer就不同了,它是种可变类型,它的值被赋予之后,
	在运行期同样可以通过它的方法,如append(String str) 操作内存上的值,而不仅仅是它的引用而已,
	也就是说同样的对s的值进行修改 
	StringBuffer s="Hello Baidu"; 
	s.append("!"); 
	这样的操作其实只创建了一个StringBuffer的对象,大大节约了内存的开销.从回收的角度上来讲的话,
	显然StringBuffer对内存的消耗要小的多.
 * 
 * 3.代码点：是指与一个编码表中的某个字符对应的代码值。
 * 	 代码单元：在基本的多语言级别中，每个字符用16位表示，通常称为代码单元。
 *  Java字符串有char序列组成。大多数常用的Unicode字符使用一个代码单元就可以表示，而辅助字符需要一对代码单元表示。
 *  lenth方法将返回采用UTF-16编码表示的给定字符串所需要的代码单元数量
 *  codePointCount方法将返回实际的长度，代码点数量
 */
public class StringTest {
	public static void main(String[] args) {
		stringTest();
	}

	private static void stringTest() {
		/*
		 * subString方法
		 * public String substring(int beginIndex,int endIndex)
		 * 返回一个新字符串，它是此字符串的一个子字符串。该子字符串从指定的 beginIndex 处开始，直到索引 endIndex - 1 处的字符。
		 * 因此，该子字符串的长度为 endIndex-beginIndex。这是substring的工作方式的一个优点：容易计算子串长度                        
		 */
		String s="Hello";
		s=s.substring(0, 3);
		System.out.println(s);
		
		/*
		 * 拼接
		 * 用+好链接两个字符串
		 * 当一个字符串与一个非字符串的值进行连接时，后者被转换成字符串（任何一个Java对象都可以转换成字符串）
		 */
		List<String> l=new ArrayList<String>();
		l.add(s);
		String s1="123"+l;
		System.out.println(l);
		System.out.println(l.toString());
		System.out.println(s1);
		
		/*
		 * 不可变字符串：在Java文档中将String类对象称为不可变字符串。
		 * 设计String的模式是不变模式。链接：http://note.youdao.com/share/?id=65dfdeb8b43070c93b55a78a425ce3e7&type=note
		 * 1.String类里没有提供用于修改字符串的方法。
		 * 2.String类里所有的属性都是私有的。
		 * 3.没有引用到其他可变的对象
		 * 4.String类用fianl修饰
		 * 综上，String是强不变模式。
		 * 不可变字符串的一个优点：编译器可以让字符串共享。
		 * 在Java设计者看来，共享带来的高效率圆圆胜过提取、拼接字符串所带来的低效率。
		 * 
		 * 测试字符串是否可修改
		 */
		String s2="string";
		String s3=s2;
		s2=s2+"!";
		if(s3==s2){
			System.out.println("在Java中，字符串可修改！");
		}else{
			System.out.println("在Java中，字符串不可修改！");
		}
		
		/*
		 * 检测字符串是否相等
		 * 用equals方法
		 * 用==只能验证是否指向同一个地址
		 */
		String s4=new String("string");
		String s5="string";
		if(s4==s3){
			System.out.println("s4：两个字符串放置在同一个位置!");//不输出
		}
		if(s5==s3){
			System.out.println("s5：两个字符串放置在同一个位置!");//不输出
		}
		/*
		 * 由上可知：一定不能使用==检测两个字符串是否相同。
		 * 如果虚拟机始终将相同的字符串共享，就可以使用==运算符检测是否相等。
		 * 但实际上，只有字符串常量是共享的，而+或substring等操作产生的结果并不是共享的。
		 * 因此，一定不要使用==运算符测试字符串的相等性，以免在程序中出现糟糕的bug。
		 * 而C++由于重载了==，故可以测试字符串的相等性；C一般使用strcmp函数
		 * public int compareTo(String anotherString)
		 * 按字典顺序比较两个字符串，如果字符串在anotherString之前返回一个负数；相等返回0；之后返回正数
		 */
		if(s3.equals(s4)){
			System.out.println("使用equals方法，s3和s4的内容相同！"); //使用equals方法更为清晰，而且可以避免空指针异常
		}
		if(s3.compareTo(s4)==0){
			System.out.println("使用compareTo方法,s3和s4的内容相同！");
		}
		//忽略大小写的比较
		if("STRING".equalsIgnoreCase(s5)){
			System.out.println("忽略大小写的比较！");
		}
		
		/*
		 * 空串和Null串
		 * 空串可以用if(emptyStr.length()==0)、if(emptyStr.equals(""))和if(emptyStr.isEmpty())
		 * 空串是一个Java对象，有自己的串长度（0）和内容（空）。
		 * null值：表示目前没有任何对象与该变量关联。    if(str==null)
		 * 要检测一个字符串既不是null也不为空串，使用if(str!=null&&str.length()!=0)
		 * 首先要检测是否为null值，否则会报NullPointerException
		 */
		String emptyStr="";
		if(emptyStr.length()==0){
			System.out.println("emptyStr是空串。");
		}
		if(emptyStr.equals("")){
			System.out.println("emptyStr是空串。");
		}
		String str=null;
		if(str==null){
			System.out.println("str是null值。");
		}
		if(s!=null&&s.length()!=0){
			System.out.println(s+"既不是null也不为空串");
		}
		
		/*
		 * length方法和codePointCount方法
		 */
		s="37游戏大厦";
		int len=s.length();
		System.out.println(len);
		int codePointNUM=s.codePointCount(0, s.length());
		System.out.println(codePointNUM);
		
		//charAt方法获得指定位置的代码单元
		char first=s.charAt(0);
		System.out.println(first);
		System.out.println(s.charAt(len-1));
		
		/*
		 * 要想得到第i个代码点，使用offsetByCodePoints方法和codePointAt方法
		 * public int offsetByCodePoints(int index, int codePointOffset)
		 * 返回此 String 中从给定的 index 处偏移 codePointOffset 个代码点的索引。
		 * 文本范围内由 index 和 codePointOffset 给定的未配对代理项各计为一个代码点。  
		 * public int codePointAt(int index)
		 * 返回指定索引处的字符（Unicode 代码点）。索引引用 char 值（Unicode 代码单元），其范围从 0 到 length() - 1                     
		 */
		int codeP1=s.offsetByCodePoints(0, 3);
		int codeP2=s.codePointAt(codeP1);
		System.out.println("codeP1="+codeP1+"  codeP2="+codeP2);
		
		/*
		 * 如果要遍历一个字符串，并且依次查看每一个代码点用下列的语句
		 * Character类的isSupplementaryCodePoint方法
		 * public static boolean isSupplementaryCodePoint(int codePoint)
		 * 确定指定字符（Unicode 代码点）是否在增补字符范围内。该方法调用以下表达式：
		 * codePoint >= 0x10000 && codePoint <= 0x10FFFF
		 * public char charAt(int index)  返回指定索引处的 char 值/代码单元。索引范围为从 0 到 length() - 1。
		 */
		int i=0;
		while(i<len){
			int cp=s.codePointAt(i);
			System.out.print(s.charAt(i));
			if(Character.isSupplementaryCodePoint(cp)){
				i+=2;
			}else{
				i++;
			}	
		}
		System.out.println();
		/*
		 * endsWith方法：测试此字符串是否以指定的后缀结束。
		 * startsWith方法：测试此字符串是否以指定的前缀开始。
		 * 
		 */
		System.out.println(s.endsWith("大厦"));  //true
		System.out.println(s.endsWith("37"));   //false
		System.out.println(s.startsWith("37")); //true
		
		/*
		 * 判断字符串是否为指定字符串的子串
		 * indexOf方法和lastOf方法
		 * 方法重载（  静态多态）：1.同一个类内  2.方法名相同  3.参数列表不同（参数类型、参数个数、参数顺序）
		 * public int indexOf(int ch)        返回指定字符在此字符串中第一次出现处的索引。
		 * public int indexOf(int ch,int fromIndex)
		 * public int indexOf(String str)    返回指定子字符串在此字符串中第一次出现处的索引。
		 * public int indexOf(String str int fromIndex)
		 * public int lastIndexOf(int ch)    返回指定字符在此字符串中最后一次出现处的索引。
		 * public int lastIndexOf(int ch, int fromIndex)
		 * public int lastIndexOf(String str)
		 * public int lastIndexOf(String str int fromIndex)                 
		 */
		//s="37游戏大厦";  codeP2是“戏”对应的代码点
		System.out.println(s.indexOf(codeP2));  //输出3
		System.out.println(s.indexOf("戏"));    //输出3
		
		/*
		 * replace方法
		 * replace(char oldChar, char newChar)
		 *  返回一个新的字符串，它是通过用 newChar 替换此字符串中出现的所有 oldChar 得到的。
		 * public String replace(CharSequence target, CharSequence replacement)
		 *  使用指定的字面值替换序列替换此字符串所有匹配字面值目标序列的子字符串。  
		 *             
		 */
		String str1="rrr";
		System.out.println(str1.replace("r", "ye"));
		/* public String replaceAll(String regex,String replacement)                        
		 * 使用给定的 replacement 替换此字符串所有匹配给定的正则表达式的子字符串。 
		 * 调用此方法的 str.replaceAll(regex, repl) 形式与以下表达式产生的结果完全相同：
		 * Pattern.compile(regex).matcher(str).replaceAll(repl)
		 * 参数：
		 * regex - 用来匹配此字符串的正则表达式     
		 * replacement - 用来替换每个匹配项的字符串 
		 * 
		 * public String[] split(String regex)  根据给定正则表达式的匹配拆分此字符串。
		 */
		String repStr="(Ye Luo Xing Cheng)";
		System.out.println(repStr.replaceAll("[\\s()]","_"));//_Ye_Luo_Xing_Cheng_
		System.out.println(repStr);  //(Ye Luo Xing Cheng)  
		System.out.println(Pattern.compile("[\\s()]").matcher(repStr).replaceAll("_"));
		
		String[] sa=repStr.split("[\\s()]");
		for(int j=0;j<sa.length;j++){
			System.out.println(sa[j]);
		}
		
		/*
		 * toLowerCase方法：使用默认语言环境的规则将此 String 中的所有字符都转换为小写。
		 * toUpperCase方法：使用默认语言环境的规则将此 String 中的所有字符都转换为大写。
		 * trim方法：返回字符串的副本，忽略前导空白和尾部空白
		 */
		String test=" YeLuo ";
		System.out.println(test.toLowerCase());
		System.out.println(test.toUpperCase());
		System.out.println(test.trim());
	}
}
