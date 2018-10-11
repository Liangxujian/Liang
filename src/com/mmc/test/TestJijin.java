package com.mmc.test;

/**
 * 七日年化收益率计算
 */
public class TestJijin {
	public static void main(String[] args) {
		TestJijin test = new TestJijin();
		System.out.println(test.getIncome(40000, "5.0960%", 30));
	}
	
	/**
	 * 计算day天后的收益
	 * @param principal 本金
	 * @param percent 七日年化收益率
	 * @param day 存放天数
	 * @return 返回理论上的收益
	 */
	public double getIncome(int principal, String percent, int day) {
		if (principal >= 1000) {
			percent = percent.substring(0, percent.lastIndexOf("%"));
			try {
				double percentD = Double.valueOf(percent);
				double incomeD = percentD / 100 / 365 * day * principal + principal;
				return incomeD;
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return 0;
			}
		}
		return 0;
	}
	
	public String getPercent(int principal) {
		return null;
	}
}
