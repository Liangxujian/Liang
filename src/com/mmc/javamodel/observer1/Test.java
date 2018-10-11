package com.mmc.javamodel.observer1;

public class Test {
    public static void main(String[] args) {
        WechatServer server = new WechatServer();
        
        Observer userZhang = new User("ZhangSan");
        Observer userLi = new User("LiSi");
        Observer userWang = new User("WangWu");
        
        // ע���û�����ӹ۲���
        server.registerObserver(userZhang);
        server.registerObserver(userLi);
        server.registerObserver(userWang);
        server.setInfomation("PHP������������õ����ԣ�");
        
        System.out.println("----------------------------------------------");
        server.removeObserver(userZhang);
        // �Ƴ���ÿ��������Ϣ������ѭ��update����message���µ�ÿ���û�
        server.setInfomation("JAVA������������õ����ԣ�");
    }
}
