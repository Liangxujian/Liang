package com.mmc.javamodel.factory.normal;

import java.util.Map;

/** 
 * ��������ģʽ_�����Ʒ�� 
 */  
public abstract class MyAbstractMessage implements IMyMessage {  
  
    private Map<String, Object> messageParam;// ����������Ϊ������Ʒ����Ҫ��ԭ���Ͽ⡣����Ǹ��Զ���Ķ�������Ϊ�˲��������ʹ��Map��  
  
    @Override  
    public Map<String, Object> getMessageParam() {  
        return messageParam;  
    }  
  
    @Override  
    public void setMessageParam(Map<String, Object> messageParam) {  
        this.messageParam = messageParam;  
    }  
}
