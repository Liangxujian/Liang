package com.mmc.javamodel.factory.normal;

/** 
 * ��������ģʽ_�������� 
 */  
public class MyFactoryMethodMain {  
  
    public static void main(String[] args) {  
        IMyMessageFactory myMessageFactory = new MyMessageFactory();  
        IMyMessage myMessage;  
        // ���������������˵������֪���������message�����Ʒ����϶Ƚ���  
        try {  
            // ����һ������֪ͨ  
            myMessage = myMessageFactory.createMessage("SMS");  
            myMessage.sendMesage();  
  
            // ��һ��oa����  
            myMessage = myMessageFactory.createMessage("OA");  
            myMessage.sendMesage();  
  
            // ��һ���ʼ�֪ͨ  
            myMessage = myMessageFactory.createMessage("EMAIL");  
            myMessage.sendMesage();  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
    }  
}  