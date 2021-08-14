package com.fjb.common.utils;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

public class ImageUtils {
    /**
     * 判断图片地址是否有效
     */
    public static boolean isImagesTrue(String posturl) throws IOException {
        URL url = new URL(posturl);
        HttpURLConnection urlcon = (HttpURLConnection) url.openConnection();
        urlcon.setRequestMethod("POST");
        urlcon.setRequestProperty("Content-type", "application/x-www-form-urlencoded");
        if (urlcon.getResponseCode() == HttpURLConnection.HTTP_OK) {
            System.out.println("Y");
            return true;
        } else {
            System.out.println("N");
            return false;
        }
    }
    public static boolean getRource(String source) {
        try {
            URL url = new URL(source);
            URLConnection uc = url.openConnection();
            InputStream in = uc.getInputStream();
            if (source.equalsIgnoreCase(uc.getURL().toString()))
                in.close();
            System.out.println("Y");
            return true;
        } catch (Exception e) {
            System.out.println("N");
            return false;
        }
    }

        static int countFiles = 0;// 声明统计文件个数的变量
        static int countFolders = 0;// 声明统计文件夹的变量

        public static File[] searchFile(File folder, final String keyWord) {// 递归查找包含关键字的文件

            File[] subFolders = folder.listFiles(new FileFilter() {// 运用内部匿名类获得文件
                @Override
                public boolean accept(File pathname) {// 实现FileFilter类的accept方法
                    if (pathname.isFile())// 如果是文件
                        countFiles++;
                    else
                        // 如果是目录
                        countFolders++;
                    if (pathname.isDirectory()
                            || (pathname.isFile() && pathname.getName().toLowerCase().contains(keyWord.toLowerCase())))// 目录或文件包含关键字
                        return true;
                    return false;
                }
            });

            List<File> result = new ArrayList<File>();// 声明一个集合
            for (int i = 0; i < subFolders.length; i++) {// 循环显示文件夹或文件
                if (subFolders[i].isFile()) {// 如果是文件则将文件添加到结果列表中
                    result.add(subFolders[i]);
                } else {// 如果是文件夹，则递归调用本方法，然后把所有的文件加到结果列表中
                    File[] foldResult = searchFile(subFolders[i], keyWord);
                    for (int j = 0; j < foldResult.length; j++) {// 循环显示文件
                        result.add(foldResult[j]);// 文件保存到集合中
                    }
                }
            }

            File files[] = new File[result.size()];// 声明文件数组，长度为集合的长度
            result.toArray(files);// 集合数组化
            return files;
        }

    public static void main(String[] args) throws IOException {
        isImagesTrue("http://221.226.21.180/examinationRY/upload/440682198811133364.jpg");
        getRource("http://localhost/dev-api/profile/avatar/2020/12/27/张莹441424199212262261.jpg");

        File folder = new File("D:/jinchu/uploadPath/avatar/2020/12/27");// 默认目录
        String keyword = "张莹441424199212262261";
        if (!folder.exists()) {// 如果文件夹不存在
            System.out.println("目录不存在：" + folder.getAbsolutePath());
            return;
        }
        File[] result = searchFile(folder, keyword);// 调用方法获得文件数组
        System.out.println("在 " + folder + " 以及所有子文件时查找对象" + keyword);
        System.out.println("查找了" + countFiles + " 个文件，" + countFolders + " 个文件夹，共找到 " + result.length + " 个符合条件的文件：");
        for (int i = 0; i < result.length; i++) {// 循环显示文件
            File file = result[i];
            System.out.println(file.getAbsolutePath() + " ");// 显示文件绝对路径
        }
    }
}
