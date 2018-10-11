package com.mmc.javamodel.observer1;

/***
 * 抽象被观察者接口
 * 声明了添加、删除、通知观察者方法
 */
public interface Observerable {
	// 登记观察者
    public void registerObserver(Observer o);
    
    // 移除观察者
    public void removeObserver(Observer o);
    
    // 通知观察者
    public void notifyObserver();
}
