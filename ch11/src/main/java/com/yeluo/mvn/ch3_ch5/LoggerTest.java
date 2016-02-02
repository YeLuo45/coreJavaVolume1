package com.yeluo.mvn.ch3_ch5;

import java.io.IOException;
import java.util.logging.Filter;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 
 * @author YeLuo
 * @function
 */
/* 记录日志
 * 1.记录日志API的优点
 * 		1.可以很容易地取消全部日志记录,或者仅仅取消某个级别的日志,而且打开和关闭这个操作也很容易.
 * 		2.可以很简单地禁止日志记录的输出,因此,将这些日志代码留在程序中的开销很小.
 * 		3.日志记录可以被定向到不同的处理器,用于在控制台中显示,用于存储在文件中等.
 * 		4.日记记录器和处理器可以对记录进行过滤.过滤器可以根据过滤实现器制定的标准丢弃那些无用的记录项.
 * 		5.日志记录可以采取不同的方式格式化,例如,纯文本或XML.
 * 		6.应用程序可以使用多个日志记录器,它们使用类似包名的这种具有层次结构的名字,例如,com.mycompany.myapp
 * 		7.在默认情况下,日志系统的配置由配置文件控制.如果需要的话,应用程序可以替换这个配置.
 * 2.日志记录方法划分为 5 个主要类别： 
		1.一系列的 "log" 方法，这种方法带有日志级别、消息字符串，以及可选的一些消息字符串参数。 
		2.一系列的 "logp" 方法（即 "log precise"），其与 "log" 方法相似，但是带有显式的源类名称和方法名称。 
		3.一系列的 "logrb" 方法（即 "log with resource bundle"），其与 "logp" 方法相似，
		但是带有显式的在本地化日志消息中使用的资源包名称。 
		4.还有跟踪方法条目（"entering" 方法）、方法返回（"exiting" 方法）和抛出异常（"throwing" 方法）的便捷方法。 
		5.最后，还有一系列在非常简单的情况下（如开发人员只想为给定的日志级别记录一条简单的字符串）使用的便捷方法。
		这些方法按标准级别名称命名（"severe"、"warning"、"info" 等等)，并带有单个参数，即一个消息字符串。

  * 3.对于不带显式源名和方法名的方法，日志记录框架将尽可能确定日志记录方法中调用了哪个类和方法。
	但是应认识到，这样自动推断的信息可能只是近似的，甚至可能是完全错误的。
	这是因为允许虚拟机在 JIT 编译时可以进行广泛的优化，并且可以完全移除栈帧，导致它无法可靠地找到调用的类和方法。 
	
  * 4.Logger 上执行的所有方法都是多线程安全的。
  * 
  * 5.事实上,与包名相比,日志记录器的层次性更强.
  * 对于包名来说,一个包的名字与其父包的名字之间没有语义关系,
  * 但是日志记录器的父与子之间将共享某些属性.
  * 例如,如果对com.mycompany日志 记录器设置了日志级别,它的子记录器也会继承这个级别.
  * 通常,有以下7个日志记录器级别:1.SEVERE   2.WARNING  3.INFO   4.CONFIG    5.FINE  6.FINER   7.FINEST
  * 在默认情况下,只记录前三个级别.也可以设置其他的级别.
  * 另外,使用Level.ALL开启所有级别的记录,使用Level.OFF关闭所有级别的记录.
  * 
  * 6.记录日志的常见用途是记录那些不可预料的异常.
  * 
  * 7.可以通过编译配置文件来修改日志系统的各种属性.在默认情况下,配置文件存在于:jre/lib/logging.properties
  * 要想使用另一个配置文件,就要将java.util.logging.config.file特性设置为配置文件的存储位置,并用下列命令启动应用程序:
  * java -Djava.util.logging.config.file=configFile MainClass
  *	警告1:在main方法中调用System.setProperty("java.util.logging.config.file",file) 没有任何影响,
  *其原因时日志管理器在VM启动过程中被初始化,它会在main之前执行.
  *	警告2:在日志管理器配置的属性设置不是系统属性,因此,用-Dcom.mycompany.myapp.level=FINE启动应用程序不会对日志记录
  *产生任何影响.
  *	警告3:截止到Java SE 7.0,Logmanager类的API文档主张通过Preferences API设置java.util.logging.config.class
  *和java.util.logging.config.file属性.这是不正确的.  
  *
  * 8.本地化:本地化的应用程序包含资源包中的本地特定信息.资源包是由各个地区的映射集合组成的.
  * 
  * 9.处理器:在默认情况下,日志记录器发送到ConsoleHandler中,并由它输出到System.err流中,特别是,日志记录器还会记录发送到父处理器中,
  * 而最终的处理器有一个ConsoleHandler.
  * 
  * 10.与日志记录器一样,处理器也有日志记录级别.对于一个要被记录的日志记录,它的日志记录级别高于日志记录器和处理器的阈值.
  * 在默认情况下,日志记录器将记录发送到自己的处理器和父处理器.
  * 
  * 11.logging.properties文件中的
  * handlers= java.util.logging.ConsoleHandler  将日志内容输出到控制台
  * handlers= java.util.logging.FileHandler     将日志文件输出到文件中
  * handlers= java.util.logging.ConsoleHandler,java.util.logging.FileHandler 将日志内容同时输出到控制台和文件中
  * # Limit the message that are printed on the console to INFO and above.
  * java.util.logging.ConsoleHandler.level = INFO   //日志输出级别
  * java.util.logging.FileHandler.pattern = %h/java%u.log  为生成的输出文件名称指定一个模式。
  * 模式由包括以下特殊组件的字符串组成，则运行时要替换这些组件：
  * "/" 本地路径名分隔符
  * "%t" 系统临时目录
  * "%h" "user.home" 系统属性的值
  * "%g" 区分循环日志的生成号
  * "%u" 解决冲突的惟一号码
  * "%%" 转换为单个百分数符号"%"  
  * 如果未指定 "%g" 字段，并且文件计数大于 1，那么生成号将被添加到所生成文件名末尾的小数点后面。 
  * java.util.logging.FileHandler.limit = 50000   限制文件的大小，以字节为单位(0表示无限制)
  * java.util.logging.FileHandler.count = 1       指定有多少输出文件参与循环（默认为 1--不循环）。
  * java.util.logging.FileHandler.formatter = java.util.logging.XMLFormatter      指定要使用的 Formatter 类的名称（默认为 java.util.logging.XMLFormatter）。 另外一个是：java.util.logging.SimpleFormatter。XMLFormatter是以xml样式输出，SimpleFormatter是以普通样式输出。
  * java.util.logging.FileHandler.append 指定是否应该将 FileHandler 追加到任何现有文件上（默认为 false）。
  * java.util.logging.FileHandler.encoding  使用的字符编码(默认是平台的编码)
  * java.util.logging.FileHandler.level   默认是Level.ALLfs
  * 
  *  12.如果希望编写更加复杂的流处理器,就应该扩展Handler类,并自定义publish、flush和close方法。
  *  
  *  13.过滤器:在默认情况下,过滤器根据日志记录的级别进行过滤,每个日志记录器和处理器都可以有一个可选的过滤来完成附加的过滤.
  *  另外,可以通过实现Filter接口并定义下列方法来自定义过滤器.
  *  要想将一个过滤器安装到一个日志记录器或处理器中,只需要调用setFilter方法就可以了.
  *  注意,同一时刻最多只能有一个过滤器.
  *  
  *  14.格式化器:ConsoleHandler类和FileHandler类可以生成文本和XML格式的日志记录.但是,也可以自定义格式.这需要扩展Formatter
  *  类并覆盖下面这个方法:
  *  String format(LogRecord record).
  *  
  *  15.日志记录说明
  *  		1.为一个简单的应用程序,选择一个日志记录器,并把日志记录器命名为与主应用程序包一样的名字.
  *  		2.默认的日志配置将级别等于或高于INFO级别的所有消息记录到控制台.用户可以覆盖默认的配置文件.
  *  但是正如前面所述,改变配置需要做相当多的工作.因此,最好在应用程序中安装一个更加适宜的默认配置.
  *  		3.现在,可以记录自己想要的内容了.但是需要牢记:所有级别为INFO、WARNING和SEVERE的消息都将显示到控制台上.
  *  因此,最好只将对程序用户有意义的消息设置为这几个级别.将程序员想要的日志记录,设定为FINE是一个很好地选择.
  *  
  *  16.本章节相关的类
  *  java.util.logging.Logger  、   java.util.logging.Handler  、   java.util.logging.ConsoleHandler
  *  java.util.logging.FileHandler  、  java.util.logging.LogRecord  、 java.util.logging.Filter
  *  java.util.logging.Formatter
  *  
  */
