package com.mmc.javamodel.observer1;

/***
 * ����۲���
 * ������һ��update()�����������۲��ߵ���notifyObservers()����ʱ���۲��ߵ�update()�����ᱻ�ص���
 */
public interface Observer {
    public void update(String message);
}