package me.n1ar4.jrandom.core;

import me.n1ar4.jrandom.util.Constants;
import me.n1ar4.jrandom.util.JNIUtil;
import me.n1ar4.jrandom.util.OSUtil;
import me.n1ar4.log.LogManager;
import me.n1ar4.log.Logger;

import java.nio.file.Files;
import java.security.SecureRandom;
import java.util.List;

@SuppressWarnings("all")
public class JRandom {
    private static final Logger logger = LogManager.getLogger();
    private static final String letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    private static final String alphanumeric = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

    // ---------- NATIVE ----------
    private native static int checkRDRAND();

    private native static long getRandInt();

    // ---------- PRIVATE ---------
    private final boolean supportRDRAND;
    private final SecureRandom random = new SecureRandom();

    public JRandom() {
        // JNI 加载
        try {
            Files.createTempDirectory(Constants.TempDir);
            if (OSUtil.getOSType().equals(OSUtil.OSType.LINUX)) {
                JNIUtil.extractDllSo("libjrandom.so", Constants.TempDir, true);
            } else if (OSUtil.getOSType().equals(OSUtil.OSType.WINDOWS)) {
                JNIUtil.extractDllSo("jrandom.dll", Constants.TempDir, true);
            } else {
                logger.debug("target os not supported");
            }
        } catch (Exception ex) {
            logger.debug("load native error: {}", ex.toString());
        }

        if (checkRDRAND() == 0) {
            supportRDRAND = false;
            random.setSeed(System.currentTimeMillis());
            logger.debug("your computer not support rdrand");
            return;
        }
        supportRDRAND = true;
        logger.debug("your computer support rdrand");
    }

    /**
     * 最底层的获得随机的函数
     *
     * @return 随机的 long 类型
     */
    private long getLong() {
        if (supportRDRAND) {
            return getRandInt();
        } else {
            return random.nextLong();
        }
    }

    /**
     * 获得任意的 INT
     *
     * @return 随机的 INT 范围
     */
    public int getInt() {
        long longValue = getLong();
        return (int) (longValue & 0xFFFFFFFFL);
    }

    /**
     * 左闭右开指定范围的随机
     *
     * @param start 左
     * @param end   右
     * @return 左闭右开的 INT 随机
     */
    public int getInt(int start, int end) {
        if (start >= end) {
            throw new IllegalArgumentException("start must be less than end");
        }
        long range = (long) end - (long) start;
        long longValue = getLong();
        int randomInRange = (int) (Math.abs(longValue) % range);
        return start + randomInRange;
    }

    /**
     * 获得指定长度的字符串（大小写字母和数字且开头不是数字）
     *
     * @param length 长度
     * @return 随即后的值
     */
    public String randomString(int length) {
        if (length <= 0) {
            throw new IllegalArgumentException("length must be greater than 0");
        }
        StringBuilder sb = new StringBuilder(length);
        int firstCharIndex = getInt(0, letters.length());
        sb.append(letters.charAt(firstCharIndex));
        for (int i = 1; i < length; i++) {
            int charIndex = getInt(0, alphanumeric.length());
            sb.append(alphanumeric.charAt(charIndex));
        }
        return sb.toString();
    }

    /**
     * 获得随机的 BYTES
     *
     * @param length 长度
     * @return 随机的 BYTES
     */
    public byte[] randomBytes(int length) {
        if (length <= 0) {
            throw new IllegalArgumentException("length must be greater than 0");
        }
        byte[] bytes = new byte[length];
        int index = 0;
        while (index < length) {
            int intValue = getInt();
            for (int i = 0; i < 4 && index < length; i++) {
                bytes[index++] = (byte) ((intValue >>> (i * 8)) & 0xFF);
            }
        }
        return bytes;
    }

    /**
     * 获得随机的列表元素
     *
     * @param list List
     * @param <T>  Object
     * @return 随机的
     */
    public <T> T randomEle(List<T> list) {
        if (list == null || list.isEmpty()) {
            throw new IllegalArgumentException("list must not be null or empty");
        }
        int index = getInt(0, list.size());
        return list.get(index);
    }

    /**
     * 获得随机的 INT 字符串
     *
     * @param n 长度
     * @return INT 字符串
     */
    public String randomNumbers(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("length must be greater than 0");
        }
        StringBuilder sb = new StringBuilder(n);
        for (int i = 0; i < n; i++) {
            int digit = getInt(0, 10);
            sb.append(digit);
        }
        return sb.toString();
    }
}
