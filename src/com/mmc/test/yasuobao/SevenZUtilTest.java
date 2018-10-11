package com.mmc.test.yasuobao;

import net.sf.sevenzipjbinding.*;
import net.sf.sevenzipjbinding.impl.RandomAccessFileInStream;
import net.sf.sevenzipjbinding.simple.ISimpleInArchive;
import net.sf.sevenzipjbinding.simple.ISimpleInArchiveItem;
import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.RandomAccessFile;
import java.util.Arrays;

public class SevenZUtilTest {
    /**
     * @param file7zPath(7z文件路径)
     * @param outPutPath(解压路径)
     * @param passWord(文件密码.没有可随便写,或空)
     * @return
     * @throws Exception
     * @Description (解压7z)
     */
    public static int un7z(String file7zPath, final String outPutPath, String passWord) throws Exception {
        IInArchive archive;
        RandomAccessFile randomAccessFile;
        randomAccessFile = new RandomAccessFile(file7zPath, "r");
        archive = SevenZip.openInArchive(null, new RandomAccessFileInStream(randomAccessFile), passWord);
        int numberOfItems = archive.getNumberOfItems();
        ISimpleInArchive simpleInArchive = archive.getSimpleInterface();
        for (final ISimpleInArchiveItem item : simpleInArchive.getArchiveItems()) {
            final int[] hash = new int[]{0};
            if (!item.isFolder()) {
                ExtractOperationResult result;
                final long[] sizeArray = new long[1];
                result = item.extractSlow(new ISequentialOutStream() {
                    public int write(byte[] data) throws SevenZipException {
                        try {
                            //判断压缩包内的文件是否存在
                            String parentFilePath = outPutPath + File.separator + item.getPath().substring(0, item.getPath().lastIndexOf(File.separator));
                            if (!new File(parentFilePath).exists()) {
                                new File(parentFilePath).mkdirs();
                            }
                            IOUtils.write(data, new FileOutputStream(new File(outPutPath + File.separator + item.getPath()), true));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        hash[0] ^= Arrays.hashCode(data); // Consume data
                        sizeArray[0] += data.length;
                        return data.length; // Return amount of consumed
                    }
                }, passWord);
                if (result == ExtractOperationResult.OK) {
                    System.out.println(String.format("%9X | %10s | %s", hash[0], sizeArray[0], item.getPath()));
                } else {
                    System.out.printf("Error extracting item: " + result);
                }
            }
        }
        archive.close();
        randomAccessFile.close();

        return numberOfItems;
    }

    public static void main(String[] args) throws Exception {

        un7z("d:\\test\\11\\cc.7z", "d:\\test\\11", "123");
    }
}
