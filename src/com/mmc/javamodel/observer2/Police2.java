package com.mmc.javamodel.observer2;

public class Police2 implements Observer {
	// ��������
	private String pname = "�����"; 

	@Override
	public void update(String message, String name) {
		// TODO Auto-generated method stub
		System.out.println(pname + "���棺" + name + "׼����������--" + message);
	}

}