package com.study.guava.io;

import com.google.common.collect.Lists;
import com.google.common.io.ByteStreams;
import com.google.common.io.CharStreams;
import com.google.common.io.LineProcessor;
import com.google.common.io.Resources;
import org.junit.Test;

import java.io.*;
import java.net.URL;
import java.util.List;

/**
 * @Author Kervin
 * @ClassName com.study.guava.io
 * @Description 测试IO
 * @Date 2019/11/30 14:46
 * @Version 1.0
 */
public class testIO {
    // ByteStreams.copy()方法,数据拷贝
    @Test
    public void copy() {
        URL url = Resources.getResource("application.yml");
        File f = new File(url.getFile());    // 声明File对象

        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(f);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        if (inputStream == null) {
            return;
        }

        try {
            OutputStream outputStream = new FileOutputStream("D:\\project\\hello-world\\study\\temp" + File.separator + "abc.txt");
            // 把InputStream里面的内容写入到OutputStream里面去
            ByteStreams.copy(inputStream, outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // ByteStreams.toByteArray()方法，把InputStream里面的数据读到数组里面去
    @Test
    public void toByteArray() {
        URL url = Resources.getResource("application.yml");
        File f = new File(url.getFile());    // 声明File对象
        // InputStream
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(f);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        if (inputStream == null) {
            return;
        }
        try {
            // InputStream里面的内容读到byte数组里面去
            byte[] byteArrary = ByteStreams.toByteArray(inputStream);
            System.out.println(new String(byteArrary));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    // ByteStreams.read() 把
    @Test
    public void read() {
        URL url = Resources.getResource("application.yml");
        File f = new File(url.getFile());    // 声明File对象
        // InputStream
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(f);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        if (inputStream == null) {
            return;
        }
        try {
            byte[] byteArray = new byte[1024];
            int readLength = ByteStreams.read(inputStream, byteArray, 0, 1024);
            System.out.println("读取都的数据长度 = " + readLength);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // CharStreams.copy() 字符流拷贝
    @Test
    public void copy2() {
        URL url = Resources.getResource("application.yml");
        File f = new File(url.getFile());    // 声明File对象
        try {
            BufferedReader in = new BufferedReader(new FileReader(f));
            StringBuilder stringBuilder = new StringBuilder();
            CharStreams.copy(in, stringBuilder);
            System.out.println(stringBuilder.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // CharStreams.readLines() 一行，一行的读取数据
    @Test
    public void readLines() {
        URL url = Resources.getResource("application.yml");
        File f = new File(url.getFile());    // 声明File对象
        try {
            BufferedReader in = new BufferedReader(new FileReader(f));
            List<String> lineList = CharStreams.readLines(in);
            for (String lineItem : lineList) {
                System.out.println(lineItem);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // CharStreams.readLines(),并且交给LineProcessor处理
    @Test
    public void readLines2() {
        URL url = Resources.getResource("application.yml");
        File f = new File(url.getFile());    // 声明File对象
        try {
            BufferedReader in = new BufferedReader(new FileReader(f));
            List<String> lineList = CharStreams.readLines(in, new LineProcessor<List<String>>() {
                List<String> resultList = Lists.newArrayList();
                @Override
                public boolean processLine(String line) throws IOException {
                    resultList.add(line);
                    return true;
                }

                @Override
                public List<String> getResult() {
                    return resultList;
                }
            });
            // 打印结果
            for (String lineItem : lineList) {
                System.out.println(lineItem);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
