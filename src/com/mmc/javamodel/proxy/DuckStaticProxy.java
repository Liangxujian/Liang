package com.mmc.javamodel.proxy;

/**
 * ��̬������
 * ��̬���������⣬��չ�˹��ܣ����Ҳ�����Դ����
 * ���ǵ��ӿڴ��ڶ��ʵ����ʱ����Ҫһһ��д
 */
public class DuckStaticProxy implements Duck {
	Duck duck = null;
	
	public DuckStaticProxy(Duck duck) {
		// TODO Auto-generated constructor stub
		this.duck = duck;
	}
	
	@Override
	public void fly(int heigh) {
		// TODO Auto-generated method stub
		System.out.println("��ëϴ��");
		duck.fly(heigh);
		System.out.println("����Ե�");
	}

	@Override
	public void taste(String taste) {
		// TODO Auto-generated method stub
		System.out.println("����");
		duck.taste(taste);
		System.out.println("�Ե�");
	}
}
