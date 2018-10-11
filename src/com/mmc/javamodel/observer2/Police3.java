package com.mmc.javamodel.observer2;

public class Police3 implements Observer {
	// 定义姓名
	private String pname = "章泽天";

	@Override
	public void update(String message, String name) {
		// TODO Auto-generated method stub
		System.out.println(pname + "报告：" + name + "那边不知道该怎么说！--" + message);
	}

}
