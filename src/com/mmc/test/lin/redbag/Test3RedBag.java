package com.mmc.test.lin.redbag;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Test3RedBag {
    // ������㷨���������Է�Ϊ��λ
    /** https://mp.weixin.qq.com/s/7yDbdKHJ3OmNw_015Jc8Cg
     * �������һ�Σ��κ�һ�ζ��ٹ��˾����������Ǳ�֤������̫����̬û��ô���ױ�ը��
     * @param totalAmount ��
     * @param totalPeopleNum ���Ÿ���
     * @return
     */
    public static List<Integer> divideRedPackage(Integer totalAmount, Integer totalPeopleNum) {
        List<Integer> amountList = new ArrayList<Integer>();
        Integer restAmount = totalAmount;
        Integer restPeopleNum = totalPeopleNum;
        Random random = new Random();
        for (int i = 0; i < totalPeopleNum - 1; i++) {
            // �����Χ��[1��ʣ���˾���������)������ҿ�
            int amount = random.nextInt(restAmount / restPeopleNum * 2 - 1) + 1;
            restAmount -= amount;
            restPeopleNum--;
            amountList.add(amount);
        }
        amountList.add(restAmount);
        return amountList;
    }

    public static void main(String[] args) {
        List<Integer> amountList = divideRedPackage(10000, 5);
        for (Integer amount : amountList) {
            System.out.println("������" + new BigDecimal(amount).divide(new BigDecimal(100)));
        }
    }
}
