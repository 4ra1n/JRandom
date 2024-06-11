package me.n1ar4.jrandom.core;

import me.n1ar4.log.LogManager;
import me.n1ar4.log.Logger;

public class JRandom {
    private static final Logger logger = LogManager.getLogger();

    private native static int checkRDRAND();

    private native static long getRandInt();

    public JRandom() {
        if (checkRDRAND() == 0) {
            logger.error("your computer not support rdrand");
            return;
        }
        logger.debug("your computer support rdrand");
    }

    public long getInt() {
        return getRandInt();
    }
}
