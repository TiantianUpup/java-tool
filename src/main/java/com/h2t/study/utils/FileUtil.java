package com.h2t.study.utils;

import com.h2t.study.exception.CustomException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * 文件处理工具类
 *
 * @author: hetiantian
 * @version 1.0
 * @date: 2019/12/12 11:14
 */
public class FileUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(FileUtil.class);

    /**
     * 删除文件或文件夹
     *
     * @param filePath
     * @return
     */
    public static boolean deleteFile(String filePath) {
        File file = new File(filePath);
        return deleteFile(file);
    }

    /**
     * 删除文件或文件夹
     *
     * @param file
     * @return
     */
    public static boolean deleteFile(File file) {
        if (!file.exists()) {
            LOGGER.info("the file path not exist, file path: {}", file.getPath());
        }

        if (file.isDirectory()) {
            File[] files = file.listFiles();
            for (File f : files) {
                deleteFile(f);
            }
        }

        return file.delete();
    }

    /**
     * 创建文件路径
     *
     * @param filePath 文件路径
     */
    public static void createFilePath(String filePath) {
        File file = new File(filePath);
        if (!file.exists()) {
            file.mkdirs();
        }

    }

    /**
     * 检查文件路径是否存在
     *
     * @param filePath 文件路径
     * */
    public static void validatePath(String filePath) {
        File file = new File(filePath);
        validateFile(file);
    }

    /**
     * 检查文件路径是否存在
     *
     * @param file 文件
     * */
    public static void validateFile(File file) {
        if (!file.exists()) {
            LOGGER.error("the file not exist, the file path is:{}", file.getPath());
            throw new CustomException("文件不存在");
        }
    }

    /**
     * 展示指定文件路径下的文件列表
     *
     * @param filePath 文件路径
     * @return
     * */
    public static List<File> listFiles(String filePath) {
        File file = new File(filePath);
        if (file.isDirectory()) {
            return Arrays.asList(file.listFiles());
        }

        return Arrays.asList(file);
    }

    /**
     * 重命名文件或文件名
     *
     * @param filePath 文件路径
     * @param newName 文件或文件夹的新名称
     * */
    public static void renameFileName(String filePath, String newName) {
        File file = new File(filePath);
        validatePath(filePath);
        File newFile = new File(String.format("%s%s%s", file.getParent(), File.separator, newName));
        file.renameTo(newFile);
    }

    /**
     * 读取文件内容
     *
     * @param filePath 文件路径
     * @return
     * */
    public static String readFile(String filePath) {
        StringBuilder content = new StringBuilder();
        try (BufferedReader in = new BufferedReader(new FileReader(filePath))) {
            String lineContent;
            while ((lineContent = in.readLine()) != null) {
                content.append(lineContent + "\n");
            }
        } catch (IOException e) {
            LOGGER.error("reading fileset throw exception, e:{}", e);
        }

        return content.toString();
    }
}
