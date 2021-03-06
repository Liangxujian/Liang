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
     * 创建数据集合 
     * @return dataSet 
     */  
    public static CategoryDataset createDataSet() {  
        // 实例化DefaultCategoryDataset对象  
        DefaultCategoryDataset dataSet = new DefaultCategoryDataset();  
        // 向数据集合中添加数据  
        dataSet.addValue(500, "Java图书", "J2SE类");  
        dataSet.addValue(100, "Java图书", "J2ME类");  
        dataSet.addValue(900, "Java图书", "J2EE类");  
        dataSet.addValue(654, "PHP图书", "J2EE类");  
        return dataSet;  
    }  
    /** 
     * 创建JFreeChart对象 
     * @return chart 
     */  
    public static JFreeChart createChart() {  
        StandardChartTheme standardChartTheme = new StandardChartTheme("CN"); //创建主题样式  
        standardChartTheme.setExtraLargeFont(new Font("隶书", Font.BOLD, 20)); //设置标题字体  
        standardChartTheme.setRegularFont(new Font("宋体", Font.PLAIN, 15)); //设置图例的字体  
        standardChartTheme.setLargeFont(new Font("宋体", Font.PLAIN, 15)); //设置轴向的字体  
        ChartFactory.setChartTheme(standardChartTheme);//设置主题样式  
        // 通过ChartFactory创建JFreeChart  
        JFreeChart chart = ChartFactory.createBarChart3D(  
                "Java图书销量统计", //图表标题  
                "Java图书", //横轴标题  
                "销量（本）",//纵轴标题  
                createDataSet(),//数据集合  
                PlotOrientation.VERTICAL, //图表方向  
                true,//是否显示图例标识  
                false,//是否显示tooltips  
                false);//是否支持超链接  
        return chart;  
    }  
    // 本地测试  
    public static void main(String[] args) {  
        ChartFrame cf = new ChartFrame("Test", createChart());  
        cf.pack();  
        cf.setVisible(true);  
    }  
}
