package com.xishanqu.excel.util;

import java.io.File;
import java.io.InputStream;

/**
 * @desc:
 * @author: BaoNing
 * @date: 2020/6/19 10:48
 */
public class ExcelFileUtil {

    /**
     * @Desc 读取文件并转化成流
     * @Author BaoNing
     * @Date 2020/6/19 10:50
    */
    public static InputStream getResourcesFileInputStream(String fileName) {
        return Thread.currentThread().getContextClassLoader().getResourceAsStream("" + fileName);
    }

    public static String getPath() {
        return ExcelFileUtil.class.getResource("/").getPath();
    }

    public static File createNewFile(String pathName) {
        File file = new File(getPath() + pathName);
        if (file.exists()) {
            file.delete();
        } else {
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
        }
        return file;
    }

    public static File readFile(String pathName) {
        return new File(getPath() + pathName);
    }

    public static File readUserHomeFile(String pathName) {
        return new File(System.getProperty("user.home") + File.separator + pathName);
    }

}
