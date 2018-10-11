package com.jfreechart.test;

import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;

public class Demo03 {
	public static void main(String[] args) {
		PiePlot pp = new PiePlot(createDataset());
		JFreeChart chart = new JFreeChart(pp);
		ChartFrame cf = new ChartFrame("Test", chart);
		cf.pack();
		cf.setVisible(true);
	}
	
	private static PieDataset createDataset() {
		DefaultPieDataset dataset = new DefaultPieDataset();
		dataset.setValue("IPhone 5s", new Double(20));
		dataset.setValue("SamSung Grand", new Double(20));
		dataset.setValue("MotoG", new Double(40));
		dataset.setValue("Nokia Lumia", new Double(10));
		return dataset;
	}
}
