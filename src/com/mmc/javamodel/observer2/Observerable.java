package com.mmc.javamodel.observer2;

public interface Observerable {
	// 添加观察者
	public void addObserver(Observer observer);
	// 移除观察者
	public void removeObserver(Observer observer);
	// 通知观察者
	public void notice(String message);
}
