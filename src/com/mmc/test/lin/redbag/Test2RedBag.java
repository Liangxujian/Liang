package com.mmc.test.lin.redbag;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Test2RedBag {
    /**
     * �ȽϹ�ƽ�ķ�ʽ���߶��и��
     * �����ĺ���������ʱ��͸о�ͦ��ƽ�ġ������ٸ�����
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
        System.out.println("min�ĸ���Ϊ" + min);
    }

    private static  List<Integer> divide(double money, int n) {
        //��֤��������У��
        int fen = (int)(money*100);
        if (fen < n || n < 1) {
            System.out.println("��������������0��������С���������1��");
         }
        List<Integer> boards = new ArrayList<>();
        boards.add(0);
        boards.add(fen);
        //��������Ͱ�ש�����Ĺ�ϵ
        while (boards.size() < n+1) {
            int index = new Random().nextInt(fen-1)+1;
            if (boards.contains(index)) {
                //��֤���ӵ�λ�ò���ͬ
                continue;
            }
            boards.add(index);
        }
        //����ÿ������Ľ�����������֮���Ǯ������
        Collections.sort(boards);
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < boards.size()-1; i++) {
            Integer e = boards.get(i+1) - boards.get(i);
            list.add(e);
        }
        return list;
    }
}
