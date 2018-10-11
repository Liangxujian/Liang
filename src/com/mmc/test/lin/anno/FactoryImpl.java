package com.mmc.test.lin.anno;

public class FactoryImpl implements Factory {
	private static Object object = null;

    @Override
    public Object create(Class clazz) throws Exception {
        if (clazz.isAnnotationPresent(Singleton.class)) {// �ж��Ƿ����ע������
            // ��ȡע���ֵ
            Singleton annotation = (Singleton) clazz.getAnnotation(Singleton.class);// ������ڸ�Ԫ�ص�ָ�����͵�ע�ͣ��򷵻���Щע�ͣ����򷵻� null
            boolean isSingle = annotation.value();
            if (isSingle) {
                // ʹ�õ������ṩ����
                if (object == null)
                    object = clazz.newInstance();
            } else {
                // ʹ�÷ǵ�������ʽ�ṩ����
                System.out.println("�ǵ���");
                object = clazz.newInstance();
            }
        }
        return object;
    }
}
