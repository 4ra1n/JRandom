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

public class MainWindows {
    public static void main(String[] args) throws Exception {
        LogManager.setLevel(LogLevel.DEBUG);
        JRandom random = new JRandom();
        for (int i = 0; i < 10; i++) {
            System.out.println(random.randomString(10));
        }
    }
}
