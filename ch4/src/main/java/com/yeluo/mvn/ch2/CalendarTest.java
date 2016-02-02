package com.yeluo.mvn.ch2;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.SynchronousQueue;

/**
 * 
 * @author YeLuo
 * @function  测试Calendar类
 */
/*
 * 1.对实例域做出修改的方法称为更改器方法，仅访问实例域而不进行修改的方法称为访问器方法。
 * 
 * 2.日历的作用是提供某个时间点的年、月、日等信息。
 * 
 */
 
public class CalendarTest {
	public static void main(String[] args) {
		/*calendarTest();
		Calendar ca1=Calendar.getInstance();
		Calendar ca2=Calendar.getInstance();
		ca1.set(2014, 6-1, 29);
		ca2.set(2015, 12-1, 10);
		int i=getIntervalDays(ca1,ca2);
		System.out.println(i);*/
		fieldTest();
		
		
	}
	/*
	 * 字段详情
	 *  ERA：
	 *  指示年代的 get 和 set 的字段数字，比如罗马儒略历中的 AD 或 BC。这是一个特定于日历的值；
	 *  YEAR：
	 *  指示年的 get 和 set 的字段数字。这是一个特定于日历的值；
	 *  MONTH：
	 *  指示月份的 get 和 set 的字段数字。这是一个特定于日历的值。
	 *  在格里高利历和罗马儒略历中一年中的第一个月是 JANUARY，它为 0；最后一个月取决于一年中的月份数。
	 *  DATE（DAY_OF_MONTH ）:
	 *  get 和 set 的字段数字，指示一个月中的某天。它与 DAY_OF_MONTH 是同义词。一个月中第一天的值为 1。
	 *  
	 *  WEEK_OF_YEAR:
	 *  get 和 set 的字段数字，指示当前年中已过的星期数。正如 getFirstDayOfWeek() 和 getMinimalDaysInFirstWeek() 所定义的那样，
	 *  一年中第一个星期的值为 1。子类定义一年第一个星期之前的天数，即 WEEK_OF_YEAR 的值。 
	 *  WEEK_OF_MONTH：
	 *  get 和 set 的字段数字，指示当前月中已过的星期数。正如 getFirstDayOfWeek() 和 getMinimalDaysInFirstWeek() 所定义的那样，
	 *  一个月中第一个星期的值为 1。子类定义一个月第一个星期之前的天数，即 WEEK_OF_MONTH 的值。 
	 *  DAY_OF_YEAR：
	 *  get 和 set 的字段数字，指示当前年中已过的天数。一年中第一天的值为 1。
	 *  HOUR：
	 *  get 和 set 的字段数字，指示上午或下午的小时。HOUR 用于 12 小时制时钟 (0 - 11)。中午和午夜用 0 表示，不用 12 表示。
	 *  例如，在 10:04:15.250 PM 这一时刻，HOUR 为 10。 
	 *  HOUR_OF_DAY:
	 *  get 和 set 的字段数字，指示一天中的小时。HOUR_OF_DAY 用于 24 小时制时钟。
	 *  例如，在 10:04:15.250 PM 这一时刻，HOUR_OF_DAY 为 22。
	 *  MINUTE:
	 *  get 和 set 的字段数字，指示一小时中的分钟。例如，在 10:04:15.250 PM 这一时刻，MINUTE 为 4。 
	 *  SECOND:
	 *  get 和 set 的字段数字，指示一分钟中的秒。例如，在 10:04:15.250 PM 这一时刻，SECOND 为 15。 
	 *  MILLISECOND:
	 *  get 和 set 的字段数字，指示一秒中的毫秒。例如，在 10:04:15.250 PM 这一时刻，MILLISECOND 为 250。 
	 *  
	 *  
	 *  
	 *  DAY_OF_WEEK：
	 *  get 和 set 的字段数字，指示一个星期中的某天。
	 *  该字段可取的值为 SUNDAY、MONDAY、TUESDAY、WEDNESDAY、THURSDAY、FRIDAY 和 SATURDAY。 
	 *  DAY_OF_WEEK_IN_MONTH:
	 *  get 和 set 的字段数字，指示当前月中的第几个星期。与 DAY_OF_WEEK 字段一起使用时，就可以唯一地指定某月中的某一天。
	 *  与 WEEK_OF_MONTH 和 WEEK_OF_YEAR 不同，该字段的值并不 取决于 getFirstDayOfWeek() 或 getMinimalDaysInFirstWeek()。
	 *
	 *  AM_PM:
	 *  get 和 set 的字段数字，指示 HOUR 是在中午之前还是在中午之后。例如，在 10:04:15.250 PM 这一时刻，AM_PM 为 PM。 
	 *
	 *	ZONE_OFFSET：
	 *	get 和 set 的字段数字，以毫秒为单位指示距 GMT 的大致偏移量。 
	 *	如果 TimeZone 实现子类支持历史上用过的 GMT 偏移量更改，则此字段可反应此 Calendar 时区的正确 GMT 偏移量值。 
	 *
	 *	DST_OFFSET:
	 *	get 和 set 的字段数字，以毫秒为单位指示夏令时的偏移量。
	 */
	private static void fieldTest() {
		Calendar time=Calendar.getInstance();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		//输出当前时间
		System.out.println(sdf.format(time.getTime()));  //2015-12-11 18:49:53
		
		//指示当前年中已过的星期数。    2015年   注意：是已过的
		System.out.println(time.get(Calendar.WEEK_OF_YEAR));  //50
		
		//指示当前月中已过的星期数         12月11号
		System.out.println(time.get(Calendar.WEEK_OF_MONTH));  //2
		
		//指示当前年中已过的天数
		System.out.println(time.get(Calendar.DAY_OF_YEAR));   //345
		
		//指示一个星期中的某天
		System.out.println(time.get(Calendar.DAY_OF_WEEK));  //6对应Friday
		
		//指示当前月中的第几个星期
		System.out.println(time.get(Calendar.DAY_OF_WEEK_IN_MONTH)); //2
		
		//指示 HOUR 是在中午之前还是在中午之后             AM=0    PM=1
		System.out.println(time.get(Calendar.AM_PM));    //1
		
		//指示上午或下午的小时
		System.out.println(time.get(Calendar.HOUR));   //6
		
		//指示一天中的小时
		System.out.println(time.get(Calendar.HOUR_OF_DAY));//18
		
		//指示一小时中的分钟
		System.out.println(time.get(Calendar.MINUTE));  //49
		
		//指示一分钟中的秒
		System.out.println(time.get(Calendar.SECOND));  //53
		
		//指示一秒中的毫秒
		System.out.println(time.get(Calendar.MILLISECOND)); //644
		
		//以毫秒为单位指示距 GMT 的大致偏移量
		System.out.println(time.get(Calendar.ZONE_OFFSET)); //28800000
		
		//以毫秒为单位指示夏令时的偏移量
		System.out.println(time.get(Calendar.ZONE_OFFSET));  //28800000
	}
	/*
	 * 通过两个日历对象，求其间隔
	 */
	private static int getIntervalDays(Calendar startTime,Calendar endTime) {
		if(null==startTime&&null==endTime){
			throw new NullPointerException();
		}
		//boolean after(Object when)
		//判断此 Calendar 表示的时间是否在指定 Object 表示的时间之后，返回判断结果。
		if(startTime.after(endTime)){
			Calendar caTemp=startTime;
			startTime=endTime;
			endTime=caTemp;
		}
		long start=startTime.getTimeInMillis();
		long end=endTime.getTimeInMillis();
		return (int)((end-start)/(1000*60*60*24));
	}
	/*
	 * 初始化一个日历对象   Calendar是抽象类
	 * 可以使用下面三个方法把日历定到任何一个时间：
		set(int year ,int month,int date)
		set(int year ,int month,int date,int hour,int minute)
		set(int year ,int month,int date,int hour,int minute,int second)
	 *
	 * 如果想获得年份、月份、小时等信息可以使用：
	 *  int get(int field)  返回给定日历字段的值。
		get(Calendar.Month);这样的方法 0表示一月，1表示二月
		get(Calendar.DAY_OF_MONTH)获得这个月的第几天
		get(Calendar.DAY_OF_WEEK)获得这个星期的第几天
		get(Calendar.DAY_OF_YEAR)获得这个年的第几天
		getTimeMillis()获得当前时间的毫秒表示
	 *
	 */
	private static void calendarTest() {
		//1.计算某一月份的最大天数
		Calendar time=Calendar.getInstance();
		//注：在使用set方法之前，必须先clear一下，否则很多信息会继承自系统当前时间
		time.clear();//将此 Calendar 的所日历字段值和时间值（从历元至现在的毫秒偏移量）设置成未定义。
		//set方法重载，即可以一个个设置，也可以多个一起设置
		time.set(Calendar.YEAR, 2015);
		time.set(Calendar.MONTH, 12-1);
		int lastDay=time.getActualMaximum(Calendar.DAY_OF_MONTH);
		System.out.println(lastDay);   //31
		
		//2.Calendar和Date的转化
		//(1) Calendar转化为Date
		Date date=new Date();
		date=time.getTime();
		System.out.println(date); //Tue Dec 01 00:00:00 CST 2015
		//(2) Date转化为Calendar
		time.setTime(date);
		//将相对时间转换为Calendar
		time.setTimeInMillis(1449676800000L);
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		System.out.println(sdf.format(time.getTime()));  //2015-12-10
		
		
		//3.计算一年中的第几星期
		//(1)计算某一天是一年中的第几星期
		time.set(Calendar.DATE,10);
		int dow=time.get(Calendar.DAY_OF_WEEK);
		System.out.println(dow);  //5     按美国的星期的第一天是星期天，所以周四就对应5
		System.out.println(time.getFirstDayOfWeek());  //1--SUNDAY
		//用void setFirstDayOfWeek(int value)方法可以设置一星期的第一天是哪一天；
		time.setFirstDayOfWeek(Calendar.MONDAY);
		//dow=time.get(Calendar.DAY_OF_WEEK);
		System.out.println(time.getFirstDayOfWeek());  //2--MONDAY
		//(2)计算一年中的第几星期的星期几是几号
		time.clear();
		time.set(Calendar.YEAR, 2006);
		time.set(Calendar.WEEK_OF_YEAR,1);
		time.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		System.out.println(sdf.format(time.getTime()));  //2005-12-26
		
		/*
		 * 4.add()和roll()的用法(不太常用)
		 * abstract  void add(int field, int amount) 
		 * 根据日历的规则，为给定的日历字段添加或减去指定的时间量。
		 *  void roll(int field, int amount)  
		 *  向指定日历字段添加指定（有符号的）时间量，不更改更大的字段。负的时间量意味着向下滚动。 
		 */
		
		time.add(Calendar.DATE, 7);
		System.out.println(sdf.format(time.getTime())); //2006-01-02
		
		time.roll(Calendar.DATE, -1);
		System.out.println(sdf.format(time.getTime())); //2006-01-1
		time.roll(Calendar.DATE, -6);
		System.out.println(sdf.format(time.getTime())); //2006-01-26
		
		
		
	}
}
