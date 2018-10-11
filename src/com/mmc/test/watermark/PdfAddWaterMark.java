package com.mmc.test.watermark;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.filechooser.FileSystemView;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfGState;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;

public class PdfAddWaterMark {
	public static void main(String[] args) {
		FileSystemView fsv = FileSystemView.getFileSystemView();
		File com = fsv.getHomeDirectory(); // 这便是读取桌面路径的方法了
		String path = com.getAbsolutePath();
		/*
		 *  正常：88290005-035(04).pdf
		 *  异常：88290004-355_00电机型式试验报告.pdf
		 */
//		File sourceFile = new File(path + "\\88290005-035(04).pdf");
		File sourceFile = new File(path + "\\88290004-355_00电机型式试验报告.pdf");
		String imgPath = path + "\\有水印.png";
		
		PdfAddWaterMark pawm = new PdfAddWaterMark();
		if (sourceFile.exists()) {
			pawm.addWaterMark(sourceFile, imgPath);
		}
	}
	
	public void addWaterMark(File sourceFile, String imgPath){
		String fileName = sourceFile.getName();
		String realName = fileName.substring(0, fileName.lastIndexOf("."));
		String suffix = fileName.substring(fileName.lastIndexOf("."),fileName.length());
		
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMddHHmmss");
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy年MM月dd日");
		String time = sdf1.format(new Date());
		
		String outPath = sourceFile.getParent() + "\\" + realName + time + suffix;
		
		PdfReader pdfReader = null;
		PdfStamper pdfStamper = null;
		Image image = null;
		try {
			pdfReader = new PdfReader(sourceFile.getAbsolutePath(), "PDF".getBytes());
			pdfStamper = new PdfStamper(pdfReader, new FileOutputStream(outPath));
			image = Image.getInstance(imgPath);
			addPdfMark_T(pdfStamper, image, 10, 10);
			addUserNameDatePDF_T(pdfStamper, "梁旭健", sdf2.format(new Date()));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (pdfStamper != null) {
					pdfStamper.close();
				}
				if (pdfReader != null) {
					pdfReader.close();
				}
			} catch (DocumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	private void addPdfMark_T(PdfStamper pdfStamper, Image img, int x, int y) throws Exception {
		int pageNum = pdfStamper.getReader().getNumberOfPages();
		Rectangle pageRect = null;
		PdfContentByte content = null;
		for (int i = 1; i <= pageNum; i++) {
			pageRect = pdfStamper.getReader().getPageSizeWithRotation(i);
			float pageHeight = pageRect.getTop();
			float imgHeight = img.getHeight();
			img.setAbsolutePosition(x, pageHeight - imgHeight - y);
			content = pdfStamper.getOverContent(i);
			content.addImage(img);
		}
//		int pageNum = pdfStamper.getReader().getNumberOfPages();
//		Rectangle pageRect = null;
//		PdfContentByte content = null;
//		for (int i = 1; i <= pageNum; i++) {
//			pageRect = pdfStamper.getReader().getPageSizeWithRotation(i);
//			float pageHeight = pageRect.getTop();
//			float height = pageRect.getHeight();
//			img.setAbsolutePosition(x, y + pageHeight - height);
//			content = pdfStamper.getOverContent(i);
//			content.addImage(img);
//		}
	}
	
	private void addUserNameDatePDF_T(PdfStamper pdfStamper, String username,String datastr) {
		try {
			addWatermark(pdfStamper, username, 60, 55);
			addWatermark(pdfStamper, datastr, 70, 75);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void addWatermark(PdfStamper pdfStamper, String waterMarkName, float x, float y) {
		PdfContentByte content = null;
		BaseFont base = null;
		Rectangle pageRect = null;
		PdfGState gs = new PdfGState();
		try {
            // 设置字体
        	base = BaseFont.createFont("STSongStd-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            if (base == null || pdfStamper == null) {
                return;
            }
            // 设置透明度为0.4
            gs.setFillOpacity(1.0f);
            gs.setStrokeOpacity(1.0f);
            int toPage = pdfStamper.getReader().getNumberOfPages();
            for (int i = 1; i <= toPage; i++) {
                pageRect = pdfStamper.getReader().getPageSizeWithRotation(i);
                // 计算水印X,Y坐标
                //float x = pageRect.getWidth() / 2;
                float y1 = pageRect.getTop() ;
                float y2 = pageRect.getHeight();
                //获得PDF最顶层
                content = pdfStamper.getOverContent(i);
                content.saveState();
                content.setGState(gs);
                content.beginText();
                content.setColorFill(BaseColor.BLACK);
                content.setFontAndSize(base, 12);
                // 水印文字成45度角倾斜
                content.showTextAligned(Element.ALIGN_CENTER, waterMarkName, x, y1 - y2 + y, 0);
                content.endText(); 
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            content = null;
            base = null;
            pageRect = null;
        }
    }
}
