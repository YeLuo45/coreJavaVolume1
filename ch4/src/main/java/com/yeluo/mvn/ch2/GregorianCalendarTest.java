package com.yeluo.mvn.ch2;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * 
 * @author YeLuo
 * @function 测试GregorianCalendar类
 */
/*
 * 1.GregorianCalendar类是用来表示大家所熟悉的日历表示法
 * GregorianCalendar 是 Calendar 的一个具体子类，提供了世界上大多数国家/地区使用的标准日历系统
 * 
 * 2.当类库设计者意识到某个方法不应该存在时，就把它标记为不鼓励使用（过时）。
 * 最好不要使用这部分方法，因为他们有可能在未来的类库版本中删去。
 * 
 * 3.GregorianCalendar 为每个日历字段使用以下默认值:
 * http://note.youdao.com/share/?id=184117460e7e52636ca27acd1f55b13d&type=note
 * 
 * 4.GregorianCalendar继承Calendar类，所以用法主要继承去父类Calendar。
 * 
 * 5.一般来说很少独自去创建这样的一个类的对象， 而是通过Calendar来获得这样的一个类的对象。其中的方法也都是只是实现了一些
 *    Calendar中的abstract方法， 在这里不做过多的演示。
 */
public class GregorianCalendarTest {
	public static void main(String[] args) {
		GCTest();
	}

	/*
	 * 1.构造器
	 * GregorianCalendar() 
                     在具有默认语言环境的默认时区内使用当前时间构造一个默认的 GregorianCalendar。 
	   GregorianCalendar(int year, int month, int dayOfMonth) 
                     在具有默认语言环境的默认时区内构造一个带有给定日期设置的 GregorianCalendar。 
	   GregorianCalendar(int year, int month, int dayOfMonth, int hourOfDay, int minute) 
                    为具有默认语言环境的默认时区构造一个具有给定日期和时间设置的 GregorianCalendar。 
	   GregorianCalendar(int year, int month, int dayOfMonth, int hourOfDay, int minute, int second) 
                    为具有默认语言环境的默认时区构造一个具有给定日期和时间设置的 GregorianCalendar。 
	 *
	 *
	 */
	private static void GCTest() {
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		//1.初始化
		GregorianCalendar gc=new GregorianCalendar();
		System.out.println(sdf.format(gc.getTime())); //2015-12-10 20:29:13
		
		GregorianCalendar gc1=new GregorianCalendar(2015, Calendar.DECEMBER,10);
		System.out.println(sdf.format(gc1.getTime())); //2015-12-10 00:00:00
		
		GregorianCalendar gc2=new GregorianCalendar(2015, Calendar.DECEMBER, 10, 23, 59);
		System.out.println(sdf.format(gc2.getTime())); //2015-12-10 23:59:00
		
		GregorianCalendar gc3=new GregorianCalendar(2015, Calendar.DECEMBER,10, 23, 59, 59);
		System.out.println(sdf.format(gc3.getTime()));  //2015-12-10 23:59:59
	}
}
