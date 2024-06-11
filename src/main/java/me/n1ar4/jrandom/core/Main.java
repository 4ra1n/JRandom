package me.n1ar4.jrandom.core;

import me.n1ar4.jrandom.util.Constants;
import me.n1ar4.jrandom.util.JNIUtil;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        Files.createDirectories(Paths.get(Constants.TempDir));
        JNIUtil.extractDllSo("jrandom.dll", Constants.TempDir, true);
        JRandom random = new JRandom();
        List<Long> l = new ArrayList<>();
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            long a = random.getInt();
            System.out.println(a);
            if (l.contains(a)) {
                System.out.println("DUP");
                System.out.println(a);
                break;
            } else {
                l.add(a);
            }
        }
    }
}
