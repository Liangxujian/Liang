package com.mmc.javamodel.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * ��̬������
 * �ڳ�������ʱ�����÷�����ƶ�̬��������
 */
public class DuckDynamicProxy implements InvocationHandler {
	Duck duck = null;
	
	public DuckDynamicProxy(Duck duck) {
		// TODO Auto-generated constructor stub
		this.duck = duck;
	}
	
	/**
	 * @param proxy ָ��������������Ǹ���ʵ����Duck.class.getClassLoader()��
	 * @param method ָ��������Ҫ������ʵ�����ĳ��������Method����new Class[] { Duck.class }��
	 * @param objs ָ��������ʵ����ĳ������ʱ���ܵĲ�����new DuckDynamicProxy(new BeijingDuck())��
	 */
	@Override
	public Object invoke(Object proxy, Method method, Object[] objs)
			throws Throwable {
		// TODO Auto-generated method stub
		Object obj = null;
		if ("fly".equals(method.getName())) {
			System.out.println("����");
			obj = method.invoke(duck, objs);
			System.out.println("����");
		} else if ("taste".equals(method.getName())) {
			System.out.println("����Ц��");
			obj = method.invoke(duck, objs);
			System.out.println("��ļٵģ�");
		}
		return obj;
	}
}
