package com.yeluo.mvn.ch2;

import java.util.Arrays;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * 
 * @author YeLuo
 * @function 测试TimeZone的功能
 */
/**
 * 1.TimeZone 表示时区偏移量，也可以计算夏令时。 
 * TimeZone主要是解决跨时区的时间问题，
 * 
 * 2.通常，使用 getDefault 获取 TimeZone，getDefault 基于程序运行所在的时区创建 TimeZone。
 * 例如，对于在日本运行的程序，getDefault 基于日本标准时间创建 TimeZone 对象。 
 * 也可以用 getTimeZone 及时区 ID 获取 TimeZone 。
 * 例如美国太平洋时区的时区 ID 是 "America/Los_Angeles"。
 * 因此，可以使用下面语句获得美国太平洋时间 TimeZone 对象： 
 * TimeZone tz = TimeZone.getTimeZone("America/Los_Angeles");
 * 
 * 
 */
public class TimeZoneTest {
	public static void main(String[] args) {
		//test();
		methodTest();
	}

	/**
	 * static String[] getAvailableIDs() 
	 * 获取受支持的所有可用 ID。
	 * 
	 * String getDisplayName()  
	 * 返回适合于展示给默认区域的用户的时区名称。
	 * 
	 * String getDisplayName(Locale locale)  
	 * 返回适合于展示给指定区域的用户的时区名称。
	 * 
	 * String getID() 
	 * 获取此时区的 ID。
	 * 
	 *  int getOffset(long date) 
	 *  从给定日期的 UTC 返回此时区的偏移量。
	 *  
	 *  public abstract boolean useDaylightTime()
	 *  查询此时区是否使用夏令时。
	 *  
	 */
	private static void methodTest() {
		//获取受支持的所有可用 ID。
		String[] ids=TimeZone.getAvailableIDs();
		System.out.println(Arrays.toString(ids));
		
		//回适合于展示给默认区域的用户的时区名称。
		TimeZone timeZone=TimeZone.getDefault();
		String name=timeZone.getDisplayName();
		System.out.println(name);    //中国标准时间
		
		//返回适合于展示给指定区域的用户的时区名称。
		Locale locale=Locale.US;
		String name1=timeZone.getDisplayName(locale);
		System.out.println(name1); //China Standard Time
		
		//获取此时区的 ID。
		String id=timeZone.getID();
		System.out.println(id);   //Asia/Shanghai
		
		//从给定日期的 UTC 返回此时区的偏移量。
		int offSet=timeZone.getOffset((new Date()).getTime());
		System.out.println(offSet);    //28800000
		
		//查询此时区是否使用夏令时。
		boolean result=timeZone.useDaylightTime();
		System.out.println(result);   //false
	}

	private static void test() {
		//获取 TimeZone，getDefault 基于程序运行所在的时区创建 TimeZone。
		TimeZone timeZone=TimeZone.getDefault();
		System.out.println(timeZone);
		//sun.util.calendar.ZoneInfo[id="Asia/Shanghai",offset=28800000,dstSavings=0,
		//useDaylight=false,transitions=19,lastRule=null]
		
		//美国太平洋时区的时区 ID 是 "America/Los_Angeles获取TimeZone
		TimeZone tz = TimeZone.getTimeZone("America/Los_Angeles");
		System.out.println(tz);//sun.util.calendar.ZoneInfo[id="America/Los_Angeles",offset=-28800000,
		//dstSavings=3600000,useDaylight=true,transitions=185,lastRule=java.util.SimpleTimeZone
		//[id=America/Los_Angeles,offset=-28800000,dstSavings=3600000,useDaylight=true,startYear=0,startMode=3,startMonth=2,startDay=8,startDayOfWeek=1,startTime=7200000,startTimeMode=0,endMode=3,endMonth=10,endDay=1,endDayOfWeek=1,endTime=7200000,endTimeMode=0]]

		
	}
}
