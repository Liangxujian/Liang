package com.mmc.javamodel.factory.simple;

/**
 * 具体产品类--兰州拉面
 */
public class LzNoodles extends INoodles {
    @Override
    public void desc() {
        System.out.println("上海的兰州拉面好贵，家里才5、6块钱一碗");
    }
}
