package com.mmc.javamodel.observer1;

/***
 * ���󱻹۲��߽ӿ�
 * ��������ӡ�ɾ����֪ͨ�۲��߷���
 */
public interface Observerable {
	// �Ǽǹ۲���
    public void registerObserver(Observer o);
    
    // �Ƴ��۲���
    public void removeObserver(Observer o);
    
    // ֪ͨ�۲���
    public void notifyObserver();
}
