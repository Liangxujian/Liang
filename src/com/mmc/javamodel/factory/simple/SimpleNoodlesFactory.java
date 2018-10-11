package com.mmc.javamodel.factory.simple;

/**
 * 简单工厂类--面馆
 * 这种工厂是一个具体的类，有一个重要的create()方法，利用if或者 switch创建产品并返回
 * 同时，create()方法通常是静态的，所以简单工厂也称之为静态工厂
 * 
 * 但是这种工厂的拓展性差（新增一种面条就要新增一个产品类，同时修改工厂类）
 * 当不同的产品需要不同额外参数的时候，不支持（不同的desc方法传不同参数）
 */
public class SimpleNoodlesFactory {
    public static final int TYPE_LZ = 1;//兰州拉面
    public static final int TYPE_PM = 2;//泡面
    public static final int TYPE_GK = 3;//干扣面

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
