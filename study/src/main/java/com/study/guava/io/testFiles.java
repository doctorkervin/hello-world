package com.study.guava.io;

import com.google.common.base.Charsets;
import com.google.common.base.Predicate;
import com.google.common.graph.Traverser;
import com.google.common.hash.HashCode;
import com.google.common.hash.Hashing;
import com.google.common.io.*;
import org.junit.Test;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Consumer;

/**
 * @Author Kervin
 * @ClassName com.study.guava.io
 * @Description 测试File类
 * @Date 2019/11/30 15:02
 * @Version 1.0
 */
public class testFiles {
    // Files.newReader() 把文件的内容读到BufferedReader里面去
    @Test
    public void newReader() {
        //　这里，需要换成你电脑存在的地址
        File file = new File("D:\\project\\hello-world\\study\\temp" + File.separator + "abc.txt");
        try {
            BufferedReader bufferedReader = Files.newReader(file, Charsets.UTF_8);
            List<String> lineList = CharStreams.readLines(bufferedReader);
            for (String lineItem : lineList) {
                System.out.println(lineItem);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Files.newWriter
    @Test
    public void newWriter() {
        File file = new File("D:\\project\\hello-world\\study\\temp" + File.separator + "filewirite.txt");
        try {
            BufferedWriter bufferedWriter = Files.newWriter(file, Charsets.UTF_8);
            bufferedWriter.write("hello word!!!");
//            bufferedWriter.flush();
            Flushables.flushQuietly(bufferedWriter);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Files.asByteSink
    @Test
    public void asByteSink() {
        File file = new File("D:\\project\\hello-world\\study\\temp" + File.separator + "filewirite.txt");
        try {
            ByteSink byteSink = Files.asByteSink(file, FileWriteMode.APPEND);
            OutputStream outputStream = byteSink.openStream();
            outputStream.write("hello word!!!".getBytes(Charsets.UTF_8));
//            bufferedWriter.flush();
            Flushables.flushQuietly(outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 对文件做hash操作
    @Test
    public void hash() {
        File file = new File("D:\\project\\hello-world\\study\\temp" + File.separator + "filewirite.txt");
        try {
            HashCode hashCode = Files.asByteSource(file).hash(Hashing.sha256());
            System.out.println(hashCode.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Files.fileTraverser() 用于遍历文件
    @Test
    public void fileTraverser() {
        Traverser<File> traverser = Files.fileTraverser();
        File file = new File("/home/tuacy/github/google-guava-study/src/main/resources");
        Iterable<File> list = traverser.breadthFirst(file);
        list.forEach(new Consumer<File>() {

            @Override
            public void accept(File file) {
                System.out.println(file.getName());
            }
        });
    }

    // MoreFiles.asCharSource()
    @Test
    public void asCharSource() {
        Path path = Paths.get("/home/tuacy/github/google-guava-study/src/main/resources/abc.txt");
        CharSource charSource = MoreFiles.asCharSource(path, Charsets.UTF_8);
        try {
            BufferedReader bufferedReader = charSource.openBufferedStream();
            List<String> lines = CharStreams.readLines(bufferedReader);
            for (String lineItem : lines) {
                System.out.println(lineItem);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // MoreFiles.deleteDirectoryContents() 删除目录里面的文件
    // MoreFiles.deleteRecursively() 删除目录已经目录里面的文件
    @Test
    public void deleteDirectoryContents() {

        Path path = Paths.get("/home/tuacy/github/google-guava-study/src/main/resources/abc");
        try {
            MoreFiles.deleteDirectoryContents(path, RecursiveDeleteOption.ALLOW_INSECURE);
            MoreFiles.deleteDirectoryContents(path, RecursiveDeleteOption.ALLOW_INSECURE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void createParentDirectories() {

        Path path = Paths.get("/home/tuacy/github/google-guava-study/src/main/resources/abc/123/789/abc.txt");
        try {
            MoreFiles.createParentDirectories(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void isDirectory() {
        Path path = Paths.get("/home/tuacy/github/google-guava-study/src/main/resources");
        Predicate<Path> predicate = MoreFiles.isDirectory();
        System.out.println("是否目录 = " + predicate.apply(path));

    }
}
