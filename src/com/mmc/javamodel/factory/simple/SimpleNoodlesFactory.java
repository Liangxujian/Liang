package com.mmc.javamodel.factory.simple;

/**
 * �򵥹�����--���
 * ���ֹ�����һ��������࣬��һ����Ҫ��create()����������if���� switch������Ʒ������
 * ͬʱ��create()����ͨ���Ǿ�̬�ģ����Լ򵥹���Ҳ��֮Ϊ��̬����
 * 
 * �������ֹ�������չ�Բ����һ��������Ҫ����һ����Ʒ�࣬ͬʱ�޸Ĺ����ࣩ
 * ����ͬ�Ĳ�Ʒ��Ҫ��ͬ���������ʱ�򣬲�֧�֣���ͬ��desc��������ͬ������
 */
public class SimpleNoodlesFactory {
    public static final int TYPE_LZ = 1;//��������
    public static final int TYPE_PM = 2;//����
    public static final int TYPE_GK = 3;//�ɿ���

    public static INoodles createNoodles(int type) {
        switch (type) {
            case TYPE_LZ:
                return new LzNoodles();
            case TYPE_PM:
                return new PaoNoodles();
            case TYPE_GK:
            default:
                return new GankouNoodles();
        }
    }
}
