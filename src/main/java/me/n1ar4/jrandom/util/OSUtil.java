package me.n1ar4.jrandom.util;

public class OSUtil {

    public enum OSType {
        WINDOWS, LINUX, MAC, UNKNOWN
    }

    public static OSType getOSType() {
        String osName = System.getProperty("os.name").toLowerCase();
        if (osName.contains("win")) {
            return OSType.WINDOWS;
        } else if (osName.contains("nux") || osName.contains("nix")) {
            return OSType.LINUX;
        } else if (osName.contains("mac")) {
            return OSType.MAC;
        } else {
            return OSType.UNKNOWN;
        }
    }
}