public class LoggerTest {
	public static void main(String[] args) {
		simpleExampleTest();
		logTest();
		enterTest();
	}

	/*
	 *  void entering(String sourceClass, String sourceMethod) 
          	记录一个方法条目。 
 		void entering(String sourceClass, String sourceMethod, Object param1) 
          	记录一个方法条目，带有一个参数。 
 		void entering(String sourceClass, String sourceMethod, Object[] params) 
          	记录一个方法条目，带有一组参数。 
 		void exiting(String sourceClass, String sourceMethod) 
          	记录一个方法返回。 
 		void exiting(String sourceClass, String sourceMethod, Object result) 
          	记录一个方法返回，带有结果对象。 
	 *	这些调用将生成FINER级别和以字符串"ENTRY"和"RETURN"开始的日志记录.
	 */
	private static void enterTest() {
		Logger logger=Logger.getLogger("log.txt");
		
		 try {
			LoggerUtil.setLoggingConfig(logger);
			logger.entering("LoggerUtil", "main");
			logger.exiting("LoggerUtil", "main");
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/*在文件路径:C:\Users\dell-1\Java日志2015-12-24中的文件内容
		 *  十二月 24, 2015 3:53:05 下午 LoggerUtil main
			较详细: ENTRY
			十二月 24, 2015 3:53:05 下午 LoggerUtil main
			较详细: RETURN
		 */
	}

	/*
	 * 简单例子
	 * public static final Logger getGlobal()
	 * 		Return global logger object with the name Logger.GLOBAL_LOGGER_NAME.
	 * 		since 1.7
	 * void info(String msg) 记录一条 INFO 消息。
	 * 注意:自动包含了时间,调用的类名和方法名. 
	 * void setLevel(Level newLevel) 
	 *      设置日志级别，指定此 logger 记录的消息级别。
	 * Filter getFilter() 
	 *       获取此 Logger 的当前过滤器。  
	 * Level getLevel() 
 	 *       获取已为此 Logger 指定的日志级别（Level）。       
	 */
	private static void simpleExampleTest() {
		Logger logger=Logger.getGlobal();
		logger.info("File-->Open menu item selected");
		/* 输出:
		 * 十二月 24, 2015 11:25:55 上午 com.yeluo.mvn.ch3.LoggerTest logTest
		 * 信息: File-->Open menu item selected
		 * 
		 */
		//取消所有日志
		logger.setLevel(Level.OFF);
		logger.info("File-->Open menu item selected"); //没有输出
		//获取此 Logger 的当前过滤器
		Filter filter=logger.getFilter();
		System.out.println(filter);		//null
		//获取已为此 Logger 指定的日志级别
		Level level=logger.getLevel();
		System.out.println(level);		//OFF
		
	}

	/*
	 *  static Logger getLogger(String name) 
          	为指定子系统查找或创建一个 logger。 
		static Logger getLogger(String name, String resourceBundleName) 
          	为指定子系统查找或创建一个 logger。 

	 *  void log(Level level, String msg) 
          	记录一条不带参数的消息。 
 		void log(Level level, String msg, Object param1) 
          	记录带有一个对象参数的消息。 
 		void log(Level level, String msg, Object[] params) 
          	记录带有一组对象参数的消息。 
 		void log(Level level, String msg, Throwable thrown) 
          	记录带有相关的可抛出信息的消息。 
 		void log(LogRecord record) 
          	记录一条 LogRecord。 
	 */
	private static void logTest() {
		Logger logger=Logger.getLogger("sgg");
		logger.log(Level.INFO,"KO");
		/*
		 * 十二月 24, 2015 12:28:51 下午 com.yeluo.mvn.ch3.LoggerTest logTest
		 * 信息: KO
		 */
		
	}
}
