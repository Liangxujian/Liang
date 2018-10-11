package com.mmc.test.lin.redbag;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Test2RedBag {
    /**
     * 比较公平的方式，线段切割法。
     * 当发的红包个数大的时候就感觉挺公平的。发的少浮动大。
     * @param args
     */
    public static void main(String[] args) {
        List<Integer> a = divide(1000,5000);
        int max = 0;
        int second = 0;
        int min = 0;
        for (int item : a) {
            if (item > max)
                max = item;
            if (item <max && item >second)
                second = item;
            if (item == 1) 
                min += 1;
        }
        System.out.println(max);
        System.out.println(second);
        System.out.println("min的个数为" + min);
    }

    private static  List<Integer> divide(double money, int n) {
        //验证参数合理校验
        int fen = (int)(money*100);
        if (fen < n || n < 1) {
            System.out.println("红包个数必须大于0，并且最小红包不少于1分");
         }
        List<Integer> boards = new ArrayList<>();
        boards.add(0);
        boards.add(fen);
        //红包个数和板砖个数的关系
        while (boards.size() < n+1) {
            int index = new Random().nextInt(fen-1)+1;
            if (boards.contains(index)) {
                //保证板子的位置不相同
                continue;
            }
            boards.add(index);
        }
        //计算每个红包的金额，将两个板子之间的钱加起来
        Collections.sort(boards);
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < boards.size()-1; i++) {
            Integer e = boards.get(i+1) - boards.get(i);
            list.add(e);
        }
        return list;
    }
}
