package me.n1ar4.jrandom.log;

public class LogManager {
    static LogLevel logLevel = LogLevel.INFO;

    public static void setLevel(LogLevel level) {
        logLevel = level;
    }

    public static Logger getLogger() {
        return new Logger();
    }
}
