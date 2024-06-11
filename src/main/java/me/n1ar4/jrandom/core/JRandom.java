package me.n1ar4.jrandom.core;

import me.n1ar4.jrandom.log.LogManager;
import me.n1ar4.jrandom.log.Logger;

public class JRandom {
    private static final Logger logger = LogManager.getLogger();
    private native static int checkRDRAND();
    private native static long getRandInt();

    public long getInt(){
        if (checkRDRAND()==0){
            logger.error("your computer not support rdrand");
            return 0;
        }
        logger.info("your computer support rdrand");
        return getRandInt();
    }
}
