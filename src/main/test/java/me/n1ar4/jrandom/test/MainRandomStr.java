package me.n1ar4.jrandom.test;

import me.n1ar4.jrandom.core.JRandom;
import me.n1ar4.jrandom.util.Constants;
import me.n1ar4.jrandom.util.JNIUtil;
import me.n1ar4.log.LogLevel;
import me.n1ar4.log.LogManager;

import java.nio.file.Files;

public class MainRandomStr {
    public static void main(String[] args) throws Exception {
        LogManager.setLevel(LogLevel.DEBUG);

        Files.createTempDirectory("jrandom");

        JNIUtil.extractDllSo("jrandom.dll", Constants.TempDir, true);
        JRandom random = new JRandom();
        for (int i = 0; i < 1000; i++) {
            String a = random.randomString(10);
            System.out.println(a);
        }
    }
}
