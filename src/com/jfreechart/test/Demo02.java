package com.jfreechart.test;

import java.awt.Font;
import java.awt.Image;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.StandardChartTheme;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer3D;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.VerticalAlignment;

public class Demo02 {
	/**
	 * �������ݼ���
	 * 
	 * @return dataSet
	 */
	public static CategoryDataset createDataSet() {
		// ʵ����DefaultCategoryDataset����
		DefaultCategoryDataset dataSet = new DefaultCategoryDataset();
		// ��ӵ�һ��������
		dataSet.addValue(6000, "��һ����", "J2SE��");
		dataSet.addValue(3000, "��һ����", "J2ME��");
		dataSet.addValue(12000, "��һ����", "J2EE��");
		// ��ӵڶ���������
		dataSet.addValue(8000, "�ڶ�����", "J2SE��");
		dataSet.addValue(4000, "�ڶ�����", "J2ME��");
		dataSet.addValue(6000, "�ڶ�����", "J2EE��");
		// ��ӵ�����������
		dataSet.addValue(5000, "��������", "J2SE��");
		dataSet.addValue(4000, "��������", "J2ME��");
		dataSet.addValue(8000, "��������", "J2EE��");
		// ��ӵ��ļ�������
		dataSet.addValue(8000, "���ļ���", "J2SE��");
		dataSet.addValue(2000, "���ļ���", "J2ME��");
		dataSet.addValue(9000, "���ļ���", "J2EE��");
		return dataSet;
	}

	/**
	 * ����JFreeChart����
	 * 
	 * @return chart
	 */
	public static JFreeChart createChart() {
		StandardChartTheme standardChartTheme = new StandardChartTheme("CN"); // ����������ʽ
		standardChartTheme.setExtraLargeFont(new Font("����", Font.BOLD, 20)); // ���ñ�������
		standardChartTheme.setRegularFont(new Font("����", Font.PLAIN, 15)); // ����ͼ��������
		standardChartTheme.setLargeFont(new Font("����", Font.PLAIN, 15)); // �������������
		ChartFactory.setChartTheme(standardChartTheme);// ����������ʽ
		// ͨ��ChartFactory����JFreeChart
		JFreeChart chart = ChartFactory.createBarChart3D("Javaͼ������ͳ��", // ͼ�����
				"Javaͼ��", // �������
				"����������",// �������
				createDataSet(),// ���ݼ���
				PlotOrientation.VERTICAL, // ͼ����
				true,// �Ƿ���ʾͼ����ʶ
				false,// �Ƿ���ʾtooltips
				false);// �Ƿ�֧�ֳ�����
		// ����ͼƬ
		Image image = null;
		try {
			// ��������ͼƬ
			URL url = new URL("https://hk.ulifestyle.com.hk/cms/images/event/1024/201505/20150513195917_2_11041160_1704775113084156_4575830673514893101_o.jpg");
			image = ImageIO.read(url);
		} catch (IOException e) {
			e.printStackTrace();
		}
		chart.getTitle().setFont(new Font("����", Font.BOLD, 25)); // ���ñ�������
		chart.getLegend().setItemFont(new Font("����", Font.PLAIN, 12)); // ����ͼ���������
		chart.setBorderVisible(true); // ������ʾ�߿�
		TextTitle subTitle = new TextTitle(
				"2012��Java��ͼ��ȫ������ͳ�ƣ�J2SE, J2ME, J2EE��");// ʵ����TextTitle����
		subTitle.setVerticalAlignment(VerticalAlignment.BOTTOM); // ���þ�����ʾ
		chart.addSubtitle(subTitle);// ����ӱ���
		CategoryPlot plot = chart.getCategoryPlot(); // ��ȡ��ͼ������
		plot.setForegroundAlpha(0.8F);// ���û�ͼ��ǰ��ɫ͸����
		plot.setBackgroundAlpha(0.5F);// ���û�ͼ������ɫ͸����
		plot.setBackgroundImage(image);// ���û�ͼ������ͼƬ
		CategoryAxis categoryAxis = plot.getDomainAxis();// ��ȡ���������
		categoryAxis.setLabelFont(new Font("����", Font.PLAIN, 12));// �����������������
		categoryAxis.setTickLabelFont(new Font("����", Font.PLAIN, 12));// �����������ֵ����
		categoryAxis.setCategoryLabelPositions(CategoryLabelPositions.UP_45);// ���������������ת�Ƕ�
		ValueAxis valueAxis = plot.getRangeAxis();// �������������
		valueAxis.setLabelFont(new Font("����", Font.PLAIN, 12));
		BarRenderer3D renderer = new BarRenderer3D();
		renderer.setItemMargin(0.32); // ��������ļ��
		plot.setRenderer(renderer);// ����ͼƬ��Ⱦ����
		return chart;
	}

	// ���ز���
	public static void main(String[] args) {
		ChartFrame cf = new ChartFrame("Test", createChart());
		cf.pack();
		cf.setVisible(true);
	}
}
