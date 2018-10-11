package com.mmc.test.lin.anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/*
 * @Retention(RetentionPolicy.RUNTIME):ָʾע�����͵�ע��Ҫ�������
 * 	RetentionPolicy.CLASS������������ע�ͼ�¼�����ļ��У���������ʱ VM ����Ҫ����ע�͡�����Ĭ�ϵ���Ϊ
 * 	RetentionPolicy.RUNTIME������������ע�ͼ�¼�����ļ��У�������ʱ VM ������ע�ͣ���˿��Է����Եض�ȡ
 * 	RetentionPolicy.SOURCE��������Ҫ������ע��
 */
@Retention(RetentionPolicy.RUNTIME)
/*
 * @Target(ElementType.TYPE) ָʾע�����������õĳ���Ԫ�ص�����
 * 	ElementType.ANNOTATION_TYPE��ע����������
 * 	ElementType.CONSTRUCTOR�����췽������
 * 	ElementType.FIELD���ֶ�����������ö�ٳ�����
 * 	ElementType.LOCAL_VARIABLE���ֲ���������
 * 	ElementType.METHOD����������
 * 	ElementType.PACKAGE��������
 * 	ElementType.PARAMETER����������
 * 	ElementType.TYPE���ࡢ�ӿڣ�����ע�����ͣ���ö������
 */
@Target(ElementType.TYPE)
/**
 * 
 * @author Administrator
 *
 */
public @interface Singleton {
	boolean value() default true;
	String name() default "��С��";
}
