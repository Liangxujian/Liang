package com.mmc.javamodel.proxy;

/**
 * ʵ����
 */
public class BeijingDuck implements Duck {
	@Override
	public void fly(int heigh) {
		// TODO Auto-generated method stub
		System.out.println("������Ѽ���ܷ�" + heigh + "�׸ߣ���");
	}

	@Override
	public void taste(String taste) {
		// TODO Auto-generated method stub
		System.out.println("������Ѽ��������" + taste + "����");
	}
}
