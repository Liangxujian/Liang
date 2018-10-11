package com.mmc.test.lin.anno;

public class FactoryImpl implements Factory {
	private static Object object = null;

    @Override
    public Object create(Class clazz) throws Exception {
        if (clazz.isAnnotationPresent(Singleton.class)) {// 判断是否存在注释类型
            // 获取注解的值
            Singleton annotation = (Singleton) clazz.getAnnotation(Singleton.class);// 如果存在该元素的指定类型的注释，则返回这些注释，否则返回 null
            boolean isSingle = annotation.value();
            if (isSingle) {
                // 使用单例来提供服务
                if (object == null)
                    object = clazz.newInstance();
            } else {
                // 使用非单例的形式提供服务。
                System.out.println("非单例");
                object = clazz.newInstance();
            }
        }
        return object;
    }
}
