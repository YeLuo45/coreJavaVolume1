package com.yeluo.mvn.ch2;

import java.text.DateFormatSymbols;
import java.util.Arrays;
import java.util.Locale;

/**
 * 
 * @author YeLuo
 * @function DateFormatSymbols
 */
/*
 * 1.DateFormatSymbols 是一个公共类，用于封装可本地化的日期-时间格式化数据，
 * 如月名、星期几的名称和时区数据。DateFormat 和 SimpleDateFormat 都使用 DateFormatSymbols 封装此信息。
 * 
 * 2.通常不应直接使用 DateFormatSymbols。
 * 建议最好使用 DateFormat 类的工厂方法创建日期-时间格式器 (formatter)：getTimeInstance、getDateInstance 或 getDateTimeInstance。
 * 这些方法自动为格式器创建一个 DateFormatSymbols，所以用户就不必再创建了。创建了格式器后，可使用 setPattern 方法修改它的格式模式。
 */
public class DateFormatSymbolsTest {
	public static void main(String[] args) {
		getWeekdaysTest();
		getMonthsTest();
	}
	/*
	 * 获得月份的简写和全写
	 * String[] getMonths()  
	 * String[] getShortMonths() 
	 */
	private static void getMonthsTest() {
		DateFormatSymbols symbols=new DateFormatSymbols();
		String[] months=symbols.getMonths();
		System.out.println(Arrays.toString(months));
		//[一月, 二月, 三月, 四月, 五月, 六月, 七月, 八月, 九月, 十月, 十一月, 十二月, ]
		String[] shortMonths=symbols.getShortMonths();
		System.out.println(Arrays.toString(shortMonths));
		//[一月, 二月, 三月, 四月, 五月, 六月, 七月, 八月, 九月, 十月, 十一月, 十二月, ]
		

		DateFormatSymbols symbols1=new DateFormatSymbols(Locale.US);
		String[] months1=symbols1.getMonths();
		System.out.println(Arrays.toString(months1));
		//[January, February, March, April, May, June, July, August, September, October, November, December, ]
		String[] shortMonths1=symbols1.getShortMonths();
		System.out.println(Arrays.toString(shortMonths1));
		//[Jan, Feb, Mar, Apr, May, Jun, Jul, Aug, Sep, Oct, Nov, Dec, ]
		
		
	}
	/*
	 * 获得星期的简写和全写
	 * String[] getWeekdays()  
	 * String[] getShortWeekdays()  
	 */
	private static void getWeekdaysTest() {
		DateFormatSymbols symbols=new DateFormatSymbols();
		String[] weekdays=symbols.getWeekdays();
		System.out.println(Arrays.toString(weekdays));
		//[, 星期日, 星期一, 星期二, 星期三, 星期四, 星期五, 星期六]
		String[] shortweekdays=symbols.getShortWeekdays();
		System.out.println(Arrays.toString(shortweekdays));
		//[, 星期日, 星期一, 星期二, 星期三, 星期四, 星期五, 星期六]
		

		DateFormatSymbols symbols1=new DateFormatSymbols(Locale.US);
		String[] weekdays1=symbols1.getWeekdays();
		System.out.println(Arrays.toString(weekdays1));
		//[, Sunday, Monday, Tuesday, Wednesday, Thursday, Friday, Saturday]
		String[] shortweekdays1=symbols1.getShortWeekdays();
		System.out.println(Arrays.toString(shortweekdays1));
		//[, Sun, Mon, Tue, Wed, Thu, Fri, Sat]
		
	}
}
