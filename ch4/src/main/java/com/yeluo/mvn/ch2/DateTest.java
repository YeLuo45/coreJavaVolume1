package com.yeluo.mvn.ch2;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 
 * @author YeLuo
 * @function 测试Date类的功能
 */
/**
 * 1.在Java程序设计语言中，使用构造器构造新实例。
 * 构造器的名字应该和类名相同。
 * 
 * 2.注意对象变量和对象的区别：一个对象变量实际上没有包含一个对象，而仅仅引用一个对象。
 * 对象是放在堆里的，而对象变量是放在栈里的。
 * 
 * 3.由于Date类不便于实现国际化，所以从JDK1.1版本开始，推荐使用Calendar类进行时间和日期处理。
 * 在类 Date 所有可以接受或返回年、月、日期、小时、分钟和秒值的方法中，将使用下面的表示形式： （特别注意年份和月份）
	年份 y 由整数 y - 1900 表示。    
	月份由从 0 至 11 的整数表示；0 是一月、1 是二月等等；因此 11 是十二月。 
	日期（一月中的某天）按通常方式由整数 1 至 31 表示。 
	小时由从 0 至 23 的整数表示。因此，从午夜到 1 a.m. 的时间是 0 点，从中午到 1 p.m. 的时间是 12 点。 
	分钟按通常方式由 0 至 59 的整数表示。 
	秒由 0 至 61 的整数表示；值 60 和 61 只对闰秒发生，尽管那样，也只用在实际正确跟踪闰秒的 Java 实现中。于按当前引入闰秒的方式，两个闰秒在同一分钟内发生是极不可能的，但此规范遵循 ISO C 的日期和时间约定。 
	在所有情形中，针对这些目的赋予方法的参数不需要在指定的范围内；例如，可以把日期指定为 1 月 32 日，并把它解释为 2 月 1 日的相同含义。 

 * 4.Date类是用来表示时间点的
 * 
 * 5.当类库设计者意识到某个方法不应该存在时，就把它标记为不鼓励使用（过时）
 */
public class DateTest {
	public static void main(String[] args) {
		dateTest();
		dateFieldTest();
	}
	/**
	 * Date类的成员变量
	 * 使用Date类中对应的get方法，可以获得Date类对象中相关的信息，
	 * 需要注意的是使用getYear获得是Date对象中年份减去1900以后的值，
	 * 所以需要显示对应的年份则需要在返回值的基础上加上1900，月份类似。
	 * 在Date类中还提供了getDay方法，用于获得Date对象代表的时间是星期几，
	 * Date类规定周日是0，周一是1，周二是2，后续的依次类推。（注意）
	 * 
	 * 注意：当类库设计者意识到某个方法不应该存在时，就把它标记为不鼓励使用（过时）
	 */
	private static void dateFieldTest() {
		Date date=new Date();
		//这些方法都过时了
		int  year=date.getYear();  // 从 JDK 1.1 开始，由 Calendar.get(Calendar.DAY_OF_MONTH) 取代。
		int  month=date.getMonth();
		int  day=date.getDate();
		int  hour=date.getHours();
		int  minute=date.getMinutes();
		int  second=date.getSeconds();
		int  dow=date.getDay();
		System.out.println("年份:"+year);   //年份:115   2015-1900
		System.out.println("月份:"+month);  //月份:11    12-1
		System.out.println("日期:"+day);    //日期:10
		System.out.println("时:"+hour);     //时:11
		System.out.println("分:"+minute);   //分:56
		System.out.println("秒:"+second);   //秒:48
		System.out.println("星期:"+dow);		//星期:4
		
		date.setYear(2000-1900);
		date.setMonth(1-1);
		date.setDate(5);
		date.setHours(12);
		date.setMinutes(59);
		date.setSeconds(13);
		System.out.println(date.toLocaleString());  //2000-1-5 12:59:13
	}
	/**
	 * 构造方法
	 * 测试两个Date类对象是否相等
	 */
	private static void dateTest() {
		//默认系统时间
		
		Date date=new Date();
		//默认格式： dow mon dd hh:mm:ss zzz yyyy    dow-星期            zzz-时区       例如：CST-中国标准时间
		System.out.println(date);  //输出当前系统时间   Thu Dec 10 11:27:36 CST 2015
		System.out.println(date.toLocaleString());  //输出本地格式的系统时间：2015-12-10 12:08:10
		System.out.println(date.toGMTString()); //10 Dec 2015 04:09:32 GMT  使用 GMT TimeZone
		//1.自定义输出格式   SimpleDateFormat类
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH：mm：ss");
		System.out.println(sdf.format(date));  //2015-12-10 12：27：20
		System.out.println(sdf.toPattern());    //yyyy-MM-dd HH：mm：ss  输出模式
		//2.自定义输出格式
		/*	DateFormat是一个抽象类，不能直接new
		 *  SHORT 完全为数字，如 12.13.52 或 3:30pm 
			MEDIUM 较长，如 Jan 12, 1952 
			LONG 更长，如 January 12, 1952 或 3:30:32pm 
			FULL 是完全指定，如 Tuesday、April 12、1952 AD 或 3:30:42pm PST。 
		 */
		DateFormat df=DateFormat.getDateInstance();
		System.out.println(df.format(date));   //2015-12-10
		df=DateFormat.getDateInstance(DateFormat.SHORT);
		System.out.println(df.format(date));  //15-12-10
		df=DateFormat.getDateInstance(DateFormat.MEDIUM);
		System.out.println(df.format(date));  //2015-12-10
		
		
		//指定时间
		Date date1=new Date(2015-1900,12-1,10);   //该构造方法过时了
		System.out.println(date1);       //Thu Dec 10 00:00:00 CST 2015
		
		/*
		 * 5. String 和 Date ，Long 之间相互转换 （最常用）
		 * 字符串转化成时间类型（字符串可以是任意类型，只要和SimpleDateFormat中的格式一致即可）
		 */
		String strTime="2000-5-24 12：13：14";
		try {
			date=sdf.parse(strTime);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		System.out.println(sdf.format(date));  //2000-05-24 12：13：14
		//将Date类对象转换为相对时间
		//UTC时间   1970 年 1 月 1 日 00:00:00
		long relativeTime=date1.getTime();
		System.out.println(relativeTime);  //1449676800000    返回自 1970 年 1 月 1 日 00:00:00 GMT 以来此 Date 对象表示的毫秒数。
		
		//将相对时间转换为Date类对象
		Date date2=new Date(1449676800000L);
		System.out.println(date2);         //Thu Dec 10 00:00:00 CST 2015
		
		/*
		 * 通过时间求时间
		 */
		
		//年月周求日期
		SimpleDateFormat formatter2 = new SimpleDateFormat("yyyy-MM F E");
		try {
			date2= formatter2.parse("2003-05 5 星期五");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		SimpleDateFormat formatter3 = new SimpleDateFormat("yyyy-MM-dd");
		String mydate2=formatter3.format(date2);
		System.out.println(mydate2);  //2003-05-30

		//求是星期几
		try {
			date2= formatter3 .parse("2001-1-1");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		SimpleDateFormat formatter4 = new SimpleDateFormat("E");
		String mydate3=formatter4.format(date2);
		System.out.println(mydate3);  //星期一
		
		//测试两个对象管是否相等
		boolean result=date1.equals(date2);
		boolean result1=date1.equals(date);
		int i=date1.compareTo(date2);
		int i1=date1.compareTo(date);
		System.out.println("equals:"+result+" compare:"+i);    //equals:true compare:0
		System.out.println("equals:"+result1+" compare:"+i1);   //equals:false compare:-1
	}
}
