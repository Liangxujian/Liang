package com.mmc.test.lin.anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/*
 * @Retention(RetentionPolicy.RUNTIME):指示注释类型的注释要保留多久
 * 	RetentionPolicy.CLASS：编译器将把注释记录在类文件中，但在运行时 VM 不需要保留注释。这是默认的行为
 * 	RetentionPolicy.RUNTIME：编译器将把注释记录在类文件中，在运行时 VM 将保留注释，因此可以反射性地读取
 * 	RetentionPolicy.SOURCE：编译器要丢弃的注释
 */
@Retention(RetentionPolicy.RUNTIME)
/*
 * @Target(ElementType.TYPE) 指示注释类型所适用的程序元素的种类
 * 	ElementType.ANNOTATION_TYPE：注释类型声明
 * 	ElementType.CONSTRUCTOR：构造方法声明
 * 	ElementType.FIELD：字段声明（包括枚举常量）
 * 	ElementType.LOCAL_VARIABLE：局部变量声明
 * 	ElementType.METHOD：方法声明
 * 	ElementType.PACKAGE：包声明
 * 	ElementType.PARAMETER：参数声明
 * 	ElementType.TYPE：类、接口（包括注释类型）或枚举声明
 */
@Target(ElementType.TYPE)
/**
 * 
 * @author Administrator
 *
 */
public @interface Singleton {
	boolean value() default true;
	String name() default "王小明";
}
