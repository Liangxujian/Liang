package com.mmc.javamodel.observer2;

import java.util.ArrayList;
import java.util.List;

public class Badman1 implements Observerable {
	// 坏人绰号
	private String bname = "法官";
	// 定义观察者集合
	private List<Observer> list = new ArrayList<>();

	@Override
	public void addObserver(Observer observer) {
		// TODO Auto-generated method stub
		list.add(observer);
	}

	@Override
	public void removeObserver(Observer observer) {
		// TODO Auto-generated method stub
		list.remove(observer);
	}

	@Override
	public void notice(String message) {
		// TODO Auto-generated method stub
		for (Observer observer : list) {
			observer.update(message, bname);
		}
	}
}
