package com.mmc.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * ����·�� ����ͼƬ Ȼ�� ���浽��Ӧ��Ŀ¼��
 * 
 * @param urlString
 *            ����Դ��ַ·�� http://media.expedia.com/hotels/1000000/10000/100/1/1_17_b.jpg
 * @param filename
 *            �ļ���
 * @param savePath
 *            ����·�� /jdylog/pic/JDY000001
 * @return
 * @throws Exception
 */
public class Test05DownloadPicture {
	public static void main(String[] args) {
		Test05DownloadPicture test = new Test05DownloadPicture();
		String savePath = "D:\\12";
//		List<String> results = readFileByLines("C:\\Users\\Administrator\\Desktop\\11.txt");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		List<String> list = null;
		try {
			Date beginDate = sdf.parse("2017-07-01");
			Date endDate = sdf.parse("2017-12-31");
			list = test.findDates(beginDate, endDate);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if(list.size() > 0){
			for(String dateStr : list){
				if(dateStr.isEmpty()){
					continue;
				}
				String urlStr = new StringBuilder("https://bg.blyoo.com/bing/tu/").append(dateStr).append(".jpg").toString();
				String filename = new StringBuilder(dateStr).append(".jpg").toString();
				try {
					test.download(urlStr, filename, savePath);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	public List<String> findDates(Date dBegin, Date dEnd) {
		List<String> lDate = new ArrayList<String>();
		SimpleDateFormat sd = new SimpleDateFormat("yyyyMMdd");
		lDate.add(sd.format(dBegin));
		Calendar calBegin = Calendar.getInstance();
		// ʹ�ø����� Date ���ô� Calendar ��ʱ��
		calBegin.setTime(dBegin);
		Calendar calEnd = Calendar.getInstance();
		// ʹ�ø����� Date ���ô� Calendar ��ʱ��
		calEnd.setTime(dEnd);
		// ���Դ������Ƿ���ָ������֮��
		while (dEnd.after(calBegin.getTime())) {
			// ���������Ĺ���Ϊ�����������ֶ���ӻ��ȥָ����ʱ����
			calBegin.add(Calendar.DAY_OF_MONTH, 1);
			lDate.add(sd.format(calBegin.getTime()));
		}
		return lDate;
	}

	public void download(String urlString, String filename, String savePath)
			throws Exception {
		// ����URL
		// System.setProperty("http.proxySet", "true");
		// System.setProperty("http.proxyHost", "192.168.2.138");
		// System.setProperty("http.proxyPort", "1081");
		URL url = new URL(urlString);
		// ������
		URLConnection con = url.openConnection();
		// ���������·��
		con.setConnectTimeout(5 * 1000);
		// ������
		InputStream is = con.getInputStream();

		// 1K�����ݻ���
		byte[] bs = new byte[1024];
		// ��ȡ�������ݳ���
		int len;
		// ������ļ���
		File sf = new File(savePath);
		if (!sf.exists()) {
			sf.mkdirs();
		}
		OutputStream os = new FileOutputStream(sf.getPath() + "/" + filename);
		// ��ʼ��ȡ
		while ((len = is.read(bs)) != -1) {
			os.write(bs, 0, len);
		}
		// ��ϣ��ر���������
		os.close();
		is.close();
	}

	public String readToString(String fileName) {
		String encoding = "UTF-8";
		File file = new File(fileName);
		Long filelength = file.length();
		byte[] filecontent = new byte[filelength.intValue()];
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(file);
			fis.read(filecontent);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (fis != null) {
					fis.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		try {
			return new String(filecontent, encoding);
		} catch (UnsupportedEncodingException e) {
			System.err.println("The OS does not support " + encoding);
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 
	 * ����Ϊ��λ��ȡ�ļ��������ڶ������еĸ�ʽ���ļ�
	 */
	public static List<String> readFileByLines(String fileName) {
		File file = new File(fileName);
		List<String> list = new ArrayList<String>();
		BufferedReader reader = null;
		try {
			System.out.println("����Ϊ��λ��ȡ�ļ����ݣ�һ�ζ�һ���У�");
			reader = new BufferedReader(new FileReader(file));
			String tempString = null;
			int line = 1;
			// һ�ζ���һ�У�ֱ������nullΪ�ļ�����
			while ((tempString = reader.readLine()) != null) {
				// ��ʾ�к�
				System.out.println("line " + line + ": " + tempString);
				list.add(tempString);
				line++;
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (reader != null) {
					reader.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return list;
	}
}
