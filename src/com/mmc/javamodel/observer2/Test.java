package com.mmc.javamodel.observer2;

/**
 * 观察者模式关键点：
 * 1.针对观察者与被观察者分别定义接口，有利于分别进行扩展
 * 2.定义观察者集合，并定义针对集合的添加、删除操作，用于增加、删除观察者；
 *   被观察者定义通知方法，用于将新情况通知给观察者
 * 3.观察者中需要有个接收被观察者通知的方法
 * 
 * 观察者模式定义的是一对多的依赖关系，
 * 		 一个被观察者可以拥有多个观察者，
 *  	并且通过接口对观察者与被观察者进行逻辑解耦，降低二者的直接耦合
 */
public class Test {
	public static void main(String[] args) {
		// 定义坏人
		Badman1 b1 = new Badman1();
		Badman2 b2 = new Badman2();
		// 定义警察
		Police1 p1 = new Police1();
		Police2 p2 = new Police2();
		Police3 p3 = new Police3();
		// 为坏人指定观察警察
		b1.addObserver(p1);
		
		b2.addObserver(p1);
		b2.addObserver(p2);
		b2.addObserver(p3);
		
		// 定义坏人信息
		b1.notice("要打劫银行啦！！");
		b2.notice("煎饼果子来一套！");
		System.out.println();
		
		b1.addObserver(p3);
		b2.removeObserver(p1);
		b1.notice("又要打劫银行！！");
		b2.notice("煎饼果子来两套！");
	}
}
