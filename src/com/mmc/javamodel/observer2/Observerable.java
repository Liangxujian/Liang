package com.mmc.javamodel.observer2;

public interface Observerable {
	// ��ӹ۲���
	public void addObserver(Observer observer);
	// �Ƴ��۲���
	public void removeObserver(Observer observer);
	// ֪ͨ�۲���
	public void notice(String message);
}
