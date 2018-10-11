package com.mmc.javamodel.factory.simple;

/**
 * 具体产品类--泡面类
 */
public class PaoNoodles extends INoodles {
    @Override
    public void desc() {
        System.out.println("泡面好吃，可不要贪杯！");
    }
}
