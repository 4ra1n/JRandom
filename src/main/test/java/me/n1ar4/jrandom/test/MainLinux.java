package me.n1ar4.jrandom.test;

import me.n1ar4.jrandom.core.JRandom;
import me.n1ar4.jrandom.util.Constants;
import me.n1ar4.jrandom.util.JNIUtil;
import me.n1ar4.log.LogLevel;
import me.n1ar4.log.LogManager;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class MainLinux {
    public static void main(String[] args) throws Exception {
        LogManager.setLevel(LogLevel.DEBUG);
        Files.createDirectories(Paths.get(Constants.TempDir));
        JNIUtil.extractDllSo("libjrandom.so", Constants.TempDir, true);
        JRandom random = new JRandom();
        for (int i = 0; i < 1000; i++) {
            long a = random.getInt(0,999999999);
            System.out.println(a);
        }
    }
}
