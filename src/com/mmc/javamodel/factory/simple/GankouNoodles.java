package com.mmc.javamodel.factory.simple;

/**
 * 具体产品类--干扣面类
 */
public class GankouNoodles extends INoodles {
    @Override
    public void desc() {
        System.out.println("还是家里的干扣面好吃 6块一碗");
    }
}