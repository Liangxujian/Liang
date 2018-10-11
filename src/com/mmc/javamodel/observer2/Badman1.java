package com.mmc.javamodel.observer2;

import java.util.ArrayList;
import java.util.List;

public class Badman1 implements Observerable {
	// ���˴º�
	private String bname = "����";
	// ����۲��߼���
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
