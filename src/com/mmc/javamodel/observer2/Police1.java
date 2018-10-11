package com.mmc.javamodel.observer2;

public class Police1 implements Observer {
	// 定义姓名
	private String pname = "赵日天";
	
	@Override
	public void update(String message, String name) {
		// TODO Auto-generated method stub
		System.out.println(pname + "报告：" + name + "那边有新情况！--" + message);
	}

}
