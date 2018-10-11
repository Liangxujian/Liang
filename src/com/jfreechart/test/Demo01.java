package com.jfreechart.test;

import java.awt.Font;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.StandardChartTheme;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

public class Demo01 {
	/** 
     * �������ݼ��� 
     * @return dataSet 
     */  
    public static CategoryDataset createDataSet() {  
        // ʵ����DefaultCategoryDataset����  
        DefaultCategoryDataset dataSet = new DefaultCategoryDataset();  
        // �����ݼ������������  
        dataSet.addValue(500, "Javaͼ��", "J2SE��");  
        dataSet.addValue(100, "Javaͼ��", "J2ME��");  
        dataSet.addValue(900, "Javaͼ��", "J2EE��");  
        dataSet.addValue(654, "PHPͼ��", "J2EE��");  
        return dataSet;  
    }  
    /** 
     * ����JFreeChart���� 
     * @return chart 
     */  
    public static JFreeChart createChart() {  
        StandardChartTheme standardChartTheme = new StandardChartTheme("CN"); //����������ʽ  
        standardChartTheme.setExtraLargeFont(new Font("����", Font.BOLD, 20)); //���ñ�������  
        standardChartTheme.setRegularFont(new Font("����", Font.PLAIN, 15)); //����ͼ��������  
        standardChartTheme.setLargeFont(new Font("����", Font.PLAIN, 15)); //�������������  
        ChartFactory.setChartTheme(standardChartTheme);//����������ʽ  
        // ͨ��ChartFactory����JFreeChart  
        JFreeChart chart = ChartFactory.createBarChart3D(  
                "Javaͼ������ͳ��", //ͼ�����  
                "Javaͼ��", //�������  
                "����������",//�������  
                createDataSet(),//���ݼ���  
                PlotOrientation.VERTICAL, //ͼ����  
                true,//�Ƿ���ʾͼ����ʶ  
                false,//�Ƿ���ʾtooltips  
                false);//�Ƿ�֧�ֳ�����  
        return chart;  
    }  
    // ���ز���  
    public static void main(String[] args) {  
        ChartFrame cf = new ChartFrame("Test", createChart());  
        cf.pack();  
        cf.setVisible(true);  
    }  
}
