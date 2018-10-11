package com.mmc.test.lin.anno;

public class Test {
    public static void main(String[] args) throws Exception {
        Factory factory = new FactoryImpl();
        Object object = factory.create(Singleton.class);
        Object object2 = factory.create(Singleton.class);
        System.out.println(object==object2);
    }
}