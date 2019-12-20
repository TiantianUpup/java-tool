package com.h2t.study.utils;

import com.csvreader.CsvReader;
import com.csvreader.CsvWriter;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * CSV文件读取工具类
 *
 * @author hetiantian
 * @version 1.0
 * @Date 2019/09/02 14:11
 */
public class CSVUtil<T> {
    /**
     * Read from CSV
     *
     * @param separator 分隔符
     * @param filePath 文件路径
     * @return
     * */
    public static<T> List<T> readFromCSV(Character separator, String filePath) {
        CsvReader reader = null;
        List<T> result = new ArrayList<>();
        try {
            //如果生产文件乱码，windows下用gbk，linux用UTF-8
            reader = new CsvReader(filePath, separator, Charset.forName("GBK"));

            // 读取标题
            reader.readHeaders();
            // 逐条读取记录，直至读完
            while (reader.readRecord()) {
                //读取第一例
                reader.get(0);
                //读取指定名字的列
                reader.get("");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != reader) {
                reader.close();
            }
        }

        return result;
    }

    /**
     * Write into CSV
     *
     * @param separator 分隔符
     * @param filePath 文件路径
     * @param strList 对应CSV中的一行记录
     * */
    public static void writeIntoCSV(Character separator, String filePath, List<List<String>> strList) {
        CsvWriter csvWriter = null;
        try {
            // 创建CSV写对象
            csvWriter = new CsvWriter(filePath, separator, Charset.forName("GBK"));
            // 写标题
            //String[] headers = {"FileName","FileSize","FileMD5"};
            //csvWriter.writeRecord(headers);
            for (List<String> list : strList) {
                    String[] writeLine = new String[list.size()];
                    list.toArray(writeLine);
                    csvWriter.writeRecord(writeLine);
            }

            csvWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != csvWriter) {
                csvWriter.close();
            }
        }
    }
}
