package com.mmc.javamodel.factory.abstra;

/**
 * ���󹤳�ģʽ
 * ���󹤳�ģʽ���˾��й�������ģʽ���ŵ��⣬����Ҫ���ŵ���ǿ���������ڲ��Բ�Ʒ�����Լ����
 * ��ν�Ĳ�Ʒ�壬һ������ٵĶ�����һ���Ĺ��������󹤳�ģʽ�Ϳ��������ڲ��Բ�Ʒ��Ĺ�����ϵ���ж����������������ר������һ���µ��������й���
 * 
 * ���ǲ�Ʒ�����չ����һ��ʮ�ַ��������飬�����Ʒ������Ҫ����һ���µĲ�Ʒ���򼸺����еĹ����඼��Ҫ�����޸ġ�
 * ����ʹ�ó��󹤳�ģʽʱ���Բ�Ʒ�ȼ��ṹ�Ļ����Ƿǳ���Ҫ��
 */
public class Factory implements IFactory {

	@Override
	public IHWPhones createHWPhones() {
		// TODO Auto-generated method stub
		return new HWPhones();
	}

	@Override
	public IXMPhones createXMPhones() {
		// TODO Auto-generated method stub
		return new XMPhones();
	}

}
