package com.yeluo.mvn.ch6_ch7;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * 
 * @author YeLuo
 * @function
 */
/*
 * 1.Class 类的实例表示正在运行的 Java 应用程序中的类和接口。枚举是一种类，注释是一种接口。
 * 每个数组属于被映射为 Class 对象的一个类，所有具有相同元素类型和维数的数组都共享该 Class 对象。
 * 基本的 Java 类型（boolean、byte、char、short、int、long、float 和 double）和关键字 void 也表示为 Class 对象。
 * 
 * 2.Class 没有公共构造方法。Class 对象是在加载类时由 Java 虚拟机以及通过调用类加载器中的 defineClass 方法自动构造的。
 * 
 */
public class ClassTest {
	public static void main(String[] args) {
		/*getClassTest();
		annotationTest();
		methodTest();
		constructorTest();
		fieldTest();*/
		modifierTest();
		judgeMethodTest();
		commonMethodTest();
	}
	/*
	 * 常用方法
	 * 1. T newInstance() 
          	创建此 Class 对象所表示的类的一个新实例。 
	 *
	 * 2. String getName() 
          	以 String 的形式返回此 Class 对象所表示的实体（类、接口、数组类、基本类型或 void）名称。 
	 *
	 * 3.String getSimpleName() 
          	返回源代码中给出的底层类的简称。 
	 * 
	 */
	private static void commonMethodTest() {
		String s="OK";
		Class clazz=s.getClass();
		String s1;
		try {
			s1 = (String) clazz.newInstance();
			System.out.println(s1);
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		
		System.out.println(clazz.getName());  //java.lang.String		
		System.out.println(clazz.getSimpleName()); //String
	}
	/*
	 *  boolean isAnnotation() 
          	如果此 Class 对象表示一个注释类型则返回 true。 
 		boolean isAnnotationPresent(Class<? extends Annotation> annotationClass) 
          	如果指定类型的注释存在于此元素上，则返回 true，否则返回 false。 
 		boolean isAnonymousClass() 
          	当且仅当底层类是匿名类时返回 true。 
 		boolean isArray() 
          	判定此 Class 对象是否表示一个数组类。 
 		boolean isAssignableFrom(Class<?> cls) 
          	判定此 Class 对象所表示的类或接口与指定的 Class 参数所表示的类或接口是否相同，或是否是其超类或超接口。 
 		boolean isEnum() 
          	当且仅当该类声明为源代码中的枚举时返回 true。 
 		boolean isInstance(Object obj) 
          	判定指定的 Object 是否与此 Class 所表示的对象赋值兼容。 
 		boolean isInterface() 
          	判定指定的 Class 对象是否表示一个接口类型。 
 		boolean isLocalClass() 
          	当且仅当底层类是本地类时返回 true。 
 		boolean isMemberClass() 
          	当且仅当底层类是成员类时返回 true。 
 		boolean isPrimitive() 
          	判定指定的 Class 对象是否表示一个基本类型。 
 		boolean isSynthetic() 
          	如果此类是复合类，则返回 true，否则 false。 

	 */
	private static void judgeMethodTest() {
		
		
	}
	/*
	 *  int getModifiers() 
          	返回此类或接口以整数编码的 Java 语言修饰符。 
	 */
	private static void modifierTest() {
		String s="OK";
		Class clazz=s.getClass();
		int modifier=clazz.getModifiers();
		System.out.println(modifier);  //17
	}
	/*
	 *  Field getDeclaredField(String name) 
          	返回一个 Field 对象，该对象反映此 Class 对象所表示的类或接口的指定已声明字段。 
 		Field[] getDeclaredFields() 
          	返回 Field 对象的一个数组，这些对象反映此 Class 对象所表示的类或接口所声明的所有字段。 
 		Field getField(String name) 
          	返回一个 Field 对象，它反映此 Class 对象所表示的类或接口的指定公共成员字段。 
 		Field[] getFields() 
          	返回一个包含某些 Field 对象的数组，这些对象反映此 Class 对象所表示的类或接口的所有可访问公共字段。 

	 */
	private static void fieldTest() {
		String s="OK";
		Class clazz=s.getClass();
		Field[] fields=clazz.getFields();
		Field[] fieldsd=clazz.getDeclaredFields();
		System.out.println(Arrays.toString(fields));
		/*
		 *[public static final java.util.Comparator java.lang.String.CASE_INSENSITIVE_ORDER] 
		 */
		for(Field f:fieldsd){
			System.out.println(f);
		}
		/*
		 * 	private final char[] java.lang.String.value
			private int java.lang.String.hash
			private static final long java.lang.String.serialVersionUID
			private static final java.io.ObjectStreamField[] java.lang.String.serialPersistentFields
			public static final java.util.Comparator java.lang.String.CASE_INSENSITIVE_ORDER

		 */
	}
	/*
	 *  Constructor<T> getConstructor(Class<?>... parameterTypes) 
          	返回一个 Constructor 对象，它反映此 Class 对象所表示的类的指定公共构造方法。 
 		Constructor<?>[] getConstructors() 
          	返回一个包含某些 Constructor 对象的数组，这些对象反映此 Class 对象所表示的类的所有公共构造方法。 

	 */
	private static void constructorTest() {
		String s="OK";
		Class clazz=s.getClass();
		try {
			Constructor constructor=clazz.getConstructor(String.class);
			System.out.println(constructor);   //public java.lang.String(java.lang.String)
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		}
		
		Constructor[] constructors=clazz.getConstructors();
		System.out.println(Arrays.toString(constructors));
		/*
		 * [public java.lang.String(byte[],int,int),
		 *  public java.lang.String(byte[],java.nio.charset.Charset),
		 *   public java.lang.String(byte[],java.lang.String) throws java.io.UnsupportedEncodingException, 
		 *   public java.lang.String(byte[],int,int,java.nio.charset.Charset),
		 *    public java.lang.String(byte[],int,int,java.lang.String) throws java.io.UnsupportedEncodingException,
		 *     public java.lang.String(java.lang.StringBuilder), 
		 *     public java.lang.String(java.lang.StringBuffer), 
		 *     public java.lang.String(byte[]), 
		 *     public java.lang.String(int[],int,int), 
		 *     public java.lang.String(), 
		 *     public java.lang.String(char[]), 
		 *     public java.lang.String(java.lang.String), 
		 *     public java.lang.String(char[],int,int), 
		 *     public java.lang.String(byte[],int), 
		 *     public java.lang.String(byte[],int,int,int)]
		 */
	}
	/*
	 *  <A extends Annotation> A getAnnotation(Class<A> annotationClass) 
          	如果存在该元素的指定类型的注释，则返回这些注释，否则返回 null。 
 		Annotation[] getAnnotations() 
          	返回此元素上存在的所有注释。 
		 Annotation[] getDeclaredAnnotations() 
          	返回直接存在于此元素上的所有注释。 
	 */
	private static void annotationTest() {
		String s="OK";
		Class clazz=s.getClass();
		Annotation[] annotation=clazz.getAnnotations();
		System.out.println(annotation.length);
		System.out.println(Arrays.toString(annotation));
	}
	/*
	 *  Method getDeclaredMethod(String name, Class<?>... parameterTypes) 
          	返回一个 Method 对象，该对象反映此 Class 对象所表示的类或接口的指定已声明方法。 
 		Method[] getDeclaredMethods() 
          	返回 Method 对象的一个数组，这些对象反映此 Class 对象表示的类或接口声明的所有方法，包括公共、保护、默认（包）访问和私有方法，但不包括继承的方法。 
 		Method getEnclosingMethod() 
          	如果此 Class 对象表示某一方法中的一个本地或匿名类，则返回 Method 对象，它表示底层类的立即封闭方法。 
        Method getMethod(String name, Class<?>... parameterTypes) 
          	返回一个 Method 对象，它反映此 Class 对象所表示的类或接口的指定公共成员方法。 
 		Method[] getMethods() 
          	返回一个包含某些 Method 对象的数组，这些对象反映此 Class 对象所表示的类或接口（包括那些由该类或接口声明的以及从超类和超接口继承的那些的类或接口）的公共 member 方法。 

	 */
	private static void methodTest() {
		String s="OK";
		Class clazz=s.getClass();
		Method[] method=clazz.getDeclaredMethods();
		System.out.println(Arrays.toString(method));
		/*
		 * [public boolean java.lang.String.equals(java.lang.Object),
		 *  public java.lang.String java.lang.String.toString(),
		 *   public int java.lang.String.hashCode(), 
		 *   public int java.lang.String.compareTo(java.lang.String), 
		 *   public int java.lang.String.compareTo(java.lang.Object), 
		 *   public int java.lang.String.indexOf(java.lang.String,int), 
		 *   public int java.lang.String.indexOf(java.lang.String), 
		 *   public int java.lang.String.indexOf(int,int), 
		 *   public int java.lang.String.indexOf(int), 
		 *   static int java.lang.String.indexOf(char[],int,int,char[],int,int,int), 
		 *   static int java.lang.String.indexOf(char[],int,int,java.lang.String,int), 
		 *   public static java.lang.String java.lang.String.valueOf(int), 
		 *   public static java.lang.String java.lang.String.valueOf(long), 
		 *   public static java.lang.String java.lang.String.valueOf(float), 
		 *   public static java.lang.String java.lang.String.valueOf(boolean), 
		 *   public static java.lang.String java.lang.String.valueOf(char[]), 
		 *   public static java.lang.String java.lang.String.valueOf(char[],int,int), 
		 *   public static java.lang.String java.lang.String.valueOf(java.lang.Object), 
		 *   public static java.lang.String java.lang.String.valueOf(char), 
		 *   public static java.lang.String java.lang.String.valueOf(double), 
		 *   public char java.lang.String.charAt(int), 
		 *   private static void java.lang.String.checkBounds(byte[],int,int), 
		 *   public int java.lang.String.codePointAt(int), 
		 *   public int java.lang.String.codePointBefore(int), 
		 *   public int java.lang.String.codePointCount(int,int), 
		 *   public int java.lang.String.compareToIgnoreCase(java.lang.String), 
		 *   public java.lang.String java.lang.String.concat(java.lang.String), 
		 *   public boolean java.lang.String.contains(java.lang.CharSequence), 
		 *   public boolean java.lang.String.contentEquals(java.lang.CharSequence), 
		 *   public boolean java.lang.String.contentEquals(java.lang.StringBuffer), 
		 *   public static java.lang.String java.lang.String.copyValueOf(char[]), 
		 *   public static java.lang.String java.lang.String.copyValueOf(char[],int,int), 
		 *   public boolean java.lang.String.endsWith(java.lang.String), 
		 *   public boolean java.lang.String.equalsIgnoreCase(java.lang.String), 
		 *   public static java.lang.String java.lang.String.format(java.util.Locale,java.lang.String,java.lang.Object[]), 
		 *   public static java.lang.String java.lang.String.format(java.lang.String,java.lang.Object[]), 
		 *   public void java.lang.String.getBytes(int,int,byte[],int), 
		 *   public byte[] java.lang.String.getBytes(java.nio.charset.Charset), 
		 *   public byte[] java.lang.String.getBytes(java.lang.String) throws java.io.UnsupportedEncodingException, public byte[] java.lang.String.getBytes(), 
		 *   public void java.lang.String.getChars(int,int,char[],int), 
		 *   void java.lang.String.getChars(char[],int), 
		 *   private int java.lang.String.indexOfSupplementary(int,int), 
		 *   public native java.lang.String java.lang.String.intern(), 
		 *   public boolean java.lang.String.isEmpty(), 
		 *   public static java.lang.String java.lang.String.join(java.lang.CharSequence,java.lang.CharSequence[]), public static java.lang.String java.lang.String.join(java.lang.CharSequence,java.lang.Iterable), 
		 *   public int java.lang.String.lastIndexOf(int), 
		 *   public int java.lang.String.lastIndexOf(java.lang.String), 
		 *   static int java.lang.String.lastIndexOf(char[],int,int,java.lang.String,int), 
		 *   public int java.lang.String.lastIndexOf(java.lang.String,int), 
		 *   public int java.lang.String.lastIndexOf(int,int), 
		 *   static int java.lang.String.lastIndexOf(char[],int,int,char[],int,int,int), 
		 *   private int java.lang.String.lastIndexOfSupplementary(int,int), 
		 *   public int java.lang.String.length(), 
		 *   public boolean java.lang.String.matches(java.lang.String), 
		 *   private boolean java.lang.String.nonSyncContentEquals(java.lang.AbstractStringBuilder), 
		 *   public int java.lang.String.offsetByCodePoints(int,int), 
		 *   public boolean java.lang.String.regionMatches(int,java.lang.String,int,int), 
		 *   public boolean java.lang.String.regionMatches(boolean,int,java.lang.String,int,int), 
		 *   public java.lang.String java.lang.String.replace(char,char), 
		 *   public java.lang.String java.lang.String.replace(java.lang.CharSequence,java.lang.CharSequence), public java.lang.String java.lang.String.replaceAll(java.lang.String,java.lang.String), 
		 *   public java.lang.String java.lang.String.replaceFirst(java.lang.String,java.lang.String), 
		 *   public java.lang.String[] java.lang.String.split(java.lang.String), 
		 *   public java.lang.String[] java.lang.String.split(java.lang.String,int), 
		 *   public boolean java.lang.String.startsWith(java.lang.String,int), 
		 *   public boolean java.lang.String.startsWith(java.lang.String), 
		 *   public java.lang.CharSequence java.lang.String.subSequence(int,int), 
		 *   public java.lang.String java.lang.String.substring(int), 
		 *   public java.lang.String java.lang.String.substring(int,int), 
		 *   public char[] java.lang.String.toCharArray(), 
		 *   public java.lang.String java.lang.String.toLowerCase(java.util.Locale), 
		 *   public java.lang.String java.lang.String.toLowerCase(), 
		 *   public java.lang.String java.lang.String.toUpperCase(), 
		 *   public java.lang.String java.lang.String.toUpperCase(java.util.Locale), 
		 *   public java.lang.String java.lang.String.trim()]
		 */
		
	}
	/*
	 * 获得class对象
	 * 1.Object类 
	 * public final native Class<?> getClass();
	 * 2.Class类
	 *  static Class<?> forName(String className) 
          	返回与带有给定字符串名的类或接口相关联的 Class 对象。 
		static Class<?> forName(String name, boolean initialize, ClassLoader loader) 
          	使用给定的类加载器，返回与带有给定字符串名的类或接 
          	
	 * 	Class<?>[] getClasses() 
          	返回一个包含某些 Class 对象的数组，这些对象表示属于此 Class 对象所表示的类的成员的所有公共类和接口。 
        Class<?>[] getDeclaredClasses() 
          	返回 Class 对象的一个数组，这些对象反映声明为此 Class 对象所表示的类的成员的所有类和接口。 
          	
     *   ClassLoader getClassLoader() 
          	返回该类的类加载器。   
          	
     *   Class<?>[] getInterfaces() 
          	确定此对象所表示的类或接口实现的接口。 
          	
     *   Class<?> getComponentType() 
          	返回表示数组组件类型的 Class。 
	 */
	private static void getClassTest() {
		//1.通过Object类的getClass方法 (通过对象获得)
		String s="OK";
		Class clazz1=s.getClass();
		System.out.println(clazz1);    //class java.lang.String
		//2.通过全限定名   方法：Class类的forName方法
		try {
			Class clazz2=Class.forName("java.lang.String");
			System.out.println(clazz2); //class java.lang.String
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		//3.通过类型加 ".class"
		Class clazz3=String.class;
		System.out.println(clazz3); //class java.lang.String	
		
		//类加载器
		ClassLoader classLoader=clazz1.getClassLoader();
		System.out.println(classLoader);     //null
	}
}
