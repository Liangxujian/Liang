package com.mmc.javamodel.factory.normal;

/** 
 * ��������ģʽ_email��Ʒ 
 */  
public class MyMessageEmail extends MyAbstractMessage {  
  
    @Override  
    public void sendMesage() throws Exception {  
        // TODO Auto-generated method stub  
        if (null == getMessageParam() || null == getMessageParam().get("EMAIL") || "".equals(getMessageParam().get("EMAIL"))) {  
            throw new Exception("���Ͷ���,��Ҫ����EMAIL����");// Ϊ�˼�����쳣Ҳ���Զ�����  
        }// �����ʼ����ݣ��Լ���������Э������ȵȶ�Ҫ����  
  
        System.out.println("�����ʼ�������֪ͨ��" + getMessageParam().get("EMAIL"));  
    }  
  
}  
