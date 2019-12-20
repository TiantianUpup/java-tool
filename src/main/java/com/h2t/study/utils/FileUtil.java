package com.h2t.study.utils;

import com.h2t.study.exception.CustomException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
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

    public static boolean deleteFile(String filePath) {
        File file = new File(filePath);
        return deleteFile(file);
    }

    public static boolean deleteFile(File file) {
        if (!file.exists()) {
            LOGGER.error("the file is not exist, file name: {}", file.getName());
            throw new CustomException("file not exist");
        }

        if (file.isDirectory()) {
            File[] files = file.listFiles();
            for (File f : files) {
                deleteFile(f);
            }
        }

        return file.delete();
    }

    public static void createFilePath(String filePath) {
        File file = new File(filePath);
        if (!file.exists()) {
            file.mkdirs();
        }

    }

    public static File validateSourcePath(String sourcePath) {
        File sourceFile = new File(sourcePath);
        if (!sourceFile.exists()) {
            LOGGER.error("the source file is not exist, source path: {}", sourceFile.getAbsolutePath());
            throw new CustomException("source file not exist");
        }
        return sourceFile;
    }

    public static File validateTargetPath(String targetPath) {
        File targetFile = new File(targetPath);
        if (!targetFile.exists()) {
            LOGGER.info("the target file is not exist, target path: {}. create", targetFile.getAbsolutePath());
            targetFile.mkdirs();
        }

        return targetFile;
    }

    public static List<File> listFiles(String filePath) {
        File file = new File(filePath);
        if (file.isDirectory()) {
            return Arrays.asList(file.listFiles());
        }

        return Arrays.asList(file);
    }

    public static void renameFileName(String filePath, String newName) {
        File file = new File(filePath);
        if (!file.isDirectory()) {
            File newFile = new File(String.format("%s%s%s", file.getParent(), File.separator, newName));
            file.renameTo(newFile);
        } else {

        }
    }
}
