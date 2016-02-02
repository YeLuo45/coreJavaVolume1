package com.yeluo.mvn.annotations;
/**
 * 
 * @author YeLuo
 * @function
 */
/**
 * @NotThreadSafe是可选的--如果没有被标明是线程安全的,
 * 就无法肯定它是是不是线程安全的,但是如果你想明确地表示出它不是线程安全的,
 * 就标注为 @NotThreadSafe
 */
public @interface NotThreadSafe {

}
