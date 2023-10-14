package io.vproxy.javaagent;

public class Utils {
    private Utils() {
    }

    private static Logger logger;

    public static void setLogger(Logger logger) {
        Utils.logger = logger;
    }

    public static void log(String msg) {
        var logger = Utils.logger;
        if (logger == null) {
            System.out.println("[javaagent] " + msg);
        } else {
            logger.log(msg);
        }
    }

    public static void log(Throwable t) {
        var logger = Utils.logger;
        if (logger == null) {
            t.printStackTrace(System.out);
        } else {
            logger.log(t);
        }
    }

    public interface Logger {
        default void log(String msg) {
            System.out.println("[javaagent] " + msg);
        }

        default void log(Throwable t) {
            t.printStackTrace(System.out);
        }
    }
}
