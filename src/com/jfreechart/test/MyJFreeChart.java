package com.jfreechart.test;

import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.Random;

import org.apache.poi.xssf.usermodel.XSSFClientAnchor;
import org.apache.poi.xssf.usermodel.XSSFDrawing;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.NumberTickUnit;
import org.jfree.chart.labels.ItemLabelAnchor;
import org.jfree.chart.labels.ItemLabelPosition;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.DatasetRenderingOrder;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.chart.renderer.category.StackedBarRenderer;
import org.jfree.chart.renderer.category.StandardBarPainter;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DatasetUtilities;
import org.jfree.io.IOUtils;
import org.jfree.ui.TextAnchor;

public class MyJFreeChart {  
    public static final String[] ITMES = {"JAN","Feb","Mar"};  
  
    //Stacked Bar Chart data  
    private static CategoryDataset categoryDataset(String[] items){  
        String[] series = new String[]{"Green","Amber","Red"};  
  
        double[] greenItems = new double[items.length];  
        double[] amberItems = new double[items.length];  
        double[] redItems = new double[items.length];  
  
        Random rd = null;  
        for (int i = 0; i < items.length; i++) {  
            rd = new Random();  
            greenItems[i] = 700 + rd.nextInt(100);  
            amberItems[i] = 150 + rd.nextInt(100);  
            redItems[i] = 10 + rd.nextInt(100);  
        }  
  
        double[][] data = new double[][]{greenItems,amberItems,redItems};  
        return DatasetUtilities.createCategoryDataset(series,ITMES,data);  
    }  
    //line Chart data  
    private static CategoryDataset categoryLineDataset(String[] items){  
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();  
        String s = "%Reds";  
  
        Random rd = null;  
        for (int i = 0; i < items.length; i++) {  
            rd = new Random();  
            dataset.addValue(rd.nextDouble()/10,s,items[i]);  
        }  
  
        return dataset;  
    }  
  
    private static JFreeChart createChart(CategoryDataset categoryBarDataset,CategoryDataset categoryLineDataset){  
        JFreeChart chart = ChartFactory.createBarChart("Title","","",categoryBarDataset, PlotOrientation.VERTICAL,true,true,false);  
        CategoryPlot plot = (CategoryPlot)chart.getPlot();  
        //set right margin  
        plot.getDomainAxis().setUpperMargin(0.1);  
        //set X axis Label lines  
        plot.getDomainAxis().setMaximumCategoryLabelLines(2);  
        NumberAxis na = (NumberAxis)plot.getRangeAxis();  
        //stack bar chart  
        StackedBarRenderer rederer = new StackedBarRenderer();  
        plot.setRenderer(0,rederer);  
        rederer.setBaseItemLabelsVisible(true);  
        rederer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());  
        rederer.setBaseItemLabelFont(new Font("Calibri",Font.ITALIC,15));  
        rederer.setMaximumBarWidth(0.07);  
        rederer.setMinimumBarLength(0.1);  
        rederer.setBarPainter(new StandardBarPainter());  
        rederer.setSeriesPaint(0,Color.green);  
        rederer.setSeriesPaint(1,Color.yellow);  
        rederer.setSeriesPaint(2,Color.red);  
  
        //line chart  
        LineAndShapeRenderer lineRenderer = new LineAndShapeRenderer();  
        plot.setRenderer(1,lineRenderer);  
  
        //set data display as x.x%  
        NumberFormat nf = NumberFormat.getPercentInstance();  
        nf.setMinimumFractionDigits(1);  
        lineRenderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator("{2}",nf));  
        lineRenderer.setBaseItemLabelFont(new Font("Calibri",Font.ITALIC,15));  
        lineRenderer.setBasePositiveItemLabelPosition(new ItemLabelPosition(ItemLabelAnchor.INSIDE12, TextAnchor.BASELINE_LEFT));  
        lineRenderer.setBaseItemLabelsVisible(true);  
        lineRenderer.setBaseShapesVisible(true);  
        //point style  
        lineRenderer.setSeriesShape(0,new Rectangle(5,5));  
        lineRenderer.setBaseShapesVisible(true);  
        NumberAxis rightYAxis = new NumberAxis("");  
        //set axis display as x.x%  
        rightYAxis.setNumberFormatOverride(nf);  
        rightYAxis.setTickUnit(new NumberTickUnit(0.01));  
        //point axis, point dataset, mapping axis and dataset  
        plot.setRangeAxis(1,rightYAxis);  
        plot.setDataset(1,categoryLineDataset);  
        plot.mapDatasetToRangeAxis(1,1);  
        //set render order  
        plot.setDatasetRenderingOrder(DatasetRenderingOrder.FORWARD);  
        return chart;  
    }  
  
    //generate jpg and insert into excel  
    public static void main(String[] args) {  
        int rowsNum = 5;  
        JFreeChart chart = createChart(categoryDataset(ITMES),categoryLineDataset(ITMES));  
        FileOutputStream excelFos = null;  
        FileInputStream excelFis = null;  
  
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(128);  
        try {  
            ChartUtilities.writeChartAsJPEG(byteArrayOutputStream,1.0f,chart,1200,900);  
            excelFis = new FileInputStream("D://testFreeChart.xlsx");  
            XSSFWorkbook wb = new XSSFWorkbook(excelFis);  
            XSSFSheet sheet = wb.getSheetAt(0);  
            XSSFDrawing patriarch = sheet.createDrawingPatriarch();  
            //                                             begin column,begin row;  end column,end row;  
            //1,0 + rowsNum,15,42+rowsNum; insert from cell(1,0 + rowsNum) to cell(15,42 + rowsNum)  
            XSSFClientAnchor anchor = new XSSFClientAnchor(0,0,0,0,1,0 + rowsNum,15,42+rowsNum);  
            patriarch.createPicture(anchor,wb.addPicture(byteArrayOutputStream.toByteArray(),XSSFWorkbook.PICTURE_TYPE_JPEG));  
            excelFos = new FileOutputStream("D://result.xlsx");  
            wb.write(excelFos);  
        } catch (IOException e) {  
            e.printStackTrace();  
        }finally {  
			// IOUtils.closeQuietly(byteArrayOutputStream);
			// IOUtils.closeQuietly(excelFos);
			// IOUtils.closeQuietly(excelFis);
        }  
    }  
}  
