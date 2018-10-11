package com.mmc.javamodel.observer2;

/**
 * �۲���ģʽ�ؼ��㣺
 * 1.��Թ۲����뱻�۲��߷ֱ���ӿڣ������ڷֱ������չ
 * 2.����۲��߼��ϣ���������Լ��ϵ���ӡ�ɾ���������������ӡ�ɾ���۲��ߣ�
 *   ���۲��߶���֪ͨ���������ڽ������֪ͨ���۲���
 * 3.�۲�������Ҫ�и����ձ��۲���֪ͨ�ķ���
 * 
 * �۲���ģʽ�������һ�Զ��������ϵ��
 * 		 һ�����۲��߿���ӵ�ж���۲��ߣ�
 *  	����ͨ���ӿڶԹ۲����뱻�۲��߽����߼�������Ͷ��ߵ�ֱ�����
 */
public class Test {
	public static void main(String[] args) {
		// ���廵��
		Badman1 b1 = new Badman1();
		Badman2 b2 = new Badman2();
		// ���徯��
		Police1 p1 = new Police1();
		Police2 p2 = new Police2();
		Police3 p3 = new Police3();
		// Ϊ����ָ���۲쾯��
		b1.addObserver(p1);
		
		b2.addObserver(p1);
		b2.addObserver(p2);
		b2.addObserver(p3);
		
		// ���廵����Ϣ
		b1.notice("Ҫ�������������");
		b2.notice("���������һ�ף�");
		System.out.println();
		
		b1.addObserver(p3);
		b2.removeObserver(p1);
		b1.notice("��Ҫ������У���");
		b2.notice("������������ף�");
	}
}
