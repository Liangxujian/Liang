package com.mmc.javamodel.factory.normal;

import java.util.Map;

/** 
 * ��������ģʽ_��Ʒ�ӿ� 
 */  
public interface IMyMessage {  
  
    public Map<String, Object> getMessageParam();  
  
    public void setMessageParam(Map<String, Object> messageParam);  
  
    public void sendMesage() throws Exception;// ����֪ͨ/��Ϣ  
  
} 
