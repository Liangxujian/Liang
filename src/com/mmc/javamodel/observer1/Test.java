package com.mmc.javamodel.observer1;

public class Test {
    public static void main(String[] args) {
        WechatServer server = new WechatServer();
        
        Observer userZhang = new User("ZhangSan");
        Observer userLi = new User("LiSi");
        Observer userWang = new User("WangWu");
        
        // 注册用户，添加观察者
        server.registerObserver(userZhang);
        server.registerObserver(userLi);
        server.registerObserver(userWang);
        server.setInfomation("PHP是世界上最好用的语言！");
        
        System.out.println("----------------------------------------------");
        server.removeObserver(userZhang);
        // 移除后，每次设置信息都进行循环update，把message更新到每个用户
        server.setInfomation("JAVA是世界上最好用的语言！");
    }
}
