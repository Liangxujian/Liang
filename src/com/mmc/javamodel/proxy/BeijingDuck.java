package com.mmc.javamodel.proxy;

/**
 * 实现类
 */
public class BeijingDuck implements Duck {
	@Override
	public void fly(int heigh) {
		// TODO Auto-generated method stub
		System.out.println("北京烤鸭，能飞" + heigh + "米高！！");
	}

	@Override
	public void taste(String taste) {
		// TODO Auto-generated method stub
		System.out.println("北京烤鸭，闻起来" + taste + "！！");
	}
}
