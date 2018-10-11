package com.mmc.javamodel.observer2;

public class Police2 implements Observer {
	// 定义姓名
	private String pname = "张昊天"; 

	@Override
	public void update(String message, String name) {
		// TODO Auto-generated method stub
		System.out.println(pname + "报告：" + name + "准备出击啦！--" + message);
	}

}
