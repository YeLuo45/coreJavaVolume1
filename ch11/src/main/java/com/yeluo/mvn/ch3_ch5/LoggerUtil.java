package com.yeluo.mvn.ch3_ch5;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 * 
 * @author YeLuo
 * @function  实现日志输出
 */
/*
 * 日志输出到登陆系统用户所在的根目录的Java日志的文件夹下，
 * 并且根据系统日期命名日志文件
 */
public class LoggerUtil {
	//存放的文件夹
	private static String fileName="Java日志";
	
	/**
	 * 得到要记录的日志的路径及文件名称
	 * @return 日志的文件名称
	 */
	private static String getLogName(){
		//日志文件路径
		StringBuffer logPath=new StringBuffer();
		logPath.append(System.getProperty("user.home"));
		logPath.append("\\"+fileName);
		//创建文件
		File file=new File(logPath.toString());
		if(!file.exists()){
			file.mkdir();
		}
		//文件名追加日期信息
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		logPath.append(sdf.format(new Date()));
		System.out.println("文件路径:"+logPath.toString());
		return logPath.toString();
	}
	
	 /**
     * 配置Logger对象输出日志文件路径
     * @param logger 
     * @throws SecurityException
     * @throws IOException
     */
    public static void setLoggingConfig(Logger logger) throws SecurityException, IOException {
    	setLoggingConfig(logger,Level.ALL);
    }
    
	/**
     * 配置Logger对象输出日志文件路径
     * @param logger
     * @param level 在日志文件中输出level级别以上的信息
     * @throws SecurityException
     * @throws IOException
     */
	private static void setLoggingConfig(Logger logger,Level level){
		FileHandler fh=null;
		
		try {
			fh=new  FileHandler(getLogName(), true);
			/*
			 *	void setFormatter(Formatter newFormatter) 
          		设置 Formatter。  
			 */
			fh.setFormatter(new SimpleFormatter());  //输出格式
			/*
			 *  void addHandler(Handler handler) 
          		添加一个日志 Handler 以接收日志记录消息。 
			 */
			logger.addHandler(fh);  //日志输出文件
			logger.setLevel(level);			
			//logger.addHandler(new ConsoleHandler()); //输出到控制台
		} catch (SecurityException e) {
			logger.log(Level.SEVERE, "安全性错误!",e);
		} catch (IOException e) {
			logger.log(Level.SEVERE, "读取日志文件错误!",e);
		}
	}
	public static void main(String[] args) {
		 Logger logger = Logger.getLogger("sgg");
		 /*
		  * 在默认情况下,日志记录器发送到ConsoleHandler中,并由它输出到System.err流中,特别是,日志记录器还会记录发送到父处理器中,
		  * 而最终的处理器有一个ConsoleHandler.
		  * 在默认情况下,日志记录器将记录发送到自己的处理器和父处理器.
		  */
		 /*
		  *  void setUseParentHandlers(boolean useParentHandlers) 
		  *          指定此 logger 是否应该将其输出发送到它的父 Logger。 
		  */
		 //设置为不发送到它的父类
		 //logger.setUseParentHandlers(false);
	        try {
	            LoggerUtil.setLoggingConfig(logger);
	            logger.log(Level.INFO, "ddddd");
	            logger.log(Level.INFO, "eeeeee");
	            logger.log(Level.INFO, "ffffff");
	            logger.log(Level.INFO, "gggggg");
	            logger.log(Level.INFO, "hhhhhh");
	            //获得与此 logger 相关的 Handler
	            Handler[] handlers=logger.getHandlers();
	            for(Handler handler:handlers){
	            	System.out.println(handler);
	            	//java.util.logging.FileHandler@330bedb4
	            	//java.util.logging.ConsoleHandler@2503dbd3
	            }
	        } catch (SecurityException e) {
	            
	            e.printStackTrace();
	        } catch (IOException e) {
	           
	            e.printStackTrace();
	        }
	        
	}
}
