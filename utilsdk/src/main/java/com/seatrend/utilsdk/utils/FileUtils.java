package com.seatrend.utilsdk.utils;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ly on 2021/1/21 15:19
 */
public class FileUtils {
    /**
     * 读取文本内容String
     *
     * @param inputStream
     * @return 读取文本内容String
     */
    public static String readFileContent(InputStream inputStream) {

        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader reader = null;
        StringBuffer sbf = new StringBuffer();
        try {
            reader = new BufferedReader(inputStreamReader);
            String tempStr;
            while ((tempStr = reader.readLine()) != null) {
                sbf.append(tempStr);
            }
            reader.close();
            return sbf.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
        return sbf.toString();
    }

    /**
     * 将str转换为inputStream
     *
     * @param str
     * @return
     */
    public static InputStream str2InputStream(String str) {
        ByteArrayInputStream is = new ByteArrayInputStream(str.getBytes());
        return is;
    }

    /**
     * 将inputStream转换为str
     *
     * @param is
     * @return
     * @throws IOException
     */
    public static String inputStream2Str(InputStream is) throws IOException {
        StringBuffer sb;
        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(is));

            sb = new StringBuffer();

            String data;
            while ((data = br.readLine()) != null) {
                sb.append(data);
            }
        } finally {
            br.close();
        }

        return sb.toString();
    }

    /**
     * 将file转换为inputStream
     *
     * @param file
     * @return
     * @throws FileNotFoundException
     */
    public static InputStream file2InputStream(File file) throws FileNotFoundException {
        return new FileInputStream(file);
    }

    /**
     * 将inputStream转化为file
     *
     * @param is
     * @param file 要输出的文件目录
     */
    public static void inputStream2File(InputStream is, File file) throws IOException {
        OutputStream os = null;
        try {
            os = new FileOutputStream(file);
            int len = 0;
            byte[] buffer = new byte[8192];

            while ((len = is.read(buffer)) != -1) {
                os.write(buffer, 0, len);
            }
        } finally {
            os.close();
            is.close();
        }
    }

    /**
     *
     * 获取目录下文件名  不包含文件目录下的子文件目录
     * @Author：
     * @Description：获取某个目录下所有直接下级文件，不包括目录下的子目录的下的文件，所以不用递归获取
     * @Date：
     */
    public static List<String> getFilesName(String path) {
        List<String> files = new ArrayList<String>();
        File file = new File(path);
        File[] tempList = file.listFiles();

        for (int i = 0; i < tempList.length; i++) {
            if (tempList[i].isFile()) {
                files.add(tempList[i].getName());
                //文件名，不包含路径
                //String fileName = tempList[i].getName();
            }
            if (tempList[i].isDirectory()) {
                //这里就不递归了，
            }
        }
        return files;
    }

    /**
     * 获取目录下文件对象  不包含文件目录下的子文件目录
     * @param path
     * @return
     */
    public static List<File> getFiles(String path){
        List<File> files = new ArrayList<>();
        if("".equals(path)){
            return null;
        }
        File file = new File(path);
        File[] tempList = file.listFiles();
        for (int i = 0; i < tempList.length; i++) {
            if (tempList[i].isFile()) {
                files.add(tempList[i]);
            }
        }

        return files;
    }

    public static void deleteFile(File file){
        // 判断传递进来的是文件还是文件夹,如果是文件,直接删除,如果是文件夹,则判断文件夹里面有没有东西
        if (file.isDirectory()) {
            // 如果是目录,就删除目录下所有的文件和文件夹
            File[] files = file.listFiles();
            // 遍历目录下的文件和文件夹
            for (File f : files) {
                // 如果是文件,就删除
                if (f.isFile()) {
                    System.out.println("已经被删除的文件:" + f);
                    // 删除文件
                    f.delete();
                } else if (file.isDirectory()) {
                    // 如果是文件夹,就递归调用文件夹的方法
                    deleteFile(f);
                }
            }
            // 删除文件夹自己,如果它低下是空的,就会被删除
            System.out.println("已经被删除的文件夹:" + file);
            file.delete();
        }

        // 如果是文件,就直接删除自己
        System.out.println("已经被删除的文件:" + file);
        file.delete();
    }
}